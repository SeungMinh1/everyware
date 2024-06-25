package com.yedam.app.approval.mapper;

import java.util.List;

import com.yedam.app.approval.service.DocVO;

public interface DocMapper {
	// 문서조회
	// 결재 대기 문서
	public List<DocVO> waitDocList(int page, int cnt, String dosearch);

	// 참조/열람 대기 문서
	public List<DocVO> waitRefViewDocList(int page, int cnt, String dosearch);

	// 결재 예정 문서
	public List<DocVO> expectedDocList(int page, int cnt, String dosearch);

	// 기안 문서
	public List<DocVO> draftDocList(int page, int cnt, String dosearch);

	// 결재 문서
	public List<DocVO> approvalDocList(int page, int cnt, String dosearch);

	// 발송 문서
	public List<DocVO> sendDocList(int page, int cnt, String dosearch);

	// 수신 문서
	public List<DocVO> receptionDocList(int page, int cnt, String dosearch);

	// 참조/열람 문서
	public List<DocVO> refViewDocList(int page, int cnt, String dosearch);

	// 참조 문서
	public List<DocVO> refDocList(int page, int cnt, String dosearch);

	// 열람 문서
	public List<DocVO> viewDocList(int page, int cnt, String dosearch);

	// 임시 문서
	public List<DocVO> temporaryDocList(int page, int cnt, String dosearch);
	
	// 개별 문서 조회
	public DocVO docInfo(DocVO docVO);
	
	// 문서등록
	public int docInsert(DocVO docVO);

	// 문서수정
	public int docUpdate(DocVO docVO);

	// 문서삭제
	public int docDelete(int docId);
	
	//리스트 개수
	public int cntList();
}
