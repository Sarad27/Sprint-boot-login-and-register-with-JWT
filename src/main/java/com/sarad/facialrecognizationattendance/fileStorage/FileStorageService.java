package com.sarad.facialrecognizationattendance.fileStorage;


import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {

    public void init();

    public void save(MultipartFile file);

//    public Resource load(String filename);
//
//    public Stream<Path> loadAll();
//
    public void deleteAll(String filename);

}
