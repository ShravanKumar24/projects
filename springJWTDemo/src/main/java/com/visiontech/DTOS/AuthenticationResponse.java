package com.visiontech.DTOS;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
	private String token;
}
