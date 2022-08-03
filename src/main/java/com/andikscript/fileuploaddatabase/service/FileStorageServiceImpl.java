package com.andikscript.fileuploaddatabase.service;

import com.andikscript.fileuploaddatabase.model.File;
import com.andikscript.fileuploaddatabase.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public void store(MultipartFile file) throws IOException {
        File file1 = new File(StringUtils.cleanPath(
                file.getOriginalFilename()),
                file.getContentType(),
                file.getBytes());
        fileRepository.save(file1);
    }

    @Override
    public File getFile(Integer id) {
        return fileRepository.findById(id).get();
    }

    @Override
    public Stream<File> getAllFiles() {
        return fileRepository.findAll().stream();
    }

    @Override
    public File getByName(String name) {
        return fileRepository.findByName(name);
    }
}
