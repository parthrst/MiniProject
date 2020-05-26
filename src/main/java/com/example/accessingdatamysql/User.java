package com.example.accessingdatamysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Value;
@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"uname", "phone"})
	)
@Entity // This tells Hibernate to make a table out of this class
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotNull(message="UserName is mandatory")
	private String uname;
	@NotNull(message="First Name is Required")
	private String fame;
	@NotNull(message="Last Name is Required")
	private String lname;
	@NotNull(message="Phone Number is required")
	@Pattern(regexp="^[0-9]{10}",message="Enter a 10 digit phone number")  
	private String phone;
	@NotNull(message="Email is Required")
	@Pattern(regexp="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}",message="Enter a Valid Email ID")  
	@Column(unique=true)
		private String email;
	@NotNull(message="Password can not be empty")
	    private String password;
	@Value("${some.key:false}")
	private boolean admin;
	@Value("${some.key:false}")
	private boolean active;
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getFame() {
		return fame;
	}

	public void setFame(String fame) {
		this.fame = fame;
	}

	
public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}



	public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
