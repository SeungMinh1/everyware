package com.yedam.app.approval.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.approval.mapper.DocMapper;
import com.yedam.app.approval.service.DocService;
import com.yedam.app.approval.service.DocVO;
import com.yedam.app.approval.service.TaskVO;
import com.yedam.app.attend.emp.service.EmpVO;

@Service
public class DocServiceImpl implements DocService {
	@Autowired
	DocMapper docMapper;

	@Override
	public  List<DocVO> waitDocList(int id) {
		
		return docMapper.waitDocList(id);
	}

	@Override
	public  List<DocVO> waitRefViewDocList(int id) {
		
		return docMapper.waitRefViewDocList(id);
	}

	@Override
	public  List<DocVO> expectedDocList(int id) {
		
		return docMapper.expectedDocList(id);
	}

	@Override
	public  List<DocVO> draftDocList(int id) {
		
		return docMapper.draftDocList(id);
	}
	
	@Override
	public  List<DocVO> approvalDocList(int id) {
		
		return docMapper.approvalDocList(id);
	}

	@Override
	public  List<DocVO> sendDocList(int id) {
		
		return docMapper.sendDocList(id);
	}

	@Override
	public  List<DocVO> receptionDocList(int id) {
		
		return docMapper.receptionDocList(id);
	}

	@Override
	public  List<DocVO> refViewDocList(int id) {
		
		return docMapper.refViewDocList(id);
	}

	@Override
	public  List<DocVO> refDocList(int id) {
		
		return docMapper.refDocList(id);
	}

	@Override
	public  List<DocVO> viewDocList(int id) {
		
		return docMapper.viewDocList(id);
	}

	@Override
	public  List<DocVO> temporaryDocList(int id) {
		
		return docMapper.temporaryDocList(id);
	}
	
	@Override
	public DocVO docInfo(DocVO docVO) {
		return docMapper.docInfo(docVO);
	}

	@Override
	public TaskVO newTask(TaskVO taskVO) {
		return docMapper.newTask(taskVO);
	}

	@Override
	public List<TaskVO> category() {
		List<TaskVO> list = docMapper.category();
		
		for(int i=0; i<list.size(); i++) {
			list.get(i).setTaskList(docMapper.task(list.get(i).getCategory()));
		}
		
		return list;
	}

	@Override
	public List<TaskVO> task(String approvalTask) {
		return docMapper.task(approvalTask);
	}
	
	// 문서 등록
	@Override
	public int docInsert(DocVO docVO) {
		int result = 0;
		
		// 결재자
		String appEmps = "";
		for(String appEmp : docVO.getApprovalNameList()) {
			appEmps += appEmp + ",";
		}
		docVO.setApprovalEmp(appEmps);
		
		// 결재자 id
		String appEmpIds = "";
		for(String appEmpId : docVO.getApprovalIdList()) {
			appEmpIds += appEmpId + ",";
		}
		docVO.setApprovalEmpId(appEmpIds);
		
		// 수신자
		String recEmps = "";
		for(String recEmp : docVO.getReceptionNameList()) {
			recEmps += recEmp + ",";
		}
		docVO.setReceptionEmp(recEmps);
		
		// 수신자 id
		String recEmpIds = "";
		for(String recEmpId : docVO.getReceptionIdList()) {
			recEmpIds += recEmpId + ",";
		}
		docVO.setReceptionEmpId(recEmpIds);
		
		// 참조자
		String refEmps = "";
		for(String refEmp : docVO.getRefNameList()) {
			refEmps += refEmp + ",";
		}
		docVO.setRefEmp(refEmps);
		
		// 참조자 id
		String refEmpIds = "";
		for(String refEmpId : docVO.getRefIdList()) {
			refEmpIds += refEmpId + ",";
		}
		docVO.setRefEmpId(refEmpIds);
		
		// 열람자
		String viewEmps = "";
		for(String viewEmp : docVO.getViewNameList()) {
			viewEmps += viewEmp + ",";
		}
		docVO.setViewEmp(viewEmps);
		
		// 열람자 id
		String viewEmpIds = "";
		for(String viewEmpId : docVO.getViewIdList()) {
			viewEmpIds += viewEmpId + ",";
		}
		docVO.setViewEmpId(viewEmpIds);
		
		// 발송자
		String sendEmps = "";
		for(String sendEmp : docVO.getSendNameList()) {
			sendEmps += sendEmp + ",";
		}
		docVO.setSendEmp(sendEmps);
		
		// 발송자 id
		String sendEmpIds = "";
		for(String sendEmpId : docVO.getSendIdList()) {
			sendEmpIds += sendEmpId + ",";
		}
		docVO.setSendEmpId(sendEmpIds);
		
		// 파일업로드
		String fileNames = "";
		for(String fileName : docVO.getApprovalFileList()) {
			fileNames += fileName + ",";
		}
		docVO.setApprovalFile(fileNames);
		
		result = docMapper.docInsert(docVO);
		return result;
	}
	
