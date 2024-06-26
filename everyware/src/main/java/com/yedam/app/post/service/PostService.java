package com.yedam.app.post.service;

import java.util.List;
import java.util.Map;

import com.yedam.app.board.service.BoardVO;
import com.yedam.app.common.service.CommonVO;

public interface PostService {
	//단건조회
	public PostVO postInfo(PostVO postVO);
	//상단공지 전체조회
	public List<PostVO> selectMainNotice (PostVO postVO);
	//공지 전체조회
	public List<PostVO> selectNoticeAll (PostVO postVO);
	//부서별 게시물 전체조회
	public List<PostVO> selectDeptAll (PostVO postVO);
	
	//익명 게시물 전체조회
	public List<PostVO> selectAnoyAll (PostVO postVO);
	//부서 조회
	public List<CommonVO> departmentList();
	//게시판 번호 조회
	public List<BoardVO> selectBoard();
	//등록
	public int postInsert (PostVO postVO);
	//수정
	public Map<String,Object> postUpdate (PostVO postVO);
	//삭제
	public int postDelete (int postId);
}
