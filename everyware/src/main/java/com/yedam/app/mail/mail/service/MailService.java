package com.yedam.app.mail.mail.service;

import java.util.List;

public interface MailService {
	//단건메일함
	public List<MailVO> mailboxInfo(MailVO mailVO);

	public MailVO mailInfo(MailVO mailVO);
	
	

}

