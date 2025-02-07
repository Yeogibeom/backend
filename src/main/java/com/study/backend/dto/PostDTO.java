package com.study.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private String username;
    private String createdAt;
    private int views;




    // 생성자나 세터 메서드를 통해 DATETIME 형식으로 변환하는 방법
    public void setCreatedAt(String createdAt) {
        // 'T'를 공백으로 변환하고, 'Z'를 제거
        this.createdAt = createdAt.replace("T", " ").replace("Z", "");
    }
}
