package com.sarad.sampleRest;

import com.sarad.sampleRest.fileStorage.FileStorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class SampleRest {

	@Resource
	FileStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(SampleRest.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception{
//		storageService.init();
//	}
}
