package org.zerock.api01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.api01.dto.TodoRegisterDTO;
import org.zerock.api01.util.LocalUploader;
import org.zerock.api01.util.S3Uploader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final LocalUploader localUploader;
    private final S3Uploader s3Uploader;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(TodoRegisterDTO todoRegisterDTO){

        log.info("todo register todo....");
        log.info(todoRegisterDTO);

        MultipartFile[] files = todoRegisterDTO.getFiles();

        if(files != null && files.length > 0){

            List<String> uploadFilesPath = new ArrayList<>();

            //local upload alll
            for (MultipartFile file:files) {
                log.info(files);
                uploadFilesPath.addAll(localUploader.uploadLocal(file));
            }

            //s3 upload
            List<String> s3UploadPaths = uploadFilesPath.stream().map(filePath -> s3Uploader.upload(filePath)).collect(Collectors.toList());

            log.info(s3UploadPaths);

            todoRegisterDTO.setS3FilePath(s3UploadPaths);


        }




        return Map.of("tno", 123L);
    }

}
