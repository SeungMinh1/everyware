package com.yedam.app.post.mapper;

import java.util.List;

import com.yedam.app.post.service.PostVO;

public interface PostMapper {
	
	//전체조회
		public List<PostVO> selectPostAll();
    //단건조회
		public int PostInfo (PostVO postVO);

}
