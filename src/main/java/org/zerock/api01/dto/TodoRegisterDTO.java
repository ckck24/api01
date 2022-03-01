package org.zerock.api01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoRegisterDTO {

    private String title;

    private String writer;

    private LocalDate dueDate;

    private MultipartFile[] files;

    private List<String> s3FilePath;


}
