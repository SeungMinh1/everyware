package com.yedam.app.attend.attend.service;

import java.util.List;

import com.yedam.app.attend.emp.service.EmpVO;

public interface AttendService {
	//출근
	public int gowork(AttendVO attendVO);
	
	//퇴근
	public int endwork(AttendVO attendVO);

	//조회
	public List<AttendVO> selectAttendAll(AttendVO attendVO);
	
	//단건조회
	public AttendVO selectAttend(AttendVO attendVO);
	
	//근무기록조회
	public int countAttend(AttendVO attendV);
	
	//근무누적
	public AttendVO countWorkTime(AttendVO attendVO);
	
	//날짜로 근무정보조회
	public AttendVO selectDateAttend(AttendVO attendVO);
	
	//근무 지각처리
	public int checkWokrLate(AttendVO attendVO);
	
	//주차확인
	public List<WeekVO> findWeeks();
	
	//전사원 주차별 누적 근무시간
	public List<EmpVO> AllWorkTime(WeekVO weekVO);
	
}
