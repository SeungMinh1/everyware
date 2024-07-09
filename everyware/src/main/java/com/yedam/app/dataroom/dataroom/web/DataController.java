package com.yedam.app.dataroom.dataroom.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.common.util.AuthUtil;
import com.yedam.app.dataroom.dataroom.service.DataService;
import com.yedam.app.dataroom.dataroom.service.DataVO;
import com.yedam.app.dataroom.file.service.DataFileService;

@Controller
public class DataController {
	
	@Autowired
	DataService dataService;
	@Autowired
	DataFileService dataFileService;
	
	//자료실 페이지 홈 
	@GetMapping("dataroom")
	public String dataroom() {
		return "dataroom/dataroom";
	}
	
	//공통자료실
	@GetMapping("dataCommon")
	public String dataCommon(DataVO dataVO, Model model) {
		dataVO.setRemarks("공통");
		
		List<DataVO> find = dataService.dataListCommon(dataVO);
		model.addAttribute("datas", find);
		return "dataroom/dataroom";
	}
	
	// 부서별 자료실
	@GetMapping("dataDept")
	public String dataDept(DataVO dataVO, Model model) {
		Integer empId = AuthUtil.getEmpId();
		String deptId = dataService.selectDeptId(empId);
		dataVO.setDepartmentId(deptId);
		dataVO.setRemarks("부서");

		List<DataVO> find = dataService.dataListDept(dataVO);
		model.addAttribute("datas", find);
		return "dataroom/dataroom";
	}
	
	// 개인 자료실
	@GetMapping("dataMe")
	public String dataMe(DataVO dataVO, Model model) {
		Integer empId = AuthUtil.getEmpId();
		dataVO.setEmpId(empId);
		dataVO.setRemarks("개인");
		
		List<DataVO> find = dataService.dataListMe(dataVO);
		model.addAttribute("datas", find);
		return "dataroom/dataroom";
	}
	/*
	// 개인 자료실 AJAX
	@GetMapping("dataMe")
	@ResponseBody
	public List<DataVO> dataMe(DataVO dataVO, Model model) {
		Integer empId = AuthUtil.getEmpId();
		dataVO.setEmpId(empId);
		dataVO.setRemarks("개인");
		
		//List<DataVO> find = dataService.dataListMe(dataVO);
		//model.addAttribute("datas", find);
		return  dataService.dataListMe(dataVO);
	}
	*/
	
	//자료등록
	@PostMapping("insertData")
	@ResponseBody
	public int insertData(@RequestBody DataVO dataVO) {
		Integer empId = AuthUtil.getEmpId();
		dataVO.setEmpId(empId);
		
		String deptId = dataService.selectDeptId(empId);
		dataVO.setDepartmentId(deptId);
		
		return dataService.insertData(dataVO);
	}
	
	//자료조회
	@GetMapping("selectData")
	public String selectData(DataVO dataVO, Model model) {
		List<DataVO> dvo = dataService.selectDataInfo(dataVO);
		model.addAttribute("datas", dvo);
		return "dataroom/data_info";
	}
	
	//자료삭제(여러개)
	@PostMapping("deleteData")
	@ResponseBody
	public Map<String, Object> deleteData(@RequestBody List<Integer> dataId) {
		return dataService.deleteData(dataId);
	}
	
	//자료 여러개 삭제시 그 그룹아이디를 가진 파일 전체 삭제
	@PostMapping("deleteGroupIdFiles")
	@ResponseBody
	public Map<String, Object> deleteFiles(@RequestBody List<Integer> dataIds) {
		return dataService.deleteFiles(dataIds);
	}
}
