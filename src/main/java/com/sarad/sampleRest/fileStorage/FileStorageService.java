package com.sarad.sampleRest.fileStorage;


import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    public void init();

    public void save(MultipartFile file);

//    public Resource load(String filename);
//
//    public Stream<Path> loadAll();
//
    public void deleteAll(String filename);

}
