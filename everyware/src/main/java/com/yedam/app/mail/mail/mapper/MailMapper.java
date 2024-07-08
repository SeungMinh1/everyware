package com.yedam.app.mail.mail.mapper;

import java.util.List;

import com.yedam.app.mail.mail.service.MailVO;

public interface MailMapper {
	//단건 메일함
	public List<MailVO> selectMailboxInfo(MailVO mailVO, int empId);
	
	//메일 단건조회
	public MailVO selectMailInfo(MailVO mailVO);
	
	//empId 조회
	public int selectEmpId(String mail);
	
	//email 조회
	public String selectEmail(int empId);
	
	//복구할메일함 조회
	public String selectRestore(int mailId);
	
	//메일 등록
	public int insertSenderMail(MailVO mailVO);
	public int insertRecipMail(MailVO mailVO);
	public int insertCcMail(MailVO mailVO);
	
	//메일 임시보관 등록
	public int insertDraftMail(MailVO mailVO);
	
	//임시보관함 메일 수정
	public int updateDraftMail(MailVO mailVO);
	
	//휴지통
	public int updateTrashMail(List<Integer> mailIds);
	public int updateTrashMailInfo(int mailId);
	
	//삭제
	public int deleteDraftMail(List<Integer> mailIds);
	public int deleteDraftMailInfo(int mailId);
	
	//복구
	public int updateRestoreMail(MailVO mailVO);
	
	
	
}
