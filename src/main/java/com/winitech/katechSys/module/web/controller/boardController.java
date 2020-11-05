package com.winitech.katechSys.module.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.winitech.katechSys.module.web.model.response.GetBoardDetailDTO;
import com.winitech.katechSys.module.web.model.response.GetBoardListDTO;
import com.winitech.katechSys.module.web.model.response.GetSelectCodeListResponseDTO;
import com.winitech.katechSys.module.web.model.response.LoginDTO;
import com.winitech.katechSys.module.web.mybatis.entity.FileVO;
import com.winitech.katechSys.module.web.mybatis.entity.MemberDTO;
import com.winitech.katechSys.module.web.service.MemberService;
import com.winitech.katechSys.module.web.service.TestService;
import com.winitech.katechSys.module.web.service.WebServiceSelectCodeService;

@Controller
public class boardController {
	private static final Logger log = LoggerFactory.getLogger(boardController.class);

	@Resource
	TestService service;

	@Resource
	MemberService memberService;

	@RequestMapping(value="/board")
	public String board(ModelMap model, HttpSession session) {
		//로그인 인증
		MemberDTO memberDTO = new MemberDTO();
		LoginDTO userInfo = (LoginDTO)session.getAttribute("userInfo");

		memberDTO = memberService.selectUserList(userInfo);

		if(memberDTO != null) {

			model.addAttribute("name", memberDTO.getName());
			model.addAttribute("bbsTitle", "테스트자료실");
			return "newBoard";
		} else {
			return "redirect:/";
		}
	}



	@RequestMapping(value="/notice")
	public String notice(ModelMap model) {
		model.addAttribute("", "");
		return "notice";
	}

	@RequestMapping(value="/archive")
	public String archive(ModelMap model) {
		model.addAttribute("", "");
		return "archive";
	}

	@RequestMapping("/list")
	public @ResponseBody List<GetBoardListDTO> selectBoardList(@RequestParam(value ="bbsId", required=false, defaultValue="") String bbsId,
																@RequestParam(value ="title", required=false, defaultValue="") String title,
																@RequestParam(value ="contents", required=false, defaultValue="") String contents,
																@RequestParam(value ="page", required=false, defaultValue="1") int page
																) {
		//Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("bbsId", param);
		List<GetBoardListDTO> listData = service.selectBoard(bbsId, title, contents, page);
		//JSONArray resultData = new JSONArray(listData);
		//model.addAttribute("list", list);
		//model.addAttribute("list", jsonData);
		return listData;
	}

	@RequestMapping(value="/listCnt", method = RequestMethod.POST)
	public @ResponseBody int selectBoardListCnt(HttpServletRequest request) {
		String bbsId = request.getParameter("bbsId");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		int result = service.selectBoardCnt(bbsId, title, contents);
		return result;
	}


	@RequestMapping(value = "/boardDetail", method = RequestMethod.POST)
    public String boardDetail(HttpServletRequest request, ModelMap model) throws Exception{

		String bbsId = request.getParameter("bbsId");
		String bbscttSn = request.getParameter("bbscttSn");
		model.addAttribute("bbsId", bbsId);
        model.addAttribute("bbscttSn", bbscttSn);
        model.addAttribute("files", service.fileDetailService(bbscttSn, bbsId));
        return "boardDetail";
    }

	@RequestMapping("/selectBoardDetail")
	public @ResponseBody GetBoardDetailDTO selectBoardDetail(@RequestParam(value ="bbsId", required=false, defaultValue="") String bbsId,
																@RequestParam(value ="bbscttSn", required=false, defaultValue="") int bbscttSn
																) {
		GetBoardDetailDTO listData = service.selectDetail(bbsId, bbscttSn);
		return listData;
	}

	@RequestMapping("/fileDown/{bbsId}/{bno}")
	private void fileDown(@PathVariable String bbsId, @PathVariable String bno, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		FileVO fileVO = service.fileDetailService(bno, bbsId);

		try {
			String fileUrl = fileVO.getFileUrl();
			fileUrl +="/";
			String savePath = fileUrl;
			String fileName = fileVO.getFileName();

			String oriFileName = fileVO.getFileOriName();
			InputStream in = null;
			OutputStream os = null;
			File file = null;
			boolean skip = false;
			String client = "";

			try {
				file = new File(savePath, fileName);
				in = new FileInputStream(file);
			} catch(FileNotFoundException fe) {
				skip = true;
			}

			client = request.getHeader("User-Agent");

			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");

			if(!skip) {
				if (client.indexOf("MSIE") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                    // IE 11 이상
                } else if (client.indexOf("Trident") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                } else {
                    // 한글 파일명 처리
                    response.setHeader("Content-Disposition",
                            "attachment; filename=\"" + new String(oriFileName.getBytes("UTF-8"), "ISO8859_1") + "\"");
                    response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
                }
				response.setHeader("Content-Length", ""+file.length());
				os = response.getOutputStream();
				byte b[] = new byte[(int)file.length()];
				int leng = 0;
				while((leng = in.read(b))>0) {
					os.write(b, 0, leng);
				}
			} else {
				response.setContentType("text/html;charset=UTF-8");
				System.out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
			}
			in.close();
			os.close();
		} catch(Exception e) {
			System.out.println("ERROR: "+ e.getMessage());
		}
	}


