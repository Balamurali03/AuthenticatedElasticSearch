package com.project.StudentApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project.StudentApp.Entity.JwtResponse;
import com.project.StudentApp.Entity.SignupData;
import com.project.StudentApp.Repo.SignupRepo;
import com.project.StudentApp.Service.CustomDetailsServiceImpl;
import com.project.StudentApp.Utility.JwtUtility;

@RestController
public class SignupController {

	@Autowired
	private SignupRepo signupRepo;
	 @Autowired
	 private AuthenticationManager authenticationManager;
@Autowired
private CustomDetailsServiceImpl customDetailsServiceImpl;
@Autowired
private JwtUtility jwtUtility;
	@PostMapping("/signUp")
	public String signUp(@RequestBody SignupData signupData)
	{
		
		SignupData newSignupData=new SignupData();
		newSignupData.setUserName(signupData.getUserName());
		newSignupData.setPassword(new BCryptPasswordEncoder().encode(signupData.getPassword()));
		SignupData sData= signupRepo.save(newSignupData);
		if(sData!=null)
		{
			return "SignedUp Successfull";
		}
		return "SignUp unsuccessfull";
	}
	@PostMapping("/login")
	public JwtResponse login(@RequestHeader String username, @RequestHeader String password) throws Exception
	{
		 try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                           username,password
	                    )
	            );
	        } catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);
	        }
		 final UserDetails userDetails
         = customDetailsServiceImpl.loadUserByUsername(username);
		 
		 final String token =
	                jwtUtility.generateToken(userDetails);

	        return  new JwtResponse(token);
	}
}
