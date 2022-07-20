package com.andikscript.fileuploaddatabase.controller;

import com.andikscript.fileuploaddatabase.message.ResponseFile;
import com.andikscript.fileuploaddatabase.message.ResponseMessage;
import com.andikscript.fileuploaddatabase.model.File;
import com.andikscript.fileuploaddatabase.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping(value = "/api/file")
public class FilesController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file")MultipartFile file) throws IOException {
        fileStorageService.store(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("Upload the file is successfull : " + file.getOriginalFilename()));
    }

    @GetMapping(value = "/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> list = fileStorageService.getAllFiles()
                .map(file -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/api/file/download/")
                            .path(String.valueOf(file.getId()))
                            .toUriString();

                    return new ResponseFile(
                            file.getName(),
                            fileDownloadUri,
                            file.getType(),
                            file.getData().length);
                }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable(value = "id") Integer id) {
        File file = fileStorageService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                + file.getName())
                .body(file.getData());
    }
}
