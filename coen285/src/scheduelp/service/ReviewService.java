package scheduelp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scheduelp.dao.ScheduelpDAO;
import scheduelp.dto.CourseDetailTO;
import scheduelp.dto.CourseSearchTO;
import scheduelp.model.Course;
import scheduelp.model.Review;

@Service
public class ReviewService {

	@Resource
	private ScheduelpDAO scheduelpDAO;

	@Transactional
	public CourseDetailTO getReviews(String courseCode) {
		// get course information
		List<Course> courses = scheduelpDAO.getCourses(new CourseSearchTO(courseCode));
		CourseDetailTO courseDetail = new CourseDetailTO();
		for (Course c : courses) {
			courseDetail.setCourseDetail(c);
		}

		// get all reviews for the course
		List<Review> reviews = scheduelpDAO.getReviews(courseCode);
		courseDetail.setReviews(reviews);

		return courseDetail;
	}

	@Transactional
	public void postReview(Review review) {
		// insert new review
		scheduelpDAO.insertReview(review);
	}
}