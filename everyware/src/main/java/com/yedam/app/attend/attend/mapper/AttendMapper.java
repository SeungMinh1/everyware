package com.yedam.app.attend.attend.mapper;

import java.util.List;

import com.yedam.app.attend.attend.service.AttendVO;
import com.yedam.app.attend.attend.service.WeekVO;
import com.yedam.app.attend.emp.service.EmpVO;

public interface AttendMapper {
	//거리측정
	public double findDistance(double xx, double yy);
	
	//출근
	public int gowork(AttendVO attendVO);
	
	//퇴근
	public int endwork(AttendVO attendVO);
	
	//조회
	public List<AttendVO> selectAttendList(AttendVO attendVO);
	
	//단건조회
	public AttendVO selectAttend(AttendVO attendVO);
	
	//근무기록조회(개수)
	public int countAttend(AttendVO attendV);
	
	//근무시간
	public List<AttendVO> countWorkTime(AttendVO attendVO, int week);
	
	// 한달 기준 누적근무시간
	public AttendVO countWorkTime2(int empId, int mon);
	
	//날짜로 근무정보조회
	public AttendVO dateAttend(AttendVO attendVO);
	
	//지각확인
	public int checkWokrLate(AttendVO attendV);
	
	//주차확인
	public List<WeekVO> findWeeks(int months);
	
	//전체사원 누적 근무시간 조회
	public List<EmpVO> selectAllWorkTime(WeekVO weekVO);
	
	
}
