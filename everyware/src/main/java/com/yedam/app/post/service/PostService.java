package com.yedam.app.post.service;

import java.util.List;

public interface PostService {
	// 전체  게시물 조회
	public List<PostVO> postList();
	//단건조회
	public PostVO postInfo(PostVO postVO)
}
