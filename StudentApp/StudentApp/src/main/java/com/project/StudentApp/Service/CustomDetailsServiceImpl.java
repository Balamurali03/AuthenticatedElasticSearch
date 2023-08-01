package com.project.StudentApp.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.StudentApp.Entity.SignupData;
import com.project.StudentApp.Repo.SignupRepo;
@Service
public class CustomDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private SignupRepo signupRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SignupData signupData=signupRepo.findByUserName(username);
		if(signupData==null)
		{
			throw new UsernameNotFoundException("uset not found");
		}
		else
		{
			
		return new User(signupData.getUserName(),signupData.getPassword(),new ArrayList<>());
		}
		
	}

}
