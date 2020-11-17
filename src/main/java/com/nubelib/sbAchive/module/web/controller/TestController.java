package com.nubelib.sbAchive.module.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nubelib.sbAchive.module.web.model.response.TestResponseDTO;
import com.nubelib.sbAchive.module.web.service.TestService;


@RestController
@RequestMapping("/test")
public class TestController {
	private static final Logger log = LoggerFactory.getLogger(TestController.class);

	@Resource
	TestService service;

	@GetMapping("/array-param")
	public List<TestResponseDTO> mapReqTest(@RequestParam String param1,
									   @RequestParam String param2,
									   @RequestParam String param3,
									   @RequestParam String param4)  throws Exception{

		log.info("param1 : "+param1);
		log.info("param2 : "+param2);
		log.info("param3 : "+param3);
		log.info("param4 : "+param4);

		List<String> list = new ArrayList<>();
		if(param1.equals("Y")) {list.add("01");} else {list.add("");}
		if(param2.equals("Y")) {list.add("02");} else {list.add("");}
		if(param3.equals("Y")) {list.add("03");} else {list.add("");}
		if(param4.equals("Y")) {list.add("04");} else {list.add("");}

		log.info("list : "+list.toString());

		return service.mapReqTest(list);
	}
}
