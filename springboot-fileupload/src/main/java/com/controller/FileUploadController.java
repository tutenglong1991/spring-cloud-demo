package com.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FileUploadController {

	@PostMapping("/upload")
	@ResponseBody
	public String uploadFile(@RequestParam MultipartFile file) throws Exception {
		log.info("start to receive file==========");
		String fileName = file.getOriginalFilename();
		log.info("file info: " + fileName + "," + file.getSize() + "kb");
		File localFile = new File("D:/storage/" + fileName);
		File dir = localFile.getParentFile();
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		file.transferTo(localFile);
		return "upload " + fileName + " success " + localFile.getCanonicalPath();
	}

	@GetMapping("/")
	public String index() {
		return "upload";
	}
}
