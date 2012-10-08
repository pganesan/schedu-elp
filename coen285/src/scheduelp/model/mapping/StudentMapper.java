package scheduelp.model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import scheduelp.model.Student;

public class StudentMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student member = new Student();
		member.setUserId(rs.getString("user_id"));
		member.setFirstName(rs.getString("first_name"));
		member.setMiddleName(rs.getString("middle_name"));
		member.setLastName(rs.getString("last_name"));

		return member;
	}

}
