package com.yedam.app.dataroom.file.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.dataroom.file.service.DataFileService;
import com.yedam.app.dataroom.file.service.DataFileVO;
import com.yedam.app.mail.mail.service.MailVO;

@Controller
public class DataFileController {
	@Autowired
    private DataFileService dataFileService;

	//첨부파일 등록
	@PostMapping(value = "/uploadAjaxData", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DataFileVO> uploadFile(@RequestPart MultipartFile[] uploadFile) {
        return dataFileService.uploadDataFiles(uploadFile);
    }
	
	//첨부파일 다운로드
	@GetMapping(value="/downloadData", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName){
		return dataFileService.downlodeDataFile(fileName);
	}
	
	//첨부파일 삭제
	@PostMapping("/deleteDataFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String Type) throws UnsupportedEncodingException{
		return dataFileService.deleteDataFile(fileName, Type);
	}
	
	//첨부파일 단건
	@PostMapping("selectDataFileInfo")
	@ResponseBody
	public DataFileVO selectFileInfo(@RequestBody DataFileVO dfileVO) {
		return dataFileService.selectDataFileInfo(dfileVO);
	}
	
	//--메일--
	//첨부파일 여러개 
	@PostMapping("selectFileByMailId")
	@ResponseBody
	public List<DataFileVO> selectFileByMailId(@RequestBody MailVO mailVO) {
		return dataFileService.selectFileByMailId(mailVO);
	}
}
