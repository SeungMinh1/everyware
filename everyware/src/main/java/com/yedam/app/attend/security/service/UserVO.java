package com.yedam.app.attend.security.service;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //빌더패턴 자동생성
public class UserVO {
	private String accountId;
	private String password;
	private Integer empId;
}
