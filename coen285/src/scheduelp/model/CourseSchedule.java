package scheduelp.model;

import java.io.Serializable;

public class CourseSchedule implements Serializable {

	private static final long serialVersionUID = 2420157209142058458L;

	private String quarter;

	private String dayOfWeek;

	private String timeOfDay;

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

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

}
