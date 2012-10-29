package scheduelp.dto;


public class CourseSearchTO {

	private String courseCode;

	private String courseName;

	private String[] selectedDays;

	private String startTime;

	private String endTime;
	
	public CourseSearchTO() {
		
	}
	
	public CourseSearchTO(String courseCode) {
		this.courseCode = courseCode;
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

	public String[] getSelectedDays() {
		return selectedDays;
	}

	public void setSelectedDays(String[] selectedDays) {
		this.selectedDays = selectedDays;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
