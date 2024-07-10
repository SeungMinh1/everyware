package com.yedam.app.common.util;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.yedam.app.attend.security.service.LoginUserVO;

@Component
public class AuthUtil {
	
	public static Integer getEmpId() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Integer empId = 0;
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
	public static String getDepartmentName() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String departmentName = null;
		if(authentication != null) {	
			departmentName = ((LoginUserVO)authentication.getPrincipal()).getUserVO().getDepartmentName();
		}
		return departmentName;
		
		
	}
	
	public static String getEmpName() {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			String empName = null;
			if(authentication != null) {	
				empName = ((LoginUserVO)authentication.getPrincipal()).getUserVO().getEmpName();
			}
			return empName;
		}
	
	public static String getEmpPosition() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String empPosition = null;
		if(authentication != null) {	
			empPosition = ((LoginUserVO)authentication.getPrincipal()).getUserVO().getPosName();
		}
		return empPosition;
	}
	
	
	}
