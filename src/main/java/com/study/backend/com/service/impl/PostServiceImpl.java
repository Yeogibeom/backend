package com.study.backend.com.service.impl;

import com.study.backend.com.mapper.PostMapper;
import com.study.backend.com.service.PostService;
import com.study.backend.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    @Override
    public Long insertPost(PostDTO post) {
       postMapper.insertPost(post);
        return null;
    }

    @Override
    public List<PostDTO> findAll() {
        return postMapper.findAll();
    }

    @Override
    public PostDTO findById(Long id) {
        return postMapper.findById(id);
    }

    @Override
    public void incrementViews(Long id) {
        postMapper.incrementViews(id);
    }

    @Override
    public List<PostDTO> searchByTitle(String Keyword) {
        return postMapper.findByTitleContaining(Keyword);
    }

    @Override
    public List<PostDTO> searchByContent(String Keyword) {
        return postMapper.findByContentContaining(Keyword);
    }

    @Override
    public List<PostDTO> searchByUsername(String Keyword) {
        return postMapper.findByUsernameContaining(Keyword);
    }

    @Override
    public List<PostDTO> findByUsername(String username) {
        return postMapper.findByUsername(username);
    }
}
