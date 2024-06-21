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
	
}
