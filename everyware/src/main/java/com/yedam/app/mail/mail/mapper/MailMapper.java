package com.yedam.app.mail.mail.mapper;

import java.util.List;
import java.util.Map;

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
	
	public int selectEmpId(String mail);  //mail로 empId 찾기
	public String selectEmail(int empId);  //empId로 email 찾기 
	public String selectRestore(int mailId);
	
	//메일 임시보관 등록
	public int insertDraftMail(MailVO mailVO);
	// 임시보관한 메일 수정
	public int updateDraftMail(MailVO mailVO);
	
	//휴지통: 여러개 이동
	public int updateTrashMail(List<Integer> mailIds);
	//휴지통: 단건 이동
	public int updateTrashMailInfo(MailVO mailVO);
	
	//여러개 삭제
	//단건 삭제
	public int deleteDraftMail(List<Integer> mailIds);
	public int deleteDraftMailInfo(int mailId);
	
	//여러개 복구
	//단건 복구
	public int updateRestoreMail(MailVO mailVO);
	
	
	
}
