package com.study.springboot.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.study.springboot.entity.FileData;
import com.study.springboot.repository.FileDataRepository;

import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class FileDataService {
	
//	private final String FOLDER_PATH = ResourceUtils.getFile("classpath:static/uploads/").toPath().toString() + "/";
//	private final String FOLDER_PATH = "/usr/local/tomcat10/webapps/Lec20_FileUpload/uploads";
	
//	@Value("${upload.folder.path}")
//    private String FOLDER_PATH;
	

	private final FileDataRepository fileDataRepository;
	
	
	public String uploadImageSystem(MultipartFile file, String title) throws IOException{
		String filePath = ResourceUtils.getFile("classpath:static/uploads/").toPath().toString() + "/" + file.getOriginalFilename();
		System.out.println(filePath);
		FileData fileData = fileDataRepository.save(
				FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .title(title)
                .build());
		file.transferTo(new File(filePath));
		if(filePath != null) {
			return "파일업로드 성공" + filePath;
		}
		return null;
	}
	
	public byte[] downloadImageSystem(Long id) throws IOException{
		FileData fileData = fileDataRepository.findById(id).orElseThrow(RuntimeException::new);
		
		String filePath = fileData.getFilePath();
		
		return Files.readAllBytes(new File(filePath).toPath());
	}
	
	public List<FileData> findAll(){
		return fileDataRepository.findAll();
	}
	
}
