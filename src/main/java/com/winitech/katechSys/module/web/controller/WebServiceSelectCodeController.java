package com.winitech.katechSys.module.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.winitech.katechSys.module.web.model.response.GetSelectCodeListResponseDTO;
import com.winitech.katechSys.module.web.service.WebServiceSelectCodeService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/web")
public class WebServiceSelectCodeController {
	private static final Logger log = LoggerFactory.getLogger(WebServiceSelectCodeController.class);

	@Resource
	WebServiceSelectCodeService service;

	@ApiOperation(value="코드 조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "코드", dataType = "String", paramType = "query", defaultValue = ""),
		@ApiImplicitParam(name = "useat", value = "사용여부", dataType = "String", paramType = "query", defaultValue = ""),

	})
	@GetMapping(value = "/SelectCode", produces = "application/json;charset=UTF-8")
	public List<GetSelectCodeListResponseDTO> selectCode(@RequestParam(value = "code",required = false, defaultValue = "") String code,
														@RequestParam(value = "useat",required = false, defaultValue = "") String useat) throws Exception {

		log.info("Web Service - Rest Service called : /web/SelectCode");
		log.info("Request parameter {code : "+code+", useat : "+useat+"}");
		return service.selectCode(code, useat);
	}

}
