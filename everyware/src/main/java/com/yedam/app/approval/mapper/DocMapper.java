package com.yedam.app.approval.mapper;

import java.util.List;

import com.yedam.app.approval.service.DocVO;
import com.yedam.app.approval.service.TaskVO;
import com.yedam.app.attend.emp.service.EmpVO;

public interface DocMapper {
	// 문서조회
	// 결재 대기 문서
	public List<DocVO> waitDocList(int id);

	// 참조/열람 대기 문서
	public List<DocVO> waitRefViewDocList(int id);

	// 결재 예정 문서
	public List<DocVO> expectedDocList(int id);

	// 기안 문서
	public List<DocVO> draftDocList(int id);
	public List<DocVO> goDraftDocList(int id);
	public List<DocVO> rejDraftDocList(int id);
	public List<DocVO> compDraftDocList(int id);

	// 결재 문서
	public List<DocVO> approvalDocList(int id);
	public List<DocVO> goApprovalDocList(int id);
	public List<DocVO> rejApprovalDocList(int id);
	public List<DocVO> compApprovalDocList(int id);

	// 발송 문서
	public List<DocVO> sendDocList(int id);
	public List<DocVO> waitSendDocList(int id);
	public List<DocVO> recSendDocList(int id);
	public List<DocVO> goSendDocList(int id);
	public List<DocVO> compSendDocList(int id);
	public List<DocVO> rejSendDocList(int id);
	public List<DocVO> retSendDocList(int id);

	// 수신 문서
	public List<DocVO> receptionDocList(int id);
	public List<DocVO> waitReceptionDocList(int id);
	public List<DocVO> recReceptionDocList(int id);
	public List<DocVO> goReceptionDocList(int id);
	public List<DocVO> compReceptionDocList(int id);
	public List<DocVO> rejReceptionDocList(int id);
	public List<DocVO> retReceptionDocList(int id);

	// 참조/열람 문서
	public List<DocVO> refViewDocList(int id);

	// 참조 문서
	public List<DocVO> refDocList(int id);

	// 열람 문서
	public List<DocVO> viewDocList(int id);

	// 임시 문서
	public List<DocVO> temporaryDocList(int id);
	
	// 개별 문서 조회
	public DocVO docInfo(DocVO docVO);
	
	// 문서등록(양식)
	public TaskVO newTask(TaskVO taskVO);
	public List<TaskVO> category();
	public List<TaskVO> task(String category);
	
	// 문서등록
	public int docInsert(DocVO docVO);
	
	// 임시저장
	public int tempInsert(DocVO docVO);

	// 문서수정
	public int docUpdate(DocVO docVO);
	
	// 문서 임시저장 수정
	public int tempDocUpdate(DocVO docVO);

	// 문서삭제
	public int docDelete(List<Integer> docId);
	
	// 문서삭제(단건)
	public int docInfoDelete(int docId);
	
	// 전체 부서 목록
	public List<EmpVO> allDept();
	
	// 부서별 사원 정보
	public List<EmpVO> deptEmp(String departmentId);
	
	// 사원 정보
	public EmpVO empInfo(int id);
	
	// 직위 코드
	public int posCode(String codeName);
	
	// 결재 수정
	public int approvalDocUpdate(DocVO docVO);
}
