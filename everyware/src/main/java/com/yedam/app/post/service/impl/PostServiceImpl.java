package com.yedam.app.post.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.post.mapper.PostMapper;
import com.yedam.app.post.service.PostService;
import com.yedam.app.post.service.PostVO;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostMapper postMapper;
	
	@Override
	public List<PostVO> postList() {
		return postMapper.selectPostAll();
	}

	@Override
	public PostVO postInfo(PostVO postVO) {
		return null;
	}

}
