package scheduelp.dto;

import scheduelp.model.Student;

public class UserSessionTO {

	private String userID;

	private String userPwd;

	private String firstName;

	private String middleName;

	private String lastName;

	private String email;
	
	private String degree;
	
	private String degreeDesc;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public String getDegreeDesc() {
		return degreeDesc;
	}

	public void setDegreeDesc(String degreeDesc) {
		this.degreeDesc = degreeDesc;
	}
	
	public static UserSessionTO createUser(Student student) {
		UserSessionTO user = new UserSessionTO();

		user.setUserID(student.getStudentID());
		user.setFirstName(student.getFirstName());
		user.setMiddleName(student.getMiddleName());
		user.setLastName(student.getLastName());		
		user.setEmail(student.getEmail());
		user.setDegree(student.getDegree());
		user.setDegreeDesc(student.getDegreeDesc());
		
		return user;
	}

}
