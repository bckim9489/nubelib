package com.winitech.katechSys.module.web.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.winitech.katechSys.module.web.model.response.GetBoardDetailDTO;
import com.winitech.katechSys.module.web.model.response.GetBoardListDTO;
import com.winitech.katechSys.module.web.mybatis.entity.FileVO;
import com.winitech.katechSys.module.web.mybatis.entity.GetBoardDetailEntity;
import com.winitech.katechSys.module.web.mybatis.entity.GetBoardListEntity;
import com.winitech.katechSys.module.web.mybatis.entity.SetBoardWriteEntity;
import com.winitech.katechSys.module.web.mybatis.entity.deleteBoardWriteEntity;
import com.winitech.katechSys.module.web.mybatis.entity.selectBoardCntEntity;
import com.winitech.katechSys.module.web.mybatis.entity.updateBoardWriteEntity;

@Service
@Mapper
public interface BoardMapper {
	List<GetBoardListDTO> selectBoardList(GetBoardListEntity entity);
	GetBoardDetailDTO selectBoardDetail(GetBoardDetailEntity entity);
	int insertBoardWrite(SetBoardWriteEntity entity);
	int updateBoardWrite(updateBoardWriteEntity entity);
	int deleteBoardWrite(deleteBoardWriteEntity entity);
	int selectBoardListCnt(selectBoardCntEntity entity);
	public int fileInsert(FileVO file) throws Exception;
	public FileVO fileDetail(String bno, String bbsId) throws Exception;
	public int fileUpdate(FileVO file) throws Exception;
	public int fileDelete(String bbsId, String bno) throws Exception;
}
