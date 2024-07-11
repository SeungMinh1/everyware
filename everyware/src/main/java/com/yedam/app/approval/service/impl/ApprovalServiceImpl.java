package com.yedam.app.approval.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.approval.mapper.ApprovalMapper;
import com.yedam.app.approval.mapper.DocMapper;
import com.yedam.app.approval.service.ApprovalService;
import com.yedam.app.approval.service.ApprovalVO;
import com.yedam.app.approval.service.ReceptionVO;
import com.yedam.app.approval.service.RefVO;
import com.yedam.app.approval.service.SendVO;
import com.yedam.app.approval.service.ViewVO;

@Service
public class ApprovalServiceImpl implements ApprovalService {
	@Autowired
	ApprovalMapper approvalMapper;
	
	@Autowired
	DocMapper docMapper;
	
	// 결재자 등록
	@Override
	public int approvalInsert(ApprovalVO approvalVO) {
		int result = 0;

		List<ApprovalVO> list = approvalVO.getApprovalEmpList();
		for(ApprovalVO approval : list) {
			int order = docMapper.posCode(approval.getApprovalPosition());
			approval.setApprovalEmpOrder(order);
		}
				
		// 결재자 순서
		if(approvalVO.getApprovalOrder().equals("ser")) {
			for(ApprovalVO approval : list) {
				approval.setApprovalType("결재예정");
			}
			list.get(list.size()-1).setApprovalType("결재대기");
		} else {
			for(ApprovalVO approval : list) {
				approval.setApprovalType("결재대기");
			}
		}
		
		for(ApprovalVO approval : list) {
			result += approvalMapper.approvalInsert(approval);
		}
		return result;
	}
	
	// 수신자 등록
	@Override
	public int receptionInsert(ReceptionVO receptionVO) {
		int result = 0;
		
		List<ReceptionVO> list = receptionVO.getReceptionEmpList();
		for(ReceptionVO reception : list) {
			reception.setReceptionStatus("접수대기");
		}
		
		for(ReceptionVO reception : list) {
			result += approvalMapper.receptionInsert(reception);
		}
		
		return result;
	}
	
	// 발송자 등록
	@Override
	public int sendInsert(SendVO sendVO) {
		int result = 0;
		
		List<SendVO> list = sendVO.getSendEmpList();
		for(SendVO send : list) {
			send.setSendStatus("접수대기");
		}
		
		for(SendVO send : list) {
			result += approvalMapper.sendInsert(send);
		}
		
		return result;
	}

	// 참조자 등록
	@Override
	public int refInsert(RefVO refVO) {
		int result = 0;
		
		List<RefVO> list = refVO.getRefEmpList();
		for(RefVO ref : list) {
			ref.setRefStatus("참조대기");
		}
		
		for(RefVO ref : list) {
			result += approvalMapper.refInsert(ref);
		}
		
		return result;
	}

	// 열람자 등록
	@Override
	public int viewInsert(ViewVO viewVO) {
		int result = 0;
		
		List<ViewVO> list = viewVO.getViewEmpList();
		for(ViewVO view : list) {
			view.setViewStatus("열람대기");
		}
		
		for(ViewVO view : list) {
			result += approvalMapper.viewInsert(view);
		}
		
		return result;
	}
	
	// 수정(승인)
	@Override
	public Map<String, Object> approvalUpdate(ApprovalVO approvalVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = approvalMapper.approvalUpdate(approvalVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", approvalVO);
		return map;
	}
	
	// 수정(승인)(여러개)
	@Override
	public int approvalUpdateAll(ApprovalVO approvalVO) {
		int result = 0;
		
		List<ApprovalVO> list = approvalVO.getApprovalList();
		for(ApprovalVO app : list) {
			result += approvalMapper.approvalUpdateAll(app);
		}
		
		return result;
	}
	
	// 수정(반려)
	@Override
	public Map<String, Object> rejectUpdate(ApprovalVO approvalVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = approvalMapper.rejectUpdate(approvalVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", approvalVO);
		return map;
	}
	
	// 수정(다음 결재자)
	@Override
	public Map<String, Object> nextEmpUpdate(ApprovalVO approvalVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = approvalMapper.nextEmpUpdate(approvalVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", approvalVO);
		return map;
	}

	@Override
	public int approvalDelete(ApprovalVO approvalVO) {
		int result = 0;
		
		List<Integer> list = approvalVO.getApprovalIdList();
		for(int id : list) {
			approvalVO.setApprovalEmpId(id);
			result += approvalMapper.approvalDelete(approvalVO);
		}
		
		return result;
	}

	@Override
	public int refUpdate(RefVO refVO) {
		int result = 0;
		
		List<RefVO> list = refVO.getRefViewList();
		for(RefVO app : list) {
			result += approvalMapper.refUpdate(app);
		}
		
		return result;
	}

	@Override
	public int viewUpdate(ViewVO viewVO) {
		int result = 0;
		
		List<ViewVO> list = viewVO.getRefViewList();
		for(ViewVO app : list) {
			result += approvalMapper.viewUpdate(app);
		}
		
		return result;
	}

	@Override
	public Map<String, Object> receptionUpdate(ReceptionVO receptionVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = approvalMapper.receptionUpdate(receptionVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", receptionVO);
		return map;
	}

	@Override
	public Map<String, Object> sendUpdate(SendVO sendVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = approvalMapper.sendUpdate(sendVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", sendVO);
		return map;
	}	
	
}
