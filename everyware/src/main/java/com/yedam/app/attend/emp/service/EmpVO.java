package com.yedam.app.attend.emp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmpVO {
	private Integer empId;  		//사원번호   
	private String empName;			//사원이름
	private String tel;				//연락처
	private  String mail;			//이메일
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
	//게정
	private String accountId; //계정 아이디
	private String password; //계정 비밀번호

}
