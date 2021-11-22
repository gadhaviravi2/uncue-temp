package com.uncue_core.uncue.Security.service;


import com.uncue_core.uncue.Security.jwt.CustomUserDetails;
import com.uncue_core.uncue.employee.Employee;
import com.uncue_core.uncue.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	EmployeeRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		System.out.println("UserService");
		
		System.out.println(userName);
		
		CustomUserDetails customUserDetails = new CustomUserDetails();
		List<Employee> users = userRepository.findByEmail(userName).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + userName));
		for(Employee rd:users) {
		customUserDetails.setUsers(rd);
		}

		return customUserDetails;
	}

}
