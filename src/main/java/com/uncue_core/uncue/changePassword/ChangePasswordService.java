package com.uncue_core.uncue.changePassword;

import com.uncue_core.uncue.Security.service.UserInfo;
import com.uncue_core.uncue.constants.DisplayMessage;
import com.uncue_core.uncue.constants.LoggerMessage;
import com.uncue_core.uncue.dto.ReturningMessage;
import com.uncue_core.uncue.employee.Employee;
import com.uncue_core.uncue.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ChangePasswordService {

	static Logger logger = Logger.getLogger(ChangePasswordService.class.getName());

	@Autowired
	EmployeeRepository employeeRepository;

	// Get userInfo from Security Context to get Current loggedIn user information
	@Autowired
	UserInfo userInfo;



	public ReturningMessage passwordUpdate(ChangePassword changePassword) throws Exception {
		logger.info(LoggerMessage.UpdateVariable);
		ReturningMessage iUDReturningMessage = new ReturningMessage();
		System.out.println(userInfo.getLoggedInUser().getUsername()+"sfjdfdhdkfh");
		System.out.println(changePassword.getNewPassword()+"sfjdfdhdkfh");
		System.out.println(changePassword.getOldPassword()+"sfjdfdhdkfh");
		Employee employee = null;

		List<Employee> users = employeeRepository.findByEmail(userInfo.getLoggedInUser().getUsername()).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + userInfo.getLoggedInUser().getUsername()));

		for(Employee rd:users) {
			System.out.println("Empoloyee"+rd.toString());
			employee= rd;
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	//	changePassword.setOldPassword(encoder.encode(changePassword.getOldPassword()));
		if (encoder.matches(changePassword.getOldPassword(),employee.getPassword())) {
			employee.setPassword(encoder.encode(changePassword.getNewPassword()));
			Employee passwordChangedUser = employeeRepository.save(employee);
			if (passwordChangedUser != null) {
				iUDReturningMessage.setData("");
				iUDReturningMessage.setError(false);
				iUDReturningMessage.setStatusMessage(DisplayMessage.ChangePassword);
			} else {
				iUDReturningMessage.setData("");
				iUDReturningMessage.setError(true);
				iUDReturningMessage.setStatusMessage(DisplayMessage.ChangePasswordError);
			}
		} else {
			iUDReturningMessage.setData("");
			iUDReturningMessage.setError(true);
			iUDReturningMessage.setStatusMessage(DisplayMessage.CurrentPassword);
		}
		return iUDReturningMessage;

	}
}
