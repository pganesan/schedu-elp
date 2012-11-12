package scheduelp.model;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = -9175281480564523747L;

	private String studentID;

	private String studentPwd;

	private String firstName;

	private String middleName;

	private String lastName;

	private String phone;

	private String email;

	private String degree;

	private String degreeDesc;

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getStudentPwd() {
		return studentPwd;
	}

	public void setStudentPwd(String studentPwd) {
		this.studentPwd = studentPwd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public void setDegreeDesc(String degreeDesc) {
		this.degreeDesc = degreeDesc;
	}

	public String getDegreeDesc() {
		return degreeDesc;
	}
}
