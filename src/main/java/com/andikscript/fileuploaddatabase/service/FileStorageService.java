package com.andikscript.fileuploaddatabase.service;

import com.andikscript.fileuploaddatabase.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileStorageService {

    public File store(MultipartFile file) throws IOException;

    public File getFile(Integer id);

    public Stream<File> getAllFiles();
}
