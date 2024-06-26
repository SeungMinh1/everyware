package com.yedam.app.mail.mail.service;

import java.util.List;
import java.util.Map;

public interface MailService {
	//조회 : 단건 메일함
	public List<MailVO> mailboxInfo(MailVO mailVO);

	//조회 : 단건 메일
	public MailVO mailInfo(MailVO mailVO);
	
	//등록 : 메일작성 후 보내기 - 보낸사람- 보낸메일함 / 받은사람(들) - 받은메일함
	public int senderMail(MailVO mailVO);
	
	//등록 : 메일작성 후 임시보관
	public int draftMail(MailVO mailVO);
	
	
}

