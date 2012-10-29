package scheduelp.model;

import java.io.Serializable;

public class CourseSchedule implements Serializable {

	private static final long serialVersionUID = 2420157209142058458L;

	private String quarter;

	private String dayOfWeek;

	private String startTime;
	
	private String endTime;
	
	public CourseSchedule(String quarter, String courseDays, String courseStartTime, String courseEndTime) {
		this.quarter = quarter;
		this.dayOfWeek = courseDays;
		this.startTime = courseStartTime;
		this.endTime = courseEndTime;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
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
	
	@Override
	public int hashCode() {
		return quarter.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean eq = false;
		
		if (obj instanceof CourseSchedule) {
			if (quarter.equalsIgnoreCase(((CourseSchedule)obj).getQuarter())) {
				eq = true;
			}
		}
		return eq;
	}

}
