package com.andikscript.fileuploaddatabase.service;

import com.andikscript.fileuploaddatabase.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileStorageService {

    void store(MultipartFile file) throws IOException;

    File getFile(Integer id);

    Stream<File> getAllFiles();

    File getByName(String name);
}
