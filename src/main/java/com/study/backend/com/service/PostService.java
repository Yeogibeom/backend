package com.study.backend.com.service;

import com.study.backend.dto.PostDTO;

import java.util.List;

public interface PostService {
   Long insertPost(PostDTO post);
   List<PostDTO> findAll();
   PostDTO findById(Long id);
   void incrementViews(Long id);
   List<PostDTO> searchByTitle(String Keyword);
   List<PostDTO> searchByContent(String Keyword);
   List<PostDTO> searchByUsername(String Keyword);
   List<PostDTO> findByUsername(String username);
}
