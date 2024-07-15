package com.yedam.app.dataroom.file.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.aspectj.weaver.tools.UnsupportedPointcutPrimitiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.attach.service.FileVO;
import com.yedam.app.common.util.AuthUtil;
import com.yedam.app.dataroom.file.mapper.DataFileMapper;
import com.yedam.app.dataroom.file.service.DataFileService;
import com.yedam.app.dataroom.file.service.DataFileVO;
import com.yedam.app.mail.mail.service.MailVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataFileServiceImpl implements DataFileService {
	@Value("${file.upload.path}")
	private String uploadFolder;
	
	@Autowired
	DataFileMapper dataFileMapper;
	
    //폴더 저장 경로
 	private String getForder() {
 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

 		Date date = new Date();
 		String str = sdf.format(date);
 		return str.replace("-", File.separator);
 	}
 	
 	//첨부파일 등록
    @Override
    public List<DataFileVO> uploadDataFiles(MultipartFile[] uploadFile) {
        List<DataFileVO> list = new ArrayList<>();
        
        // 폴더 만들기
        //String uploadFolder = "C:\\upload";
        String uploadFolderPath = getForder();
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info("upload path: " + uploadPath);
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		//그룹아이디 생성
        String attachmentGroupId = UUID.randomUUID().toString();
        
        for (MultipartFile multipartFile : uploadFile) {
        	DataFileVO dfileVO = new DataFileVO();
        	
        	dfileVO.setAttachmentGroupId(attachmentGroupId);
        	
            log.info("------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());
			log.info("Upload ContentType : " + multipartFile.getContentType());

			//원본_파일_이름
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name : " + uploadFileName);
			//[ fileVO ] 원본파일이름
			dfileVO.setOriginFileName(uploadFileName); 
			
			//UUID 생성
            UUID uuid = UUID.randomUUID(); 
            uploadFileName = uuid.toString() + "_" + uploadFileName;
            log.info("UUID 1 : " + uploadFileName);
            log.info("UUID 2 : " + uuid.toString());
            
            //확장자만 담기
            int index = uploadFileName.lastIndexOf(".");
            log.info("contentType:" + uploadFileName.substring(index + 1));
            dfileVO.setExt(uploadFileName.substring(index + 1));
            
            //fileVO에 담기
            dfileVO.setUploadFileName(uuid.toString());   				//UUID -> 업로드파일이름
            dfileVO.setFileType(multipartFile.getContentType());         //첨부파일종류
            dfileVO.setFileSize(multipartFile.getSize());				//파일 사이즈
            dfileVO.setUploadPath(uploadFolderPath); 					//업로드 경로	
            
            Integer empId = AuthUtil.getEmpId();
            dfileVO.setEmpId(empId);
            
            dfileVO.setType("dataroom");
            
            try {
            	File saveFile = new File(uploadPath, uploadFileName);
            	multipartFile.transferTo(saveFile);
            	dataFileMapper.insertDataFile(dfileVO);
				list.add(dfileVO);
            } catch (Exception e) {
            	e.printStackTrace();
            }

        }
        return list;
    }

    //첨부파일 다운로드
	@Override
	public ResponseEntity<Resource> downlodeDataFile(String fileName) {
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
	public ResponseEntity<String> deleteDataFile(String fileName, String Type) throws UnsupportedEncodingException {
		log.info("deleteFile:" + fileName);
		
		File file;
		
		try {
			file = new File("C:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			file.delete();

		} catch (UnsupportedPointcutPrimitiveException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<String>("deleted", HttpStatus.OK);

		// 콘솔 출력
		//log.info("Original File Name : " + list.getOriginFileName());
		//log.info("Upload File Size : " + list.getFileSize());
		//log.info("Upload File Name : " + list.getUploadFileName());
		//log.info("Upload Path : " + list.getUploadPath());
		//String fileName = list.getUploadPath().replace("\\", "/") + '/' + list.getUploadFileName() + "_" 
		//	+ list.getOriginFileName();

	}
	
	
	
	//파일 단건 조회(DB)
	@Override
	 public DataFileVO selectDataFileInfo(DataFileVO dfileVO) {
		 return dataFileMapper.selectDataFileInfo(dfileVO);
	 }
	//파일 여러개 조회(DB)
	@Override
	public List<DataFileVO> selectFileByMailId(MailVO mailVO) {
		return dataFileMapper.selectFileByMailId(mailVO);
	}

	//파일 단건 삭제(DB)
	@Override
	public int deleteDataFileInfo(int fileId) {
		return dataFileMapper.deleteDataFileInfo(fileId);
	};
	
	
}
