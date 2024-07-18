package com.yedam.app.attend.emp.mapper;

import java.util.List;

import com.yedam.app.attach.service.FileVO;
import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.common.service.CommonVO;

public interface EmpMapper {
	
	//전체조회
	public List<EmpVO> selectEmpAll(int page, int cnt, String dosearch, String searchOption);
	
	// 단건조회
	public EmpVO selectEmpInfo(EmpVO empVO);
	
	// 등록
	public int insertEmpInfo(EmpVO empVO);
	
	// 수정
	public int updateEmpInfo(EmpVO empVO);
	
	// 삭제
	public int deleteEmpInfo(int empId);
	
	//emp_id 조회
	public int selectEmpId();
	
	//직위 조회
	public List<CommonVO> selectPosition();
	
	//부서 조회
	public List<CommonVO> selectDepartment();
	
	//비밀번호 초기화
	public int resetPwd(EmpVO empVO);
	
	//리스트개수
	public int countList(String dosearch, String searchOption);
	public int countList2();

	//생산팀 사원 출력
	public List<EmpVO> prodEmpList ();
	
	//사진찾기
	public FileVO selectPhoto(EmpVO empVO);
	
	//사진찾기위해 데이터찾기
	public EmpVO searchPhoto(EmpVO empVO);
	
	//아이디 중복체크
	public int checkId(EmpVO empVO);
}
