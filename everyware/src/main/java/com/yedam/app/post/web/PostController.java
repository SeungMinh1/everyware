
package com.yedam.app.post.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.attend.emp.service.PageDTO;
import com.yedam.app.board.service.BoardVO;
import com.yedam.app.common.service.CommonVO;
import com.yedam.app.common.util.AuthUtil;
import com.yedam.app.post.service.PostService;
import com.yedam.app.post.service.PostVO;
import com.yedam.app.post.service.SearchVO;

@Controller
public class PostController {
	
	Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	PostService postService;
	
	//상단공지조회
	@GetMapping("postMainNotice")
	public String postList(Model model,Integer page, Integer cnt,PostVO postVO,SearchVO searchVO, Integer boardId) {
		postVO.setBoardId(1);
		page = page == null? 1 : page; 
		cnt = cnt == null ? 10 : cnt ;
		
		//페이징 
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page,postCnt,cnt); //페이징 
		
		List<PostVO> list = postService.selectMainNotice(postVO);
		model.addAttribute("postUpMain",list);
		
		//postVO.setBoardId(2);	
		List<PostVO> depList = postService.selectDeptAll(postVO, searchVO);
		model.addAttribute("deptMain",depList);
		model.addAttribute("pg", pg);
		return "post/MainNotice";
	}
	
	//단건조회
	@GetMapping("postInfo")
	public String postInfo (Model model, PostVO postVO, @RequestParam int postId) {
		postVO.setPostId(postId);
		postService.updateViewCnt(postVO); 
		PostVO findVO = postService.postInfo(postVO);
		model.addAttribute("post",findVO);
		return "post/postInfo";
	}
	
	//전체공지 
	@GetMapping("selectNoticeAll")
	public String selectNoticeAll(Model model, Integer page, Integer cnt,  PostVO postVO, SearchVO searchVO){
		postVO.setBoardId(1);
		page = page == null? 1 : page; 
		cnt = cnt == null ? 10 : cnt ;
		
		//페이징 
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page,postCnt,cnt); //페이징 

		
		List<PostVO> list = postService.selectNoticeAll(postVO,searchVO);
		model.addAttribute("postMain",list);
		model.addAttribute("pg", pg);
		
		return "post/postNotice";
	}	
	
	@PostMapping("selectNoticeAll")
	public String selectNoticeAll1(Model model, Integer page, Integer cnt,  PostVO postVO, SearchVO searchVO){
		
		postVO.setBoardId(1);
		page = page == null? 1 : page; 
		cnt = cnt == null ? 10 : cnt ;
		
		//페이징 
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page,postCnt,cnt); //페이징 

		
		List<PostVO> list = postService.selectNoticeAll(postVO,searchVO);
		model.addAttribute("postMain",list);
		model.addAttribute("pg", pg);
		
		return "post/postNotice :: noticeList";
	}	
	
	@PostMapping("selectNoticeByView")
	//@ResponseBody
	public String selectNoticeByView(Model model,Integer page, Integer cnt,  PostVO postVO,SearchVO searchVO) {
		postVO.setBoardId(1);
		page = page == null? 1 : page; 
		cnt = cnt == null ? 10 : cnt ;
		
		//페이징 
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page,postCnt,cnt); //페이징 	
		 List<PostVO> list = postService.selectNoticeAll(postVO, searchVO);
		 model.addAttribute("postMain",list);
		 model.addAttribute("pg", pg);
		 
	 return "post/postNotice :: noticeList";
	 
	}
	
	@PostMapping("selectDeptByView")
	//@ResponseBody
	public String selectDeptByView(Model model,Integer page, Integer cnt,  PostVO postVO,SearchVO searchVO) {
		postVO.setBoardId(2);
		page = page == null? 1 : page; 
		cnt = cnt == null ? 10 : cnt ;
		
		//페이징 
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page,postCnt,cnt); //페이징 	
		 List<PostVO> list = postService.selectDeptAll(postVO, searchVO);
		 model.addAttribute("postMain",list);
		 model.addAttribute("pg", pg);
		 
	 return "post/postDept :: noticeList";
	 
	}
	
	@PostMapping("selectAnoyByView")
	//@ResponseBody
	public String selectAnoyByView(Model model,Integer page, Integer cnt,  PostVO postVO,SearchVO searchVO) {
		postVO.setBoardId(3);
		page = page == null? 1 : page; 
		cnt = cnt == null ? 10 : cnt ;
		
		//페이징 
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page,postCnt,cnt); //페이징 	
		 List<PostVO> list = postService.selectAnoyAll(postVO, searchVO);
		 model.addAttribute("postMain",list);
		 model.addAttribute("pg", pg);
		 
	 return "post/postAnoy :: noticeList";
	 
	}
	
	
	//전체부서별
	@GetMapping("selectDeptAll")
	public String selectDeptAll(Model model, Integer page, Integer cnt, PostVO postVO, SearchVO searchVO) {
		postVO.setBoardId(2);
		page = page == null? 1 : page; 
		cnt = cnt == null ? 10 : cnt ;
		
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page,postCnt,cnt); //페이징 
		
		List<PostVO> list = postService.selectDeptAll(postVO,searchVO);
		model.addAttribute("postMain",list);
		model.addAttribute("pg", pg);
		
		return "post/postDept";	
	}
	@PostMapping("selectDeptAll")
	public String selectDeptAll1(Model model, Integer page, Integer cnt,  PostVO postVO, SearchVO searchVO){
		
		postVO.setBoardId(2);
		page = page == null? 1 : page; 
		cnt = cnt == null ? 10 : cnt ;
		
		//페이징 
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page,postCnt,cnt); //페이징 

		
		List<PostVO> list = postService.selectDeptAll(postVO,searchVO);
		model.addAttribute("postMain",list);
		model.addAttribute("pg", pg);
		
		return "post/postDept :: noticeList";
	}	
	
	//전체익명	
	@GetMapping("selectAnoyAll")
	public String selectAnoyAll(Model model, Integer page, Integer cnt, PostVO postVO, SearchVO searchVO) {
		postVO.setBoardId(3);
		page = page == null? 1 : page; 
		cnt = cnt == null ? 10 : cnt ;
		
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page,postCnt,cnt); //페이징 
		
		List<PostVO> list = postService.selectAnoyAll(postVO, searchVO);
		model.addAttribute("postMain",list);
		model.addAttribute("pg", pg);
		
		return "post/postAnoy";			
	}
	
	@PostMapping("selectAnoyAll")
	public String selectAnoyAll1(Model model, Integer page, Integer cnt,  PostVO postVO, SearchVO searchVO){
		
		postVO.setBoardId(3);
		page = page == null? 1 : page; 
		cnt = cnt == null ? 10 : cnt ;
		
		//페이징 
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page,postCnt,cnt); //페이징 

		
		List<PostVO> list = postService.selectAnoyAll(postVO,searchVO);
		model.addAttribute("postMain",list);
		model.addAttribute("pg", pg);
		
		return "post/postAnoy :: noticeList";
	}	
	// 등록 -페이지
	@GetMapping ("postInsert")
	public String postInsertForm(Model model) {
		PostVO postVO = new PostVO();
		List<CommonVO> departmentList = postService.departmentList();	
		List<BoardVO> selectBoard = postService.selectBoard();	
		model.addAttribute("post",postVO);
		model.addAttribute("department",departmentList);
		model.addAttribute("board",selectBoard);
		return "post/postInsert";
	}
	
	//등록 - 처리
	@PostMapping("postInsert")
	public String postInsertProcess (MultipartFile[] uploadFile,  PostVO postVO) {
		//int boardType = postService.selectBoard(codeId);
		postVO.setEmpId(AuthUtil.getEmpId());
		postService.postInsert(postVO);

		if("f1".equals(postVO.getBoardType())){
			postVO.setNotificationYn("Y");
			return "redirect:selectNoticeAll";
		}else if("f2".equals(postVO.getBoardType())){
			return "redirect:selectDeptAll";
	   }else if("f3".equals(postVO.getBoardType())){
		   return "redirect:selectAnoyAll";
	   }else {
		   return "redirect:postInsert";
	   }
	}
	
	//수정 - 페이지
	@GetMapping("postUpdate")
	public String postUpdateForm(PostVO postVO, Model model) {
		PostVO findVO = postService.postInfo(postVO);
		List<CommonVO> departmentList = postService.departmentList();	
		model.addAttribute("post",findVO);
		model.addAttribute("department",departmentList);
		return "post/postUpdate";
	}
	//수정 - 처리
	@PostMapping("postUpdate")
	public String postUpdate( PostVO postVO){
		postService.postUpdate(postVO);
		return "redirect:postInfo?postId="+postVO.getPostId();
	}
	//삭제 - 처리 
	@GetMapping("postDelete")
	public String postDelete(@RequestParam Integer postId) {
		postService.postDelete(postId);
		return "redirect:selectNoticeAll";
	}
	
	//추천수 업뎃
	@PostMapping("likeUpdate")
	@ResponseBody
	public PostVO likeUpdate(PostVO postVO){
		postVO.setEmpId(AuthUtil.getEmpId());
		postService.updateLikeCnt(postVO);
		postService.insertRecommend(postVO);
		return postService.postInfo(postVO);
	}
	
	//추천취소 업뎃
		@PostMapping("likeDown")
		@ResponseBody
		public PostVO likeDown(PostVO postVO){
			postVO.setEmpId(AuthUtil.getEmpId());
			postService.downLikeCnt(postVO);
			postService.deleteRecommend(postVO);
			return postService.postInfo(postVO);	
		}
	
	
}