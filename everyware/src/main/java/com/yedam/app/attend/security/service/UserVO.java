package com.yedam.app.attend.security.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //빌더패턴 자동생성
public class UserVO {
	private String accountId;
	private String password;
	private Integer empId;
	private String empName;
	private String tel;
	private String mail;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;			//입사일자
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expireDate;		//퇴사일자
	private String address;			//주소
	private String photo;			//개인사진
	private String departmentId;	//부서번호
	private String departmentName;	//부서이름
	private String departmentEmpId; //부서장사원번호
	private String empPosition;		//직위
	private String posName; 		//직위이름
	
	private String auth; //ROLE
}
