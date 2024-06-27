package com.yedam.app.mail.mail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<MailVO> mailboxInfo(MailVO mailVO, int empId) {
		return mailMapper.selectMailboxInfo(mailVO, empId);
	}
	
	@Override
	public MailVO mailInfo(MailVO mailVO) {
		return mailMapper.selectMailInfo(mailVO);
	}
	
	//메일 등록 -> 보낸사람 -> 보낸메일함
	//		  -> 받는사람(들) -> 받는메일함
	@Override
	public int senderMail(MailVO mailVO) {
		int result = 0;
		
		// 보낸사람 ================== 
		String recips = ""; //2) for문 값을 담음 
		for(String recip: mailVO.getRecipList()) { //RecipList는 mail_insert.html에서 값을 받음
			recips += recip + " "; //1) 받는사람 이메일 , 이메일 ...
		}
		mailVO.setRecipient(recips); //3) 보낸사람의 보낸메일함에 받는사람을 여러명 출력해줌

		String ccs = "";
		for(String cc: mailVO.getCcList()) {
			ccs += cc;// + ", ";
		}
		mailVO.setCc(ccs);
		result = mailMapper.insertSenderMail(mailVO); 
		
		// 받는사람 ==================
		for(String recip: mailVO.getRecipList()) { 
			 mailVO.setCc(ccs);
			 int empId = mailMapper.selectEmpId(recip);
			 mailVO.setEmpId(empId);
			 mailVO.setRecipient(recips);
			 result = mailMapper.insertRecipMail(mailVO);
		}
		
		// 참조자  ====================
		for(String cc:mailVO.getCcList()) {
			mailVO.setCc(ccs);
			mailVO.setRecipient(recips);
			result = mailMapper.insertCcMail(mailVO);
		}
		return result;
	}

	//메일작성 후 임시보관
	@Override
	public int draftMail(MailVO mailVO) {
		int result = 0;
		String recips = "";
		for(String recip: mailVO.getRecipList()) { 
			recips += recip + ", ";
		}
		mailVO.setRecipient(recips);

		String ccs = "";
		for(String cc: mailVO.getCcList()) {
			ccs += cc + ", ";
		}
		mailVO.setCc(ccs);
		result = mailMapper.insertDraftMail(mailVO);
		return result;
	}
	//임시보관 후 수정(다시 임시보관을 누르면 수정됨)
	@Override
	public Map<String, Object> updateDraftMail(MailVO mailVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		
		int result = 0;
		String recips = "";
		for(String recip: mailVO.getRecipList()) { 
			recips += recip + ", ";
		}
		mailVO.setRecipient(recips);

		String ccs = "";
		for(String cc: mailVO.getCcList()) {
			ccs += cc + ", ";
		}
		mailVO.setCc(ccs);
		
		result = mailMapper.updateDraftMail(mailVO);
		
		if(result == 1) {
			inSuccessed = true;
		}
		map.put("result", inSuccessed);
		map.put("target", mailVO);
		
		return map;
	}

	@Override
	public int deleteMail(int mailId) {
		return mailMapper.deleteDraftMail(mailId);
	}

	@Override
	public String emailSelect(int empId) {
		return mailMapper.selectEmail(empId);
	}
	
	
}
