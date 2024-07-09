package com.yedam.app.dataroom.file.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.attach.service.FileVO;
import com.yedam.app.mail.mail.service.MailVO;

public interface DataFileService {
	 //첨부파일 등록
	 List<DataFileVO> uploadDataFiles(MultipartFile[] uploadFile);
	
	 //첨부파일 다운로드
	 public ResponseEntity<Resource> downlodeDataFile(String fileName);
	 
	 //첨부파일 삭제
	 public ResponseEntity<String> deleteDataFile(String fileName, String Type) throws UnsupportedEncodingException;
	 
	 //첨부파일 단건
	 public DataFileVO selectDataFileInfo(DataFileVO dfileVO);
	 
	 
	 // --- 메일 ---
	 //mailId로 첨부파일 조회(DB)
	 public List<DataFileVO> selectFileByMailId(MailVO mailVO);
	 
	 //단건삭제(DB)
	 public int deleteDataFileInfo(int fileId);
}
