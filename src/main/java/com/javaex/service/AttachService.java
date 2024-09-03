package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.AttachDao;
import com.javaex.vo.AttachVo;

@Service
public class AttachService {
	
	@Autowired
	private AttachDao attachDao;
	
	
	public String upload(MultipartFile file) {
		System.out.println("AttachService.upload()");
		//System.out.println(file.getOriginalFilename());
		
		// 사진에 있는 기본정보로 우리가 관리할 정보를 뽑아내야된다 --> db 저장
		// 파일 저장 폴더
		String saveDir = "c:\\javaStudy\\upload";
		
		// 오리지날 파일명 --> 다시 제공할때 오리지날 파일명으로 주기위해서 (우리는 이름이 겹치지않기위해 다른이름으로 저장함)
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName);
		
		// 확장자 jpg
		String exeName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exeName: " + exeName);			// .jpg
		/*	// 파일명에 .이 몇번째에 위치했는지 알려주는
		System.out.println(orgName.lastIndexOf("."));
		// 11번째부터 끝까지 알려주는
		System.out.println(orgName.substring(11)); */
		
		// 파일사이즈
		long fileSize = file.getSize();
		System.out.println("FileSize: " + fileSize);
		
		// 내가 관리할 파일명 (겹치지않게 저장할파일명)
		//					  현재시간 (절대 겹치지않게 넣는거임)		 랜덤숫자
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+ exeName;
		System.out.println("saveName: " + saveName);
		
		// 파일 전체 경로 + 파일명
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath: " + filePath);
		
		// (1) db 저장    service에서 만들어낸 정보라서 new로 해야함
		// (1-1) 데이터 묶기
		AttachVo attachVo = new AttachVo(orgName, saveName, fileSize, filePath);
		System.out.println(attachVo);

		// (1-2) dao를 통해서 db에 저장
		// 과제....db에  table만들기
		System.out.println("과제: db 저장중...");
		
		
		// System.out.println(count); 
		
		// (2) 사진을 서버(강남)에 복사해야한다 우리쪽에 --> 하드디스크에 복사 (사진은 용량이 큼 db 오라클은 비쌈)
		// 파일 저장
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		} 
		
		return saveName;	// 시간 + 랜덤숫자
		
	}

	
	
}
