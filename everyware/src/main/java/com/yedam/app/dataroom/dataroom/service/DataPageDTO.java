package com.yedam.app.dataroom.dataroom.service;

import lombok.Data;

@Data
public class DataPageDTO {
	private int page; //현재페이지
	private int startPage, endPage, realEnd; // 11 [13]  20 시작/끝/총페이지수
	private boolean prev, next; // 이전, 이후 페이지
	private int prod = 10; // 한페이지당 몇개
	private int pgnum = 5; // 페이지를 몇개표시
	
	
	public DataPageDTO(int page, int totalCnt, int prod) {
		this.page = page;
		realEnd = (int) Math.ceil(totalCnt/(double)prod);  
		this.endPage = (int) Math.ceil(page/(double)pgnum) * pgnum;
		this.startPage = this.endPage-(pgnum-1);
		this.endPage = this.endPage > realEnd ? realEnd : this.endPage;
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
