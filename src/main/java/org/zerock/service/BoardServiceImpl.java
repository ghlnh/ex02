package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

/*@Log4j*/
//비즈니스 영역을 담당하는 객체임을 표시하기 위해 사용
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	//BoardServiceImpl이 정상적으로 동작하기 위한 BoardMapper 객체 생성
	//spring 4.3 이상에서 단일 파라미터를 받는 생성자의 겨우 필요한 자동파라미터를 자동 주입해줌
	@Setter(onMethod_= @Autowired)
	private BoardMapper mapper;
	
	@Override
	public BoardVO get(Long bno) {
		System.out.println("get......" + bno);
		return mapper.read(bno);
	}
	
	@Override
	public List<BoardVO> getlist() {
		System.out.println("getList.....");
		return mapper.getList();
	}
	@Override
	public boolean modify(BoardVO board) {
		System.out.println("modify....." + board);
		return mapper.update(board) == 1;
	}
	
	@Override
	//필요하다면 예외처리나 void대신 int타입을 이용해서 사용 가능
	//ex) mapper.insertSelectKey()반환 값 int 사용하려고 int 리턴하는 쪽으로 작성
	public void register(BoardVO board) {
		System.out.println("register....." + board);
		mapper.insertSelectKey(board);
		
	}
	
	@Override
	public boolean remove(Long bno) {
		System.out.println("remove....." + bno);
		return mapper.delete(bno) == 1;
	}
	
	
}
