package com.yedam.app.mail.mail.mapper;

import java.util.List;

import com.yedam.app.mail.mail.service.MailVO;

public interface MailMapper {
	//단건 메일함
	public List<MailVO> selectMailboxInfo(MailVO mailVO);
	
	//메일 단건조회
	public MailVO selectMailInfo(MailVO mailVO);
	
	

}
