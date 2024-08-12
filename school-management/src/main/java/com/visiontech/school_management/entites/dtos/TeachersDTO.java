package com.visiontech.school_management.entites.dtos;

import lombok.Data;

@Data
public class TeachersDTO {

	private long tid;
	private String firstName;
	private String lastName;
	private String gender;
	private long mobileNumber;
	private String email;
}
