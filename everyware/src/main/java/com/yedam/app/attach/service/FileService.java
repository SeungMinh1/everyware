package com.yedam.app.attach.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	 //첨부파일 등록
	 List<FileVO> uploadFiles(MultipartFile[] uploadFile);
	
	 //첨부파일 다운로드
	 public ResponseEntity<Resource> downlodeFile(String fileName);
	 
	 //첨부파일 삭제
	 public ResponseEntity<String> deleteFile(String fileName, String Type) throws UnsupportedEncodingException;
	 
	 //첨부파일 단건
	 public FileVO selectFileInfo(FileVO fileVO);
}
