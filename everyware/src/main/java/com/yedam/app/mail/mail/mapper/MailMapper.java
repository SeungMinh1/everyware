package com.yedam.app.mail.mail.mapper;

import java.util.List;

import com.yedam.app.mail.mail.service.MailVO;

public interface MailMapper {
	//단건 메일함
	public List<MailVO> selectMailboxInfo(MailVO mailVO, int empId);
	
	//메일 단건조회
	public MailVO selectMailInfo(MailVO mailVO);
	
	//메일등록 -> 보낸사람 -> 보낸메일함
	//       -> 받는사람 -> 받은메일함
	//		 -> 참조    -> 받은메일함
	public int insertSenderMail(MailVO mailVO);
	public int insertRecipMail(MailVO mailVO);
	public int insertCcMail(MailVO mailVO);
	
	public int selectEmpId(String mail);
	public String selectEmail(int empId);
	
	//메일 임시보관 등록
	public int insertDraftMail(MailVO mailVO);
	// 임시보관한 메일 수정
	public int updateDraftMail(MailVO mailVO);
	
	//삭제
	public int deleteDraftMail(int mailId);
}
