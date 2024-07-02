package com.yedam.app.attend.attend.mapper;

import java.util.List;

import com.yedam.app.attend.attend.service.AttendVO;

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
}
