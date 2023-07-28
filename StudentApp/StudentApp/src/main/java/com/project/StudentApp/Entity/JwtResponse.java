package com.project.StudentApp.Entity;

import lombok.Data;

@Data

public class JwtResponse {

	private String jwtToken;

	public JwtResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}
}
