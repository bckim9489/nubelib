package com.winitech.katechSys.module.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winitech.katechSys.module.web.model.response.GetBoardDetailDTO;
import com.winitech.katechSys.module.web.model.response.GetBoardListDTO;
import com.winitech.katechSys.module.web.model.response.TestResponseDTO;
import com.winitech.katechSys.module.web.mybatis.entity.FileVO;
import com.winitech.katechSys.module.web.mybatis.entity.GetBoardDetailEntity;
import com.winitech.katechSys.module.web.mybatis.entity.GetBoardListEntity;
import com.winitech.katechSys.module.web.mybatis.entity.SetBoardWriteEntity;
import com.winitech.katechSys.module.web.mybatis.entity.TestEntity;
import com.winitech.katechSys.module.web.mybatis.entity.deleteBoardWriteEntity;
import com.winitech.katechSys.module.web.mybatis.entity.selectBoardCntEntity;
import com.winitech.katechSys.module.web.mybatis.entity.updateBoardWriteEntity;
import com.winitech.katechSys.module.web.mybatis.mapper.BoardMapper;
import com.winitech.katechSys.module.web.mybatis.mapper.TestMapper;

@Service
public class TestService implements TestServiceImp {
	@Autowired
	private BoardMapper mapper;

	/*
	 * public List<GetBoardListDTO> selectBoard(String bbsId) { GetBoardListEntity
	 * entity = new GetBoardListEntity(bbsId); return
	 * mapper.selectBoardList(entity); }
	 */

	public List<GetBoardListDTO> selectBoard(String bbsId, String title, String contents, int page) {
		GetBoardListEntity entity = new GetBoardListEntity(bbsId, title, contents, page);
		return mapper.selectBoardList(entity);
	}

	public GetBoardDetailDTO selectDetail(String bbsId, int bbscttSn) {
		GetBoardDetailEntity entity = new GetBoardDetailEntity(bbsId, bbscttSn);
		return mapper.selectBoardDetail(entity);
	}


	@Override
	public List<TestResponseDTO> mapReqTest(List<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertWrite(String bbsId, String title, String contents, String  noticeYn, String ip) {
		SetBoardWriteEntity entity = new SetBoardWriteEntity(bbsId, title, contents, noticeYn, ip);
		return mapper.insertBoardWrite(entity);
	}

	public int updateWrite(String bbsId, String bbscttSn, String title, String contents, String  noticeYn) {
		updateBoardWriteEntity entity = new updateBoardWriteEntity(bbsId, bbscttSn, title, contents,  noticeYn);
		return mapper.updateBoardWrite(entity);
	}

	public int deleteWrite(String bbsId, String bbscttSn) {
		deleteBoardWriteEntity entity = new deleteBoardWriteEntity(bbsId, bbscttSn);
		return mapper.deleteBoardWrite(entity);
	}

	public int selectBoardCnt(String bbsId, String title, String contents) {
		selectBoardCntEntity entity = new selectBoardCntEntity(bbsId, title, contents);
		return mapper.selectBoardListCnt(entity);
	}

	public int fileInsertService(FileVO file) throws Exception{
	    return mapper.fileInsert(file);
	}
	public FileVO fileDetailService(String bno, String bbsId) throws Exception{
		return mapper.fileDetail(bno, bbsId);
	}

	public int fileUpdateService(FileVO file) throws Exception {
		return mapper.fileUpdate(file);

	}

	public int fileDeleteService(String bbsId, String bno)throws Exception {
		return mapper.fileDelete(bbsId, bno);
	}

}
