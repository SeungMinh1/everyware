package com.yedam.app.attend.attend.service;

import java.util.List;

public interface AttendService {
	
	public int gowork(AttendVO attendVO);
	
	//퇴근
	public int endwork(AttendVO attendVO);

	//조회
	public List<AttendVO> selectAttendAll(AttendVO attendVO);
	
	public AttendVO selectAttend(AttendVO attendVO);
}
