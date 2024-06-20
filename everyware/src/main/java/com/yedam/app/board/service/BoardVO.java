package com.yedam.app.board.service;

import lombok.Data;

@Data
public class BoardVO {
	private Integer boardId;  //게시판 번호
	private String boardType;  
	private String boardTitle;

}
