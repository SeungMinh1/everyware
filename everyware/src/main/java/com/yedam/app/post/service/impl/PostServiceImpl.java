package com.yedam.app.post.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.service.BoardVO;
import com.yedam.app.common.service.CommonVO;
import com.yedam.app.post.mapper.PostMapper;
import com.yedam.app.post.service.PostService;
import com.yedam.app.post.service.PostVO;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostMapper postMapper;
	
	//단건조회
	@Override
	public PostVO postInfo(PostVO postVO) {
		return postMapper.postInfo(postVO);
	}
	//상단공지조회
	@Override
	public List<PostVO> selectMainNotice(PostVO postVO) {
		return postMapper.selectMainNotice(postVO);
	}
	//전체공지조회
	@Override
	public List<PostVO> selectNoticeAll(PostVO postVO) {
		return postMapper.selectNoticeAll(postVO);
	}
	//등록
	@Override
	public int postInsert(PostVO postVO) {
		return postMapper.postInsert(postVO);
	}
	//수정
	@Override
	public int postUpdate(PostVO postVO) {
		return postMapper.postUpdate(postVO);
	}
	//삭제
	@Override
	public int postDelete(int postId) {
		return postMapper.postDelete(postId);
	}
	//부서별 게시물 전체조회
	@Override
	public List<PostVO> selectDeptAll(PostVO postVO) {
		return postMapper.selectDeptAll(postVO);
	}
	@Override
	public List<PostVO> selectAnoyAll(PostVO postVO) {
		return postMapper.selectAnoyAll(postVO);
	}
	//부서 번호
	@Override
	public List<CommonVO> departmentList() {
		return postMapper.selectDepartment();
	}
	//게시판 번호
	@Override
	public List<BoardVO> selectBoard() {
		return postMapper.selectBoard();
	}
	//게시물 개수 
	@Override
	public int postCnt(PostVO postVO) {
		return postMapper.postCnt(postVO);
	}
	//조회수 
	@Override
	public int updateViewCnt(PostVO postVO) {
		return postMapper.updateViewCnt(postVO);
	}
	//추천수 
	@Override
	public int updateLikeCnt(PostVO postVO) {
		return postMapper.updateLikeCnt (postVO);
	}
	//추천 취소
	@Override
	public int downLikeCnt(PostVO postVO) {
		return postMapper.downLikeCnt (postVO);
	}



}
