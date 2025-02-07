//package com.study.backend.com.service.impl;
//
//import com.study.backend.com.mapper.FileMapper;
//import com.study.backend.com.service.FileService;
//import com.study.backend.dto.FileRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//@Service
//@RequiredArgsConstructor
//public class FileServiceImpl implements FileService {
//    private  final FileMapper fileMapper;
//    @Override
//    public void saveFile(final Long postId,final List<FileRequest> files) {
//        if (CollectionUtils.isEmpty(files)) {
//            return;
//        }
//        for (FileRequest file : files) {
//            file.setPostId(postId);
//        }
//        fileMapper.saveAll(files);
//    }
//}
