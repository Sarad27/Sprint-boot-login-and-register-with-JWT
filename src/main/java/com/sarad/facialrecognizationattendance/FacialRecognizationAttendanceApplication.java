package com.sarad.facialrecognizationattendance;

import com.sarad.facialrecognizationattendance.fileStorage.FileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class FacialRecognizationAttendanceApplication {

	@Resource
	FileStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(FacialRecognizationAttendanceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception{
//		storageService.init();
//	}
}
