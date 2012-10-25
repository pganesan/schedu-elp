package scheduelp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scheduelp.common.ScheduelpException;
import scheduelp.dao.ScheduelpDAO;
import scheduelp.dto.UserSessionTO;
import scheduelp.model.Student;

@Service
public class LoginService {
	
	@Resource
	private ScheduelpDAO scheduelpDAO;

	@Transactional
	public UserSessionTO login(UserSessionTO credentials) throws ScheduelpException {
		Student student = scheduelpDAO.getStudentRecord(credentials.getUserID(),
				credentials.getUserPwd());
		if (student == null) {
			throw new ScheduelpException("Username/password is incorrect");
		}

		UserSessionTO userTO = UserSessionTO.createUser(student);
		return userTO;
	}

}
