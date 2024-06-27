package com.yedam.app.attend.security.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.app.attend.security.service.UserVO;

@Mapper
public interface UserMapper {
	
	public UserVO selectUser(String id);
	
	
	
}
