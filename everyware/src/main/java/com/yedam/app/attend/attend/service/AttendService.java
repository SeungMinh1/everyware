package com.yedam.app.attend.attend.service;

import java.util.List;

import com.yedam.app.attend.emp.service.EmpVO;

public interface AttendService {
	//출근
	public int gowork(AttendVO attendVO);
	
	//퇴근
	public int endwork(AttendVO attendVO);
	
	//퇴근2
	public int endworkExWork(AttendVO attendVO);

	//조회
	public List<AttendVO> selectAttendAll(AttendVO attendVO);
	
	//단건조회
	public AttendVO selectAttend(AttendVO attendVO);
	
	//근무기록조회
	public int countAttend(AttendVO attendV);
	
	//근무누적
	public AttendVO countWorkTime(AttendVO attendVO);
	
	//근무누적
	public AttendVO countWorkTime2(int empId, int mon);
	
	//날짜로 근무정보조회
	public AttendVO selectDateAttend(AttendVO attendVO);
	
	//근무 지각처리
	public int checkWokrLate(AttendVO attendVO);
	
	//주차확인
	public List<WeekVO> findWeeks(int months);
	
	//전사원 주차별 누적 근무시간
	public List<EmpVO> AllWorkTime(WeekVO weekVO);
	
	//전사원 주차별 누적 근무시간
	public List<EmpVO> AllOverWorkTime(WeekVO weekVO);
	
	//부서별 직원 근태관리
	public List<EmpVO> selectdeptAttend(String departmentName, String newdate);
	
	//이번달 사원 근무기록 리스트
	public List<AttendVO> selectMonthList(AttendVO attendVO);
	
}
