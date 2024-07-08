package com.yedam.app.attend.attend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.attend.attend.mapper.AttendMapper;
import com.yedam.app.attend.attend.service.AttendService;
import com.yedam.app.attend.attend.service.AttendVO;
import com.yedam.app.attend.attend.service.WeekVO;
import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.common.util.AuthUtil;
@Service
public class AttendServiceImpl implements AttendService {

	@Autowired
	AttendMapper attendMapper;
	
	@Override
	public int gowork(AttendVO attendVO) {
		return attendMapper.gowork(attendVO);
	}
	@Override
	public int endwork(AttendVO attendVO) {
		return attendMapper.endwork(attendVO);
	}
	@Override
	public int endworkExWork(AttendVO attendVO) {
		if(!AuthUtil.getDepartmentId().equals("g3") ) { // 현장직 제외
			if(attendVO.getWorkTime() < 9*60) {
				attendVO.setExceedWorkTime(0);
			}else {
				int extratime = (attendVO.getWorkTime() - 9*60);
				attendVO.setExceedWorkTime(extratime);
			}
			
		}
		return attendMapper.endworkExWork(attendVO);
	}

	@Override
	public List<AttendVO> selectAttendAll(AttendVO attendVO) {
		return attendMapper.selectAttendList(attendVO);
	}


	@Override
	public AttendVO selectAttend(AttendVO attendVO) {
		return attendMapper.selectAttend(attendVO);
	}
	@Override
	public int countAttend(AttendVO attendV) {
		return attendMapper.countAttend(attendV);
	}
	@Override
	public AttendVO countWorkTime(AttendVO attendVO) { //누적근무시간
		AttendVO result = new AttendVO();
		
		//이번주
		int week = 0; 
		int totalWork = 0;
		int totalExtraWork = 0;
		List<AttendVO> list1 = attendMapper.countWorkTime(attendVO, week); //이번주 근무기록
		for(int i=0; i< list1.size(); i++) {
			totalWork += list1.get(i).getWorkTime(); //이번주 누적 근무기록
			totalExtraWork += list1.get(i).getExceedWorkTime(); //이번주 누적 초과근무기록
		}
		result.setExtrawork1(totalExtraWork);
		result.setTotalwork1(totalWork);
		
		//지난주
//		week =7;
//		totalWork = 0;
//		totalExtraWork = 0;
//		List<AttendVO> list2 = attendMapper.countWorkTime(attendVO, week); //저번주 근무기록
//		for(int i=0; i< list2.size(); i++) {
//			totalWork += list2.get(i).getWorkTime(); //저번주 누적 근무기록
//			totalExtraWork += list2.get(i).getExceedWorkTime(); //저번주 누적 초과근무기록
//		}
//		result.setExtrawork2(totalExtraWork);
//		result.setTotalwork2(totalWork);
		
		return result;
	}
	@Override
	public AttendVO selectDateAttend(AttendVO attendVO) {
		return attendMapper.dateAttend(attendVO);
	}
	@Override
	public int checkWokrLate(AttendVO attendVO) {
		if(AuthUtil.getDepartmentId() != "g3") {
			AttendVO findA = attendMapper.selectAttend(attendVO);
			Date startworkTime = findA.getGoTime();
			System.out.println(startworkTime.getHours());
			if(startworkTime.getHours() >= 9 && startworkTime.getMinutes() > 0) {
				findA.setWorkdetail("late");
			}else {
				findA.setWorkdetail("normal");
			}
			return attendMapper.checkWokrLate(findA);
		}
		attendVO.setWorkdetail("work");
		
		return attendMapper.checkWokrLate(attendVO);
	}
	@Override
	public List<WeekVO> findWeeks(int months) {
		months = months * 30;
		return attendMapper.findWeeks(months);
	}
	@Override
	public List<EmpVO> AllWorkTime(WeekVO weekVO) {
		return attendMapper.selectAllWorkTime(weekVO);
	}
	@Override
	public AttendVO countWorkTime2(int empId, int mon) {
		return attendMapper.countWorkTime2(empId, mon);
	}
	@Override
	public List<EmpVO> AllOverWorkTime(WeekVO weekVO) {
		return attendMapper.selectAllOverWorkTime(weekVO);
	}
	@Override
	public List<EmpVO> selectdeptAttend(String departmentName,String newdate) {
		return attendMapper.deptAttend(departmentName, newdate);
	}
	@Override
	public List<AttendVO> selectMonthList(AttendVO attendVO) {
		return attendMapper.selectMonthList(attendVO);
	}


}
