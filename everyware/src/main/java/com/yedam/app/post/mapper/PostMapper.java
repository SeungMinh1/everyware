package com.yedam.app.post.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.app.post.service.PostVO;

public interface PostMapper {
	
	//전체조회
	//	public List<PostVO> selectPostAll();
    //단건조회
		public PostVO postInfo (PostVO postVO);
	//상단공지조회
		public List<PostVO> selectMainNotice (PostVO postVO);
	//공지전체조회
		public List<PostVO> selectNoticeAll (PostVO postVO);
	//부서별 게시물 전체조회
		public List<PostVO> selectDeptAll (PostVO postVO);	
	//익명 게시물 전체조회	
		public List<PostVO> selectAnoyAll (PostVO postVO);
	//등록 
		public int postInsert (PostVO postVO);
	//수정	
		public Map<String, Object> postUpdate (PostVO postVO);
	//삭제 
		public int postDelete (int postId);

}
