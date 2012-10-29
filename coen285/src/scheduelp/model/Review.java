package scheduelp.model;

import java.io.Serializable;

public class Review implements Serializable {

	private static final long serialVersionUID = -3650020561147373965L;

	private Integer reviewID;

	private String course;

	private String student;

	private Integer rating;

	private String comments;

	private String reviewDateTime;

	public Integer getReviewID() {
		return reviewID;
	}

	public void setReviewID(Integer reviewID) {
		this.reviewID = reviewID;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getReviewDateTime() {
		return reviewDateTime;
	}

	public void setReviewDateTime(String reviewDateTime) {
		this.reviewDateTime = reviewDateTime;
	}

}
