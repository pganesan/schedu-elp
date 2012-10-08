package scheduelp.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import scheduelp.model.Student;
import scheduelp.model.mapping.StudentMapper;

@Repository
public class ScheduelpDAO {

	public static final String SQL_PERCENT = "%";

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Student getStudentRecord(String userName, String pwd) {
		Student member = null;

		String sql = "SELECT user_id, first_name, middle_name, last_name FROM member " + 
				"WHERE user_id=:userName AND user_password=:pwd";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userName", userName);
		parameters.put("pwd", pwd);
		try {
			member = jdbcTemplate.queryForObject(sql, parameters, new StudentMapper());
		} catch (EmptyResultDataAccessException dae) {
			// return null
		}

		return member;
	}

	// sample insert
	/*
	public Integer insertReservation(Reservation reservation) {
		String sql = "INSERT INTO reservation(restaurant_id,type_id,user_id,reserve_datetime) " + 
				"VALUES(:restaurantId, :tableType, :userId, str_to_date(concat(:reserveDate,:reserveTime),'%m/%d/%Y%h:%i %p'))";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(reservation);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(sql, parameters, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	// sample update
	public void updateReservation(Reservation reservation) {
		String sql = "UPDATE reservation SET reserve_datetime = str_to_date(concat(:reserveDate,:reserveTime),'%m/%d/%Y%h:%i %p'), " + 
				"type_id=:tableType WHERE reservation_number = :reservationNumber";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(reservation);
		jdbcTemplate.update(sql, parameters);
	}
	
	// sample delete
	public void deleteReservation(Reservation reservation) {
		String sql = "DELETE FROM reservation WHERE reservation_number = :reservationNumber";

		SqlParameterSource parameters = new MapSqlParameterSource("reservationNumber",
				reservation.getReservationNumber());
		jdbcTemplate.update(sql, parameters);
	}
	*/

}
