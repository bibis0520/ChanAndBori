package com.chan.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.chan.util.UploadFileUtils;

@Controller
public class UploadController {

	@Resource( name="uploadPath" )	// <== chanServletContext에 bean으로 설정해둔 값
	private String uploadPath;

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

// Ajax를 이용한 파일 업로드 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping( value = "/uploadAjax", method = RequestMethod.GET )
	public void uploadAjax() throws Exception {

		logger.info("/uploadAjax, GET");

	}
																		//produces부분은 한국어를 정상적으로 전송하기 위한 간단한 설정.
	@RequestMapping( value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8" )
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {

		logger.info("/uploadAjax, POST");

		logger.info("originalName : " + file.getOriginalFilename());
		logger.info("size : " + file.getSize());
		logger.info("contentType : " + file.getContentType());
																//HttpStatus.CREATED는 원하는 리소스가 정상적으로 생성되었다는 상태코드(HttpStatus.OK를 사용해도 무방)
		return new ResponseEntity<>( UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes())
								     , HttpStatus.CREATED );
	}

// <iframe>을 이용한 파일 업로드 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping( value = "/uploadForm", method = RequestMethod.GET )
	public void uploadForm() throws Exception {

	}

	@RequestMapping( value = "/uploadForm", method = RequestMethod.POST )
	public String uploadForm( MultipartFile file, Model model ) throws Exception {

		// MultipartFile은 POST방식으로 들어온 파일 데이터를 의미한다.
		// MultipartFile 객체를 이용하면 아래와 같은 정보들을 추출할 수 있다.

		logger.info("originalName : " + file.getOriginalFilename());
		logger.info("size : " + file.getSize());
		logger.info("contentType : " + file.getContentType());

		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());

		model.addAttribute("savedName", savedName);

		return "uploadResult";
	}

	/**
	 * <<저장될 파일의 이름을 만들고 업로드까지 실행하는 함수>>
	 * 1. 랜덤한 uuid를 발생시킨다.
	 * 2. 그 uuid와 원본파일이름 사이에 "_"를 붙인뒤 저장될 파일이름(=savedName)을 만든다.
	 * 3. upload할 파일 객체(target, 쓸 파일)를 만든다.
	 * 4. fileData를 target에 copy한다.
	 * 5. 저장된 파일 이름을 return 한다.
	 *
	 * @param originalFileName
	 * @param fileData
	 * @return 최종 저장될 파일 이름
	 */
	private String uploadFile(String originalFileName, byte[] fileData) throws Exception {

		UUID uuid = UUID.randomUUID();	// <== random UUID generator, 중복되지 않는 고유한 키 값을 설정할 때 사용한다. (동일한 파일이 업로드될때 파일명의 중복을 방지)

		String savedName = uuid.toString() + "_" + originalFileName;

		logger.info("savedName : " + savedName);

		File target = new File(uploadPath, savedName);

		FileCopyUtils.copy(fileData, target);	// 실제로 파일 처리를 담당하는 FileCopyUtils ( 스프링이 제공, in org.springframework.util package )
												// .copy <== 데이터가 담긴 바이트의 배열(in)을 파일에 기록한다.
		return savedName;
	}
}