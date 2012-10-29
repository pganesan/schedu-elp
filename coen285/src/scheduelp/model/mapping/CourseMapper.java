package scheduelp.model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import scheduelp.model.Course;
import scheduelp.model.CourseSchedule;

public class CourseMapper implements RowMapper<Course> {

	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		Course course = new Course();
		course.setCourseCode(rs.getString("course_code"));
		course.setCourseName(rs.getString("course_name"));
		course.setCourseDesc(rs.getString("course_description"));
		course.setUnits(rs.getInt("units"));
		course.setPrerequisite(rs.getString("prerequisite"));
		CourseSchedule schedule = new CourseSchedule(rs.getString("quarter"),
				rs.getString("course_days"), rs.getString("course_start_time"),
				rs.getString("course_end_time"));
		course.setSchedule(schedule);

		return course;
	}
}
