package com.blogApplication.blog.blogApplication.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	@NotEmpty
	@Size(min = 4 ,message="Username must be of minimum 4 character")
	private String name;
	
	@Email(message="Email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=4, max=10 , message="Password must be minimum of 3 characters and maximum of 10 characters")
	private String password;
	
	@NotEmpty
	private String about;

}
