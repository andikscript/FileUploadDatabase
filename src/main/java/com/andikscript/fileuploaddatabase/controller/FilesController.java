package com.andikscript.fileuploaddatabase.controller;

import com.andikscript.fileuploaddatabase.message.ResponseMessage;
import com.andikscript.fileuploaddatabase.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}
