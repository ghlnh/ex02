package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

public interface BoardMapper {
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	//delete()의 메서드 리턴 타입은 int로 지정해서 
	//만일 정상적으로 데이터가 삭제되면 1 이상의 값을 가지도록 작성
	public int delete(Long bno);
	
	public int update(BoardVO board);
}