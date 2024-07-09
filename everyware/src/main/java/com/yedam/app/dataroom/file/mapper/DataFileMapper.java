package com.yedam.app.dataroom.file.mapper;

import java.util.List;

import com.yedam.app.dataroom.dataroom.service.DataVO;
import com.yedam.app.dataroom.file.service.DataFileVO;
import com.yedam.app.mail.mail.service.MailVO;

public interface DataFileMapper {
	
	//--- 자료실 ---
	public int insertDataFile(DataFileVO dfileVO);
	public DataFileVO selectDataFileInfo(DataFileVO dfileVO);
	//파일첨부를 여러번 할 때 groupId를 같은 id로 업데이트
	public int updateDataGroupId(DataVO dataVO);
		
	//--- 메일 ---
	public int updateMailGroupId(MailVO mailVO);
	public List<DataFileVO> selectFileByMailId(MailVO mailVO);
	
	//파일 단건 삭제(DB)
	public int deleteDataFileInfo(int fileId);
		
}
