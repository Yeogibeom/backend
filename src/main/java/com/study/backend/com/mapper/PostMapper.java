package com.study.backend.com.mapper;

import com.study.backend.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    void insertPost(PostDTO post);
    List<PostDTO> findAll();
    PostDTO findById(Long id);
    void incrementViews(Long id);
    List<PostDTO> findByTitleContaining(String Keyword);
    List<PostDTO> findByContentContaining(String Keyword);
    List<PostDTO> findByUsernameContaining(String Keyword);
    List<PostDTO> findByUsername(String Username);
}
