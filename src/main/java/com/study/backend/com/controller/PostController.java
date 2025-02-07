package com.study.backend.com.controller;



//import com.study.backend.com.service.FileService;
import com.study.backend.com.service.PostService;
//import com.study.backend.config.util.FileUtils;
//import com.study.backend.dto.FileRequest;
import com.study.backend.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
//    private final FileService fileService;
//    private final FileUtils fileUtils;



    @PostMapping("/write")
    public String createPost(@RequestBody PostDTO postDTO, @RequestParam("files") List<MultipartFile> files) {
        // 시간 형식 수정
        String formattedDate = postDTO.getCreatedAt().replace("T", "").replace("Z", "");
        postDTO.setCreatedAt(formattedDate);

        // 게시글 저장
        Long postId = postService.insertPost(postDTO);

//        // 파일 업로드 처리
//        List<FileRequest> fileRequests = fileUtils.uploadFiles(files);
//
//        // 파일 정보 DB에 저장
//        fileService.saveFile(postId, fileRequests);

        return "게시글과 파일이 등록되었습니다.";
    }

    @GetMapping("/post")
    public List<PostDTO> getAllPosts() {
        var post = postService.findAll();
        System.out.println(post);
        return postService.findAll();
    }
    @GetMapping("/post/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        PostDTO post = postService.findById(id);
        post.setViews(post.getViews() + 1); // 조회수 증가
        postService.incrementViews(id); // 조회수 증가 메서드 호출
        return post;
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPost(@RequestParam String type, @RequestParam String keyword) {
        List<PostDTO> k = null;
        if("title".equals(type)) {
             k = postService.searchByTitle(keyword);
            System.out.println(k);

        } else if ("content".equals(type)) {
             k = postService.searchByContent(keyword);
            System.out.println(k);

        } else if ("username".equals(type)) {
             k = postService.searchByUsername(keyword);
            System.out.println(k);

        }
        if(k==null|| k.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시물이없습니");
        }
        return ResponseEntity.ok(k);
    }
    @GetMapping("/mylist")
    public List<PostDTO> getMyPosts(@RequestParam String username) {
        System.out.println(username);
        var post = postService.findByUsername(username);
        System.out.println(post);
        return post;
    }
}
