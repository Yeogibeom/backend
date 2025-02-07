package com.study.backend.com.service;

import java.util.List;
import java.util.Map;

public interface ComService {
	public Map<String,Object> getTest();

	public List<Map<String,Object>> getAllUser() throws Exception;
	
	
}