	// 문서 임시저장
	@Override
	public int tempInsert(DocVO docVO) {
		int result = 0;
		
		// 결재자
		String appEmps = "";
		for (String appEmp : docVO.getApprovalNameList()) {
			appEmps += appEmp + ",";
		}
		docVO.setApprovalEmp(appEmps);

		// 결재자 id
		String appEmpIds = "";
		for (String appEmpId : docVO.getApprovalIdList()) {
			appEmpIds += appEmpId + ",";
		}
		docVO.setApprovalEmpId(appEmpIds);

		// 수신자
		String recEmps = "";
		for (String recEmp : docVO.getReceptionNameList()) {
			recEmps += recEmp + ",";
		}
		docVO.setReceptionEmp(recEmps);

		// 수신자 id
		String recEmpIds = "";
		for (String recEmpId : docVO.getReceptionIdList()) {
			recEmpIds += recEmpId + ",";
		}
		docVO.setReceptionEmpId(recEmpIds);

		// 참조자
		String refEmps = "";
		for (String refEmp : docVO.getRefNameList()) {
			refEmps += refEmp + ",";
		}
		docVO.setRefEmp(refEmps);

		// 참조자 id
		String refEmpIds = "";
		for (String refEmpId : docVO.getRefIdList()) {
			refEmpIds += refEmpId + ",";
		}
		docVO.setRefEmpId(refEmpIds);

		// 열람자
		String viewEmps = "";
		for (String viewEmp : docVO.getViewNameList()) {
			viewEmps += viewEmp + ",";
		}
		docVO.setViewEmp(viewEmps);

		// 열람자 id
		String viewEmpIds = "";
		for (String viewEmpId : docVO.getViewIdList()) {
			viewEmpIds += viewEmpId + ",";
		}
		docVO.setViewEmpId(viewEmpIds);

		// 발송자
		String sendEmps = "";
		for (String sendEmp : docVO.getSendNameList()) {
			sendEmps += sendEmp + ",";
		}
		docVO.setSendEmp(sendEmps);

		// 발송자 id
		String sendEmpIds = "";
		for (String sendEmpId : docVO.getSendIdList()) {
			sendEmpIds += sendEmpId + ",";
		}
		docVO.setSendEmpId(sendEmpIds);
		
		result = docMapper.tempInsert(docVO);
		return result;
	}

