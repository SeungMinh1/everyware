package com.yedam.app.attend.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yedam.app.attend.security.mapper.UserMapper;
import com.yedam.app.attend.security.service.LoginUserVO;
import com.yedam.app.attend.security.service.UserVO;


@Service
public class CustomerUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO userVO = userMapper.selectUser(username);
		
		if(userVO == null) {
			throw new UsernameNotFoundException(username);
		}
		    
		return new LoginUserVO(userVO);
	}

}
