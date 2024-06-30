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
	
	//조회 : 단건 메일함
	@Override
	public List<MailVO> mailboxInfo(MailVO mailVO, int empId) {
		return mailMapper.selectMailboxInfo(mailVO, empId);
	}
	
	//조회 : 단건 메일
	@Override
	public MailVO mailInfo(MailVO mailVO) {
		return mailMapper.selectMailInfo(mailVO);
	}
	
	//메일 등록 -> 보낸사람 -> 보낸메일함
	//		  -> 받는사람(들) -> 받은메일함
	//		  -> 참조자(들)  -> 받은메일함
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

	//등록 : 메일작성 후 임시보관
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
	
	//수정 : 임시보관 메일 수정 - 임시보관에서 다시 임시보관누르면 수정된 값 저장
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
	
	//조회 : empId로 email 찾기
	@Override
	public String emailSelect(int empId) {
		return mailMapper.selectEmail(empId);
	}

	
	//수정 : 휴지통으로 이동 (mailIds의 메일함을 휴지통(d5)으로 수정)
	@Override
	public Map<String, Object> moveTrashMail(List<Integer> mailIds) {
		Map<String, Object> result = new HashMap<>();
		try {
			mailMapper.updateTrashMail(mailIds);
			result.put("result", true);
		} catch (Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		return result;
	}
	//수정 : 단건 휴지통 이동
	@Override
	public Map<String, Object> moveTrashMailInfo(MailVO mailVO) {
		Map<String, Object> map = new HashMap<>();
		int result = mailMapper.updateTrashMailInfo(mailVO);
		boolean inSuccessed = false;
		
		if(result == 1) {
			inSuccessed = true;
		}
		map.put("result", inSuccessed);
		map.put("target", mailVO);
		return map;
	}
	
	//삭제 : 메일 완전 삭제 (여러개)
	@Override
	public Map<String, Object> deleteMail(List<Integer> mailIds) {
		Map<String, Object> result = new HashMap<>();
		try {
			mailMapper.deleteDraftMail(mailIds);
			result.put("result", true);
		} catch (Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		return result;
		
	}
	//삭제 : 메일 완전 삭제 (단건)
	@Override
	public int deleteMailInfo(int mailId) {
		return mailMapper.deleteDraftMailInfo(mailId);
	}

	@Override
	public int moveRestoreMail(MailVO mailVO) {
		int result = 0;
		for(Integer mailIds: mailVO.getMailIdList()) {
			mailVO.setMailId(mailIds);
			result = mailMapper.updateRestoreMail(mailVO);
		}
		return result;
	}


	
	
}
