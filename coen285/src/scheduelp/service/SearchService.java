package scheduelp.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scheduelp.dao.ScheduelpDAO;
import scheduelp.dto.CourseDetailTO;
import scheduelp.dto.CourseSearchTO;
import scheduelp.model.Course;

@Service
public class SearchService {

	@Resource
	private ScheduelpDAO scheduelpDAO;

	@Transactional
	public List<CourseDetailTO> findCourses(CourseSearchTO searchTO) {
		List<Course> courses = scheduelpDAO.getCourses(searchTO);

		List<CourseDetailTO> courseList = new ArrayList<CourseDetailTO>();
		Course prevCourse = null;
		CourseDetailTO courseDetail = null;
		for (Course c : courses) {
			if ((prevCourse == null) || (!c.equals(prevCourse))) {
				courseDetail = new CourseDetailTO();
				courseList.add(courseDetail);
			}
			courseDetail.setCourseDetail(c);
			prevCourse = c;
		}

		return courseList;
	}

}
