package com.yedam.app.common.util;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.yedam.app.attend.security.service.LoginUserVO;

@Component
public class AuthUtil {
	
	public static Integer getEmpId() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Integer empId = null;
		if(authentication != null) {	
			empId = ((LoginUserVO)authentication.getPrincipal()).getUserVO().getEmpId();
		}
		return empId;
		
		
	}
	public static String getDepartmentId() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String departmentId = null;
		if(authentication != null) {	
			departmentId = ((LoginUserVO)authentication.getPrincipal()).getUserVO().getDepartmentId();
		}
		return departmentId;
		
		
	}
}
