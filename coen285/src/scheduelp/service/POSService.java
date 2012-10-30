package scheduelp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scheduelp.dao.ScheduelpDAO;

@Service
public class POSService {

	@Resource
	private ScheduelpDAO scheduelpDAO;

	@Transactional
	public void addCourse(String userID, String degree, String courseCode) {
		scheduelpDAO.addCourseToProgram(userID, degree, courseCode);
	}

}