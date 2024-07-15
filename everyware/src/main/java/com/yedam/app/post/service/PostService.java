package com.yedam.app.post.service;

import java.util.List;
import java.util.Map;

import com.yedam.app.board.service.BoardVO;
import com.yedam.app.common.service.CommonVO;

public interface PostService {
	//단건조회
	public PostVO postInfo(PostVO postVO);
	//익명 단건조회
	public PostVO anoyInfo(PostVO postVO);
	//상단공지 전체조회
	public List<PostVO> selectMainNotice (PostVO postVO);
	//공지 전체조회
	public List<PostVO> selectNoticeAll (PostVO postVO, SearchVO searchVO);
	//부서별 게시물 전체조회
	public List<PostVO> selectDeptAll (PostVO postVO, SearchVO searchVO);
	
	//익명 게시물 전체조회
	public List<PostVO> selectAnoyAll (PostVO postVO, SearchVO searchVO);
	//부서 조회
	public List<CommonVO> departmentList();
	//게시판 번호 조회
	public List<BoardVO> selectBoard();
	//등록
	public int postInsert (PostVO postVO);
	//수정
	public int postUpdate (PostVO postVO);
	//삭제
	public int postDelete (int postId);
	
	//게시물 개수 
	public int postCnt(PostVO postVO, SearchVO searchVO);
	
	//조회수 
	public int updateViewCnt(PostVO postVO);
	
	//추천수
	public int updateLikeCnt(PostVO postVO);
	
	//추천 취소
	public int downLikeCnt(PostVO postVO);
	
	//추천 테이블 삽입
    public  int insertRecommend (PostVO postVO);
    
    //추천 테이블 삭제 
    public int deleteRecommend (PostVO postVO);
    
}