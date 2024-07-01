package com.yedam.app.mail.mail.service;

import java.util.List;
import java.util.Map;

public interface MailService {
	//조회 : 단건 메일함
	public List<MailVO> mailboxInfo(MailVO mailVO, int empId);

	//조회 : 단건 메일
	public MailVO mailInfo(MailVO mailVO);
	
	//등록 : 메일작성 후 보내기 - 
	//보낸사람- 보낸메일함 / 받은사람(들) - 받은메일함 / 참조자(들) - 받은메일함
	public int senderMail(MailVO mailVO);
	
	//등록 : 메일작성 후 임시보관
	public int draftMail(MailVO mailVO);
	
	//수정 : 임시보관 메일 수정 - 임시보관에서 다시 임시보관누르면 수정된 값 저장
	public Map<String, Object> updateDraftMail(MailVO mailVO);

	//조회 : empId로 email 찾기
	public String emailSelect(int empId);
	
	//수정 : 휴지통으로 이동 (여러개 / mailIds의 메일함을 휴지통(d5)으로 수정)
	public Map<String, Object> moveTrashMail(List<Integer> mailIds);
	//수정 : 휴지통으로 이동 (단건)
	public Map<String, Object> moveTrashMailInfo(MailVO mailVO);
	
	//삭제 : 메일 완전 삭제 (여러개)
	public Map<String, Object> deleteMail(List<Integer> mailIds);
	//삭제 : 메일 완전 삭제 (단건)
	public int deleteMailInfo(int mailId);
	
	//복구 : 메일 복구
	public int moveRestoreMail(MailVO mailVO);

}

