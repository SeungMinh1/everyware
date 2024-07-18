package com.yedam.app.attend.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.attach.service.FileVO;
import com.yedam.app.attend.security.mapper.UserMapper;
import com.yedam.app.attend.security.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public FileVO selectLogo() {
		return userMapper.mainLogo();
	}
	

}
