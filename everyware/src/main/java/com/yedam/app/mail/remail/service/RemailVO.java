package com.yedam.app.mail.remail.service;

import lombok.Data;

@Data		 //메일 받는사람
public class RemailVO {
	private Integer mailId;       //메일번호
	private String recipient;     //받는사람    
	private int receiveStatus;    //수신상태
}
