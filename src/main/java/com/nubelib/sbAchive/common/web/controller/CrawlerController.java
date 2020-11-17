package com.nubelib.sbAchive.common.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrawlerController {

	@RequestMapping("/crawling")
	public @ResponseBody String crawling(ModelMap model, HttpServletRequest request) throws IOException {

		String url = "https://www.naver.com/";
		Connection.Response response = Jsoup.connect(url)
				.method(Connection.Method.GET)
				.execute();
		Document naverDocument = response.parse();
		Element elem = naverDocument.select("#NM_TS_ROLLING_WRAP").first();

		String result = elem.toString();
		return result;
	}

	@RequestMapping("/crawling2")
	public @ResponseBody String crawling2(ModelMap model, HttpServletRequest request) throws IOException {

		String url = "https://www.naver.com/";
		Connection.Response response = Jsoup.connect(url)
				.method(Connection.Method.GET)
				.execute();
		Document naverDocument = response.parse();
		Element elem = naverDocument.select("#yna_rolling").first();

		String result = elem.toString();
		return result;
	}
}
