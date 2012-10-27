package scheduelp.model;

import java.io.Serializable;

public class ProgramOfStudy implements Serializable {

	private static final long serialVersionUID = 3003713312773775334L;

	private String courseCode;

	private String courseName;

	private Integer units;

	private String specialRequirement;

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

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public String getSpecialRequirement() {
		return specialRequirement;
	}

	public void setSpecialRequirement(String specialRequirement) {
		this.specialRequirement = specialRequirement;
	}

}
