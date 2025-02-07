package com.study.backend.com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.backend.com.mapper.ComMapper;
import com.study.backend.com.service.ComService;

@Service
public class ComServiceImpl implements ComService {

	
	@Autowired
	private ComMapper comMapper;
	
	
	@Override
	public Map<String, Object> getTest() {
		
		List<Map<String,Object>> list = comMapper.getAllUser("uten");
		
		return null;
	}

	@Override
	public List<Map<String, Object>> getAllUser() throws Exception {

		return comMapper.getAllUserList();
	}


}
