package scheduelp.model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import scheduelp.model.PlannedCourse;
import scheduelp.model.SpecialRequirement;

public class PlannedCourseMapper implements RowMapper<PlannedCourse> {

	@Override
	public PlannedCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
		PlannedCourse course = new PlannedCourse();
		course.setCourseCode(rs.getString("course_code"));
		course.setCourseName(rs.getString("course_name"));
		course.setUnits(rs.getInt("units"));
		
		SpecialRequirement specReqt = new SpecialRequirement();
		specReqt.setRequirementID(rs.getInt("special_requirement"));
		specReqt.setRequirementDesc(rs.getString("requirement_desc"));
		specReqt.setReqtUnits(rs.getInt("reqt_units"));
		course.setSpecialRequirement(specReqt);
		
		return course;
	}
}
