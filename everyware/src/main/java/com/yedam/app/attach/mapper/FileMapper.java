package com.yedam.app.attach.mapper;

import com.yedam.app.attach.service.FileVO;
import com.yedam.app.dataroom.service.DataVO;

public interface FileMapper {
	 
	//--- 공통 ---
	public int insertFile(FileVO fileVO);
	
	//--- 자료실 ---
	//파일첨부를 여러번 할 때 groupId를 같은 id로 업데이트
	public int updateGroupId(DataVO dataVO);
	
}