	@RequestMapping(value="/boardWrite", method = RequestMethod.POST)
	public String boardWrite(HttpServletRequest request, ModelMap model) {
		String bbsId = request.getParameter("bbs_id");
		String bbscttSn = request.getParameter("bbsctt_sn");
		model.addAttribute("bbsId", bbsId);
		model.addAttribute("bbscttSn", bbscttSn);
		return "boardWrite";
	}

	@RequestMapping(value = "/insertBoardWrite", method = RequestMethod.POST)
	public @ResponseBody int insertBoardWrite(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception {
		String bbsId = request.getParameter("bbs_id");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String noticeYn = request.getParameter("bbsctt_radio");
		String ip =  request.getRemoteAddr();

		int result;

		FileVO file  = new FileVO();
		if(files.isEmpty()) {
			result = service.insertWrite(bbsId, title, contents, noticeYn, ip);
			return result;
		} else {
			String fileName = files.getOriginalFilename();
			String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
			File destinationFile;
			String destinationFileName;
			String fileUrl = "C:\\spring-tool-suite-4-4.5.1.RELEASE-e4.14.0-win32-x86_64\\workspace\\katechSys\\src\\main\\webapp\\WEB-INF\\uploadFiles\\";

			do {
				destinationFileName = RandomStringUtils.randomAlphanumeric(32)+"."+fileNameExtension;
				destinationFile = new File(fileUrl + destinationFileName);
			} while(destinationFile.exists());

			destinationFile.getParentFile().mkdirs();
			files.transferTo(destinationFile);

			result = service.insertWrite(bbsId, title, contents, noticeYn, ip);

			file.setBbsId(bbsId);
			file.setBno("-1");
			file.setFileName(destinationFileName);
			file.setFileOriName(fileName);
			file.setFileUrl(fileUrl);
			service.fileInsertService(file);

			return result;
		}
	}

	@RequestMapping(value = "/updateBoardWrite", method = RequestMethod.POST)
	public @ResponseBody int updateBoardWrite(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception {

		String bbsId = request.getParameter("bbs_id");
		String bbscttSn = request.getParameter("bbsctt_sn");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String noticeYn = request.getParameter("bbsctt_radio");
		String atchmnflId = request.getParameter("atchmnfl_id");

		int result;

		FileVO file  = new FileVO();
		FileVO curfile  = new FileVO();
		if(files.isEmpty()) { //첨부파일 없는 경우
			result = service.updateWrite(bbsId, bbscttSn, title, contents, noticeYn);
			if(atchmnflId.isEmpty()) { // 기존파일 없는 경우
				curfile = service.fileDetailService(bbscttSn, bbsId);
				if(curfile != null) { // table에 있는 경우
					String path = curfile.getFileUrl() + curfile.getFileName();
					File deleteFile = new File(path);
					if(deleteFile.exists() == true) {
						deleteFile.delete();
					}
					service.fileDeleteService(bbsId, bbscttSn);
				}
			}
			return result;
		}

		String fileName = files.getOriginalFilename();
		String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		String fileUrl = "C:\\spring-tool-suite-4-4.5.1.RELEASE-e4.14.0-win32-x86_64\\workspace\\katechSys\\src\\main\\webapp\\WEB-INF\\uploadFiles\\";

		do {
			destinationFileName = RandomStringUtils.randomAlphanumeric(32)+"."+fileNameExtension;
			destinationFile = new File(fileUrl + destinationFileName);
		} while(destinationFile.exists());

		destinationFile.getParentFile().mkdirs();
		files.transferTo(destinationFile);

		result = service.updateWrite(bbsId, bbscttSn, title, contents, noticeYn);

		file.setBbsId(bbsId);
		file.setBno(bbscttSn);
		file.setFileName(destinationFileName);
		file.setFileOriName(fileName);
		file.setFileUrl(fileUrl);


		curfile = service.fileDetailService(bbscttSn, bbsId);
		if(curfile != null) {//기존파일이 있는경우
			String path = curfile.getFileUrl() + curfile.getFileName();
			File deleteFile = new File(path);
			if(deleteFile.exists() == true) {
				deleteFile.delete();
			}
			service.fileUpdateService(file);
			return result;
		}

		service.fileInsertService(file);
		return result;
	}

	@RequestMapping(value = "/deleteBoardWrite", method = RequestMethod.POST)
	public @ResponseBody int deleteBoardWrite(HttpServletRequest request) throws Exception {
		String bbsId = request.getParameter("bbs_id");
		String bbscttSn = request.getParameter("bbsctt_sn");
		FileVO curfile = new FileVO();
		curfile = service.fileDetailService(bbscttSn, bbsId);
		if(curfile != null) {//기존파일이 있는경우
			String path = curfile.getFileUrl() + curfile.getFileName();
			File deleteFile = new File(path);
			if(deleteFile.exists() == true) {
				deleteFile.delete();
			}
			service.fileDeleteService(bbsId, bbscttSn);
		}
		int result = service.deleteWrite(bbsId, bbscttSn);
		return result;
	}
}
