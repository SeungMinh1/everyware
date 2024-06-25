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
	public int senderMail(MailVO mailVO) {
		//int result = mailMapper.insertSenderMail(mailVO);
		int result = 0;
		String ress = "";
		for(String recip: mailVO.getRecipList()) {
			ress += recip + ", ";
		}
		mailVO.setRecipient(ress);
		result = mailMapper.insertSenderMail(mailVO); 
		
		
		 for(String recip: mailVO.getRecipList()) { 
			 mailVO.setRecipient(recip);
			 result = mailMapper.insertRecipMail(mailVO); 
		 }
		 
		return result;
	}
}
