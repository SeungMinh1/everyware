package com.yedam.app.attach.service;

import lombok.Data;

@Data
public class FileVO {
	private int fileId;		  		  //첨부파일 번호
	private String attachmentGroupId; //첨부파일 그룹번호
	private String fileType;    	  //첨부 파일 종류
	private String originFileName;    //원본 파일 이름
	private String uploadFileName;    //업로드 파일 이름
	private String uploadPath;		  //업로드 경로
	private String type;			  //유형 (mail, post 등등)
	private String ext; 			  //확장자
	private long fileSize;		      //파일 크기
	private int empId;			      //사원 번호
	
}




