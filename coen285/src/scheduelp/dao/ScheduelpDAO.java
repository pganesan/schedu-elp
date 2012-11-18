package scheduelp.dao;

import static scheduelp.common.ScheduelpUtil.isNull;
import static scheduelp.common.ScheduelpUtil.join;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import scheduelp.dto.CourseSearchTO;
import scheduelp.model.Course;
import scheduelp.model.PlannedCourse;
import scheduelp.model.Review;
import scheduelp.model.Student;
import scheduelp.model.mapping.CourseMapper;
import scheduelp.model.mapping.PlannedCourseMapper;
import scheduelp.model.mapping.ReviewMapper;
import scheduelp.model.mapping.StudentMapper;

@Repository
public class ScheduelpDAO {

	public static final String SQL_PERCENT = "%";

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Student getStudentRecord(String userID, String pwd) {
		Student student = null;

		String sql = "SELECT s.student_id, s.first_name, s.middle_name, s.last_name, s.email "
				+ ", s.degree, d.degree_name FROM student s "
				+ "INNER JOIN degree_program d ON s.degree = d.degree_code "
				+ "WHERE student_id=:userID AND student_pwd=:pwd";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userID", userID);
		parameters.put("pwd", pwd);
		try {
			student = jdbcTemplate.queryForObject(sql, parameters, new StudentMapper());
		} catch (EmptyResultDataAccessException dae) {
			// return null
		}

		return student;
	}

	public List<Course> getCourses(CourseSearchTO searchTO) {
		StringBuffer sql = new StringBuffer(
				"SELECT c.course_code, c.course_name, c.course_description, c.units "
						+ ", r.prerequisite " + ", s.quarter, s.course_days "
						+ ", date_format(s.course_start_time,'%h:%i %p') as course_start_time "
						+ ", date_format(s.course_end_time,'%h:%i %p') as course_end_time  "
						+ "FROM course c  "
						+ "LEFT JOIN course_prerequisite r ON c.course_code = r.course "
						+ "LEFT JOIN course_schedule s ON c.course_code = s.course_code "
						+ "WHERE c.course_name LIKE :courseName "
						+ "AND c.course_code LIKE :courseCode");

		Map<String, Object> parameters = new HashMap<String, Object>();
		if (!isNull(searchTO.getCourseName())) {
			parameters.put("courseName",
					SQL_PERCENT.concat(searchTO.getCourseName()).concat(SQL_PERCENT));
		} else {
			parameters.put("courseName", SQL_PERCENT);
		}

		if (!isNull(searchTO.getCourseCode())) {
			parameters.put("courseCode", searchTO.getCourseCode());
		} else {
			parameters.put("courseCode", SQL_PERCENT);
		}

		String[] courseDays = searchTO.getSelectedDays();
		if ((courseDays != null) && (courseDays.length != 0)) {
			sql.append(" AND s.course_days REGEXP :courseDays");
			parameters.put("courseDays", join(courseDays, "|"));
		}
		if ((!isNull(searchTO.getStartTime())) && (!isNull(searchTO.getEndTime()))) {
			sql.append(" AND s.course_start_time >= str_to_date(:startTime,'%h:%i %p') "
					+ "AND s.course_end_time <= str_to_date(:endTime,'%h:%i %p') ");
			parameters.put("startTime", searchTO.getStartTime());
			parameters.put("endTime", searchTO.getEndTime());
		}
		sql.append(" ORDER BY c.course_code, s.quarter");

		List<Course> courses = jdbcTemplate.query(sql.toString(), parameters, new CourseMapper());

		return courses;
	}

	public List<Review> getReviews(String courseCode) {
		String sql = "SELECT review_id, course_code, s.first_name, rating, comments "
				+ ", date_format(review_datetime,'%m/%d/%Y %h:%i %p') AS review_datetime "
				+ "FROM course_review r " + "JOIN student s ON r.student_id = s.student_id "
				+ "WHERE r.course_code = :courseCode " + "ORDER BY r.review_datetime DESC";

		SqlParameterSource parameters = new MapSqlParameterSource("courseCode", courseCode);

		return jdbcTemplate.query(sql, parameters, new ReviewMapper());
	}

	public void insertReview(Review review) {
		String sql = "INSERT INTO course_review(course_code,student_id,rating,comments,review_datetime) "
				+ "VALUES(:course,:student,:rating,:comments,now())";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(review);
		jdbcTemplate.update(sql, parameters);
	}	
	
	public List<PlannedCourse> getPlannedCourses(String userID, String degree) {
		String sql = "SELECT p.course_code, "
				+ "c.course_name, c.units, "
				+ "COALESCE(cr.special_requirement,999999) AS special_requirement, "
				+ "IFNULL(r.requirement_desc,'Electives') AS requirement_desc, r.units AS reqt_units "
				+ "FROM program_of_studies p "
				+ "INNER JOIN course c ON p.course_code = c.course_code "
				+ "LEFT JOIN course_spec_reqt cr ON (p.course_code = cr.course_code AND (cr.degree = :degree OR cr.degree IS NULL)) "
				+ "LEFT JOIN special_requirement r ON cr.special_requirement = r.requirement_id "
				+ "WHERE p.student_id = :studentID "
				+ "ORDER BY COALESCE(cr.special_requirement,999999) ";

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("studentID", userID);
		parameters.put("degree", degree);

		return jdbcTemplate.query(sql, parameters, new PlannedCourseMapper());
	}
	
	public void insertCourse(String student_id, String course_code) {
		String sql = "INSERT INTO program_of_studies(student_id, course_code) "
				+ "VALUES(:studentID,:courseCode)";

		Map<String, Object> paramMap = new HashMap <String, Object>();
		paramMap.put("studentID", student_id);
		paramMap.put("courseCode", course_code);
		
		SqlParameterSource parameters = new MapSqlParameterSource(paramMap);
		jdbcTemplate.update(sql, parameters);
	}

	// sample insert
	/*
	 * public Integer insertReservation(Reservation reservation) { String sql =
	 * "INSERT INTO reservation(restaurant_id,type_id,user_id,reserve_datetime) "
	 * +
	 * "VALUES(:restaurantId, :tableType, :userId, str_to_date(concat(:reserveDate,:reserveTime),'%m/%d/%Y%h:%i %p'))"
	 * ;
	 * 
	 * SqlParameterSource parameters = new
	 * BeanPropertySqlParameterSource(reservation); KeyHolder keyHolder = new
	 * GeneratedKeyHolder();
	 * 
	 * jdbcTemplate.update(sql, parameters, keyHolder); return
	 * keyHolder.getKey().intValue(); }
	 * 
	 * // sample update public void updateReservation(Reservation reservation) {
	 * String sql =
	 * "UPDATE reservation SET reserve_datetime = str_to_date(concat(:reserveDate,:reserveTime),'%m/%d/%Y%h:%i %p'), "
	 * + "type_id=:tableType WHERE reservation_number = :reservationNumber";
	 * 
	 * SqlParameterSource parameters = new
	 * BeanPropertySqlParameterSource(reservation); jdbcTemplate.update(sql,
	 * parameters); }
	 * 
	 * // sample delete public void deleteReservation(Reservation reservation) {
	 * String sql =
	 * "DELETE FROM reservation WHERE reservation_number = :reservationNumber";
	 * 
	 * SqlParameterSource parameters = new
	 * MapSqlParameterSource("reservationNumber",
	 * reservation.getReservationNumber()); jdbcTemplate.update(sql,
	 * parameters); }
	 */

}
