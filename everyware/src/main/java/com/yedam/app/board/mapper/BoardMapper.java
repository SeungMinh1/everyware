package com.yedam.app.board.mapper;

import java.util.List;

import com.yedam.app.board.service.BoardVO;

public interface BoardMapper {
	//전체조회
	public List<BoardVO> selectBoardAll();
	
	//단건조회
	public BoardVO selectBoard(BoardVO bvo);
	
	//등록
	public int insertBoard(BoardVO bvo);
	
	//수정
	public int updateBoard(BoardVO bvo);
	
	//삭제
	public int deleteBoard(int boardId);
}