	// 문서 수정
	@Override
	public Map<String, Object> docUpdate(DocVO docVO) {
		Map<String, Object> map = new HashMap<>();
		
		// 결재자
		String appEmps = "";
		for (String appEmp : docVO.getApprovalNameList()) {
			appEmps += appEmp + ",";
		}
		docVO.setApprovalEmp(appEmps);

		// 결재자 id
		String appEmpIds = "";
		for (String appEmpId : docVO.getApprovalIdList()) {
			appEmpIds += appEmpId + ",";
		}
		docVO.setApprovalEmpId(appEmpIds);

		// 수신자
		String recEmps = "";
		for (String recEmp : docVO.getReceptionNameList()) {
			recEmps += recEmp + ",";
		}
		docVO.setReceptionEmp(recEmps);

		// 수신자 id
		String recEmpIds = "";
		for (String recEmpId : docVO.getReceptionIdList()) {
			recEmpIds += recEmpId + ",";
		}
		docVO.setReceptionEmpId(recEmpIds);

		// 참조자
		String refEmps = "";
		for (String refEmp : docVO.getRefNameList()) {
			refEmps += refEmp + ",";
		}
		docVO.setRefEmp(refEmps);

		// 참조자 id
		String refEmpIds = "";
		for (String refEmpId : docVO.getRefIdList()) {
			refEmpIds += refEmpId + ",";
		}
		docVO.setRefEmpId(refEmpIds);

		// 열람자
		String viewEmps = "";
		for (String viewEmp : docVO.getViewNameList()) {
			viewEmps += viewEmp + ",";
		}
		docVO.setViewEmp(viewEmps);

		// 열람자 id
		String viewEmpIds = "";
		for (String viewEmpId : docVO.getViewIdList()) {
			viewEmpIds += viewEmpId + ",";
		}
		docVO.setViewEmpId(viewEmpIds);

		// 발송자
		String sendEmps = "";
		for (String sendEmp : docVO.getSendNameList()) {
			sendEmps += sendEmp + ",";
		}
		docVO.setSendEmp(sendEmps);

		// 발송자 id
		String sendEmpIds = "";
		for (String sendEmpId : docVO.getSendIdList()) {
			sendEmpIds += sendEmpId + ",";
		}
		docVO.setSendEmpId(sendEmpIds);
		
		boolean isSuccessed = false;
		int result = docMapper.docUpdate(docVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", docVO);
		return map;
	}
	
	// 문서 임시저장 수정
	@Override
	public Map<String, Object> tempDocUpdate(DocVO docVO) {
		Map<String, Object> map = new HashMap<>();
		
		// 결재자
		String appEmps = "";
		for (String appEmp : docVO.getApprovalNameList()) {
			appEmps += appEmp + ",";
		}
		docVO.setApprovalEmp(appEmps);

		// 결재자 id
		String appEmpIds = "";
		for (String appEmpId : docVO.getApprovalIdList()) {
			appEmpIds += appEmpId + ",";
		}
		docVO.setApprovalEmpId(appEmpIds);

		// 수신자
		String recEmps = "";
		for (String recEmp : docVO.getReceptionNameList()) {
			recEmps += recEmp + ",";
		}
		docVO.setReceptionEmp(recEmps);

		// 수신자 id
		String recEmpIds = "";
		for (String recEmpId : docVO.getReceptionIdList()) {
			recEmpIds += recEmpId + ",";
		}
		docVO.setReceptionEmpId(recEmpIds);

		// 참조자
		String refEmps = "";
		for (String refEmp : docVO.getRefNameList()) {
			refEmps += refEmp + ",";
		}
		docVO.setRefEmp(refEmps);

		// 참조자 id
		String refEmpIds = "";
		for (String refEmpId : docVO.getRefIdList()) {
			refEmpIds += refEmpId + ",";
		}
		docVO.setRefEmpId(refEmpIds);

		// 열람자
		String viewEmps = "";
		for (String viewEmp : docVO.getViewNameList()) {
			viewEmps += viewEmp + ",";
		}
		docVO.setViewEmp(viewEmps);

		// 열람자 id
		String viewEmpIds = "";
		for (String viewEmpId : docVO.getViewIdList()) {
			viewEmpIds += viewEmpId + ",";
		}
		docVO.setViewEmpId(viewEmpIds);

		// 발송자
		String sendEmps = "";
		for (String sendEmp : docVO.getSendNameList()) {
			sendEmps += sendEmp + ",";
		}
		docVO.setSendEmp(sendEmps);

		// 발송자 id
		String sendEmpIds = "";
		for (String sendEmpId : docVO.getSendIdList()) {
			sendEmpIds += sendEmpId + ",";
		}
		docVO.setSendEmpId(sendEmpIds);
		
		boolean isSuccessed = false;
		int result = docMapper.tempDocUpdate(docVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", docVO);
		return map;
	}

	@Override
	public Map<String, Object> docDelete(List<Integer> docId) {
		Map<String, Object> result = new HashMap<>();
		try {
			docMapper.docDelete(docId);
			result.put("result", true);
		} catch (Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	// 문서삭제(단건)
	@Override
	public int docInfoDelete(int docId) {
		return docMapper.docInfoDelete(docId);
	}

	@Override
	public List<EmpVO> allDept() {
		// 부서 목록
		List<EmpVO> list = docMapper.allDept();
		
		for(int i=0; i<list.size(); i++) {
			list.get(i).setEmpInfo(docMapper.deptEmp(list.get(i).getDepartmentId()));
		}
		
		return list;
	}

	@Override
	public List<EmpVO> deptEmp(String departmentId) {
		return docMapper.deptEmp(departmentId);
	}

	@Override
	public EmpVO empInfo(int id) {
		return docMapper.empInfo(id);
	}

	@Override
	public int posCode(String codeName) {
		return docMapper.posCode(codeName);
	}

	// 결재 수정
	@Override
	public Map<String, Object> approvalDocUpdate(DocVO docVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = docMapper.approvalDocUpdate(docVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", docVO);
		return map;
	}

	@Override
	public List<DocVO> goDraftDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.goDraftDocList(id);
	}

	@Override
	public List<DocVO> rejDraftDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.rejDraftDocList(id);
	}

	@Override
	public List<DocVO> compDraftDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.compDraftDocList(id);
	}

	@Override
	public List<DocVO> goApprovalDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.goApprovalDocList(id);
	}

	@Override
	public List<DocVO> rejApprovalDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.rejApprovalDocList(id);
	}

	@Override
	public List<DocVO> compApprovalDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.compApprovalDocList(id);
	}

	@Override
	public List<DocVO> waitSendDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.waitSendDocList(id);
	}

	@Override
	public List<DocVO> recSendDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.recSendDocList(id);
	}

	@Override
	public List<DocVO> goSendDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.goSendDocList(id);
	}

	@Override
	public List<DocVO> compSendDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.compSendDocList(id);
	}

	@Override
	public List<DocVO> rejSendDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.rejSendDocList(id);
	}

	@Override
	public List<DocVO> retSendDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.retSendDocList(id);
	}

	@Override
	public List<DocVO> waitReceptionDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.waitReceptionDocList(id);
	}

	@Override
	public List<DocVO> recReceptionDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.recReceptionDocList(id);
	}

	@Override
	public List<DocVO> goReceptionDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.goReceptionDocList(id);
	}

	@Override
	public List<DocVO> compReceptionDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.compReceptionDocList(id);
	}

	@Override
	public List<DocVO> rejReceptionDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.rejReceptionDocList(id);
	}

	@Override
	public List<DocVO> retReceptionDocList(int id) {
		// TODO Auto-generated method stub
		return docMapper.retReceptionDocList(id);
	}
	
	
	
}

