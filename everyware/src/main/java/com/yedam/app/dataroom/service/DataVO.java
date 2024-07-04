package com.yedam.app.dataroom.service;

import java.util.Date;

import lombok.Data;

@Data
public class DataVO {
	private int dataId;			   //자료_번호
	private Date registrateDate;   //등록_일자
	private String departmentId;   //부서_번호
	private int empId;			   //사원_번호
	private String attachmentGroupId; //첨부파일 그룹번호
	private String title;			  //파일이름
	private String remarks;			  //비고
}
