package com.yedam.app.attach.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.attach.service.FileService;
import com.yedam.app.attach.service.FileVO;

@Controller
public class FileController {
	@Autowired
    private FileService fileService;
	
	/*   /fileTest 가서 test 가능   */
	
	@GetMapping("fileTest")
	public String fileTest() {
		return "attach/fileUpload";
	}

	//첨부파일 등록
	@PostMapping(value = "/uploadAjax", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<FileVO> uploadFile(@RequestPart MultipartFile[] uploadFile) {
        return fileService.uploadFiles(uploadFile);
    }
	
	//첨부파일 다운로드
	@GetMapping(value="/download", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName){
		return fileService.downlodeFile(fileName);
	}
	
	//첨부파일 삭제
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String Type) throws UnsupportedEncodingException{
		return fileService.deleteFile(fileName, Type);
	}
	
	//첨부파일 단건
	@PostMapping("selectFileInfo")
	@ResponseBody
	public FileVO selectFileInfo(@RequestBody FileVO fileVO) {
		return fileService.selectFileInfo(fileVO);
	}
}
