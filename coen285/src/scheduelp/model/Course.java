package scheduelp.model;

import java.io.Serializable;

public class Course implements Serializable {

	private static final long serialVersionUID = 5531142462311439691L;

	private String courseCode;

	private String courseName;

	private String courseDesc;

	private Integer units;

	private String prerequisite;

	private CourseSchedule schedule;

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

	public String getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}

	public CourseSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(CourseSchedule schedule) {
		this.schedule = schedule;
	}

	@Override
	public int hashCode() {
		return courseCode.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean eq = false;

		if (obj instanceof Course) {
			if (courseCode.equalsIgnoreCase(((Course) obj).getCourseCode())) {
				eq = true;
			}
		}
		return eq;
	}

}
