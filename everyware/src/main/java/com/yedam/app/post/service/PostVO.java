package com.yedam.app.post.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class PostVO {
	private Integer postId;  			//게시물 번호
	private Integer boardId; 			//게시판 번호 
	private String notificationYn;  	//공지 여부
	private String holdYn;  			//글고정 여부
	private String title; 				//제목
	@DateTimeFormat(pattern = "yy/MM/dd")
	private Date writeDate; 			//작성일
	private Integer viewCnt;   			//조회수 
	private Integer recommentCnt; 		//추천수
	private String attachmentGroupId;  //첨부파일 그룹번호 
	private String content;  			//내용 
	private Integer empId;  			//사원번호
	
	

}
