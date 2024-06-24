package com.yedam.app.mail.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.mail.mail.mapper.MailMapper;
import com.yedam.app.mail.mail.service.MailService;
import com.yedam.app.mail.mail.service.MailVO;

@Service
public class MailServiceImpl implements MailService {
	@Autowired
	MailMapper mailMapper;
	
	@Override
	public List<MailVO> mailboxInfo(MailVO mailVO) {
		return mailMapper.selectMailboxInfo(mailVO);
	}
	
	@Override
	public MailVO mailInfo(MailVO mailVO) {
		return mailMapper.selectMailInfo(mailVO);
	}

	@Override
	public int mailInsert(MailVO mailVO) {
		int result = mailMapper.insertMail(mailVO);
		if(result == 1) {
			return mailVO.getMailId();
		}else {
			return -1;			
		}
	}

	@Override
	public String recip(MailVO mailVO) {
		return mailMapper.selectRe(mailVO);
	}
	
}
