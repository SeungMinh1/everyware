package com.yedam.app.mail.mail.service;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data		 //메일	
public class MailVO { 
	private Integer mailId;        //메일번호
	private String mailboxId;      //메일함번호  
	private String sender;         //보낸사람
	private String title;          //제목
	private String content;        //내용
	private Date sendDate;         //전송일자
	private Date deleteDate;       //삭제일자
	private String readYn;         //읽은여부
	private String sendYn;         //보낸여부
	private String deleteYn;       //삭제여부
	private String replyMailId;    //답장메일번호
	private int empId;             //사원번호
	private String mailboxName;
	
	private String recipient;        //받는사람
	private List<String> recipList;  //받는사람들    
	private int receiveStatus;       //수신상태
	
	private String cc;			 //참조자	
	private List<String> ccList; //참조자들
	
}
