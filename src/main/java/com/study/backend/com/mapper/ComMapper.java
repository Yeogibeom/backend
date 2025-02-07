package com.study.backend.com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface ComMapper {
	
	public  List<Map<String,Object>> getAllUser(String id);

	public List<Map<String,Object>> getAllUserList();
	
	

}
