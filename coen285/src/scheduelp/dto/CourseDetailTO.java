package scheduelp.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scheduelp.common.ScheduelpUtil;
import scheduelp.model.Course;
import scheduelp.model.CourseSchedule;
import scheduelp.model.Review;

public class CourseDetailTO {

	private String courseCode;

	private String courseName;

	private String courseDesc;

	private Integer units;

	private Set<String> prerequisites;

	private Set<CourseSchedule> schedules;

	private List<Review> reviews;

	public CourseDetailTO() {
		this.prerequisites = new HashSet<String>();
		this.schedules = new HashSet<CourseSchedule>();
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Set<String> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(Set<String> prerequisites) {
		this.prerequisites = prerequisites;
	}

	public Set<CourseSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(Set<CourseSchedule> schedules) {
		this.schedules = schedules;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void setCourseDetail(Course course) {
		// add course information
		this.courseCode = course.getCourseCode();
		this.courseName = course.getCourseName();
		this.courseDesc = course.getCourseDesc();
		this.units = course.getUnits();

		// add prerequisites for the course
		if (!ScheduelpUtil.isNull(course.getPrerequisite())) {
			this.prerequisites.add(course.getPrerequisite());
		} else {
			this.prerequisites.add("None");
		}

		// add schedules for the course
		this.schedules.add(course.getSchedule());
	}
}
