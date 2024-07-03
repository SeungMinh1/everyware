package com.yedam.app.attach.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.attach.service.FileService;
import com.yedam.app.attach.service.FileVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    //폴더 저장 경로
 	private String getForder() {
 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

 		Date date = new Date();
 		String str = sdf.format(date);
 		return str.replace("-", File.separator);
 	}
 	//첨부파일 등록
    @Override
    public List<FileVO> uploadFiles(MultipartFile[] uploadFile) {
        List<FileVO> list = new ArrayList<>();
        
        // 폴더 만들기
        String uploadFolder = "C:\\upload";
        String uploadFolderPath = getForder();
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info("upload path: " + uploadPath);
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
        for (MultipartFile multipartFile : uploadFile) {
        	FileVO fileVO = new FileVO();
        	
            log.info("------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());
			log.info("Upload ContentType : " + multipartFile.getContentType());

			//원본_파일_이름
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name : " + uploadFileName);
			//[ fileVO ] 원본파일이름
			fileVO.setOriginFileName(uploadFileName); 
			
			int nameindex = uploadFileName.indexOf('.');
			log.info("Upload File Name substring : " + uploadFileName.substring(0, nameindex));
			
			//UUID 생성
            UUID uuid = UUID.randomUUID(); 
            uploadFileName = uuid.toString() + "_" + uploadFileName;
            log.info("UUID 1 : " + uploadFileName);
            log.info("UUID 2 : " + uuid.toString());
        
            int index = uploadFileName.indexOf(".");
            log.info("contentType:" + uploadFileName.substring(index + 1));
            
            //fileVO에 담기
            fileVO.setUploadFileName(uuid.toString());   					  //UUID -> 업로드파일이름
            fileVO.setAttachmentType(multipartFile.getContentType());         //첨부파일종류
            fileVO.setFileSize(multipartFile.getSize());				      //파일 사이즈
            fileVO.setUploadPath(uploadFolderPath); 						  //업로드 경로	
            try {
            	File saveFile = new File(uploadPath, uploadFileName);
            	multipartFile.transferTo(saveFile);
				list.add(fileVO);
            } catch (Exception e) {
            	e.printStackTrace();
            }

        }
        return list;
    }

    //첨부파일 다운로드
	@Override
	public ResponseEntity<Resource> downlodeFile(String fileName) {
		log.info("download file: " + fileName);
		FileSystemResource resource = new FileSystemResource("C:\\upload\\" + fileName);
		log.info("resource : " + resource);
		
		//파일이름 uuid 지우기
		String resourceName = resource.getFilename();
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition",
					"attachment; filename=" + new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	//첨부파일 삭제
	@Override
	public int deleteFile(List<FileVO> fileVO) {
		return 0;
	}
    
	
	
}