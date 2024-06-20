package com.yedam.app.attend.emp.service;

import java.util.Date;

import lombok.Data;

@Data
public class EmpVO {
	private Integer empId;  		//사원번호   
	private String empName;			//사원이름
	private String tel;				//연락처
	private  String mail;			//이메일
	private Date hireDate;			//입사일자
	private Date expireDate;		//퇴사일자
	private String address;			//주소
	private String photo;			//개인사진
	private String departmentId;	//부서번호
	private String departmentEmpId; //부서장사원번호
	private String empPosition;		//직위
	
	

}
