package com.yedam.app.attach.mapper;

import com.yedam.app.attach.service.FileVO;

public interface FileMapper {
	 
	//--- 공통 ---
	public int insertFile(FileVO fileVO);
	public FileVO selectFileInfo(FileVO fileVO);
	
}
