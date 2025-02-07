package com.study.backend.com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.backend.com.service.ComService;

@RestController
@RequestMapping("/public")
public class ComController {

	@Autowired
	private ComService comService;



	/**
	 *  함수 설명
	 *
	 * @author : 이름
	 * @param : 매개변수 - 이름 설명
	 * @return : ResponseEntity - 응답
	 */
	@RequestMapping("/test")
	public ResponseEntity  bbb() {
		comService.getTest();
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("test","test");
		
		return ResponseEntity.ok().body(map);
	}

	/**
	 *  사용자 조회
	 *
	 * @author : 이름
	 * @param : 매개변수 - 이름 설명
	 * @return : ResponseEntity - 응답
	 */
	@RequestMapping("/getAllUser")
	public ResponseEntity<?>  getAllUser() throws Exception{
		List<Map<String,Object>> getALLuserList =  comService.getAllUser();


		return ResponseEntity.ok().body(getALLuserList);
	}



	
	
	
}
