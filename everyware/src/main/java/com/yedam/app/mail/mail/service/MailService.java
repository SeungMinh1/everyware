package com.yedam.app.mail.mail.service;

import java.util.List;

public interface MailService {
	//단건 메일함
	public List<MailVO> mailboxInfo(MailVO mailVO);

	//단건 메일
	public MailVO mailInfo(MailVO mailVO);
	
	//메일 등록
	public int mailInsert(MailVO mailVO);
	
	public String recip(MailVO mailVO);
}

