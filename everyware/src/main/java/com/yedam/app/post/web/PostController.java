package com.yedam.app.post.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.post.service.PostService;
import com.yedam.app.post.service.PostVO;

@Controller
public class PostController {
	@Autowired
	PostService postService;
	
	//상단공지조회
	@GetMapping("postMainNotice")
	public String postList(Model model,PostVO postVO) {
		List<PostVO> list = postService.selectMainNotice(postVO);
		model.addAttribute("postMain",list);
		return "post/MainNotice";
	}
	
	//단건조회
	@GetMapping("postInfo")
	public String postInfo ( Model model, PostVO postVO) {
		PostVO findVO = postService.postInfo(postVO);
		model.addAttribute("post",findVO);
		
		return "post/postInfo";
	}
	
	//전체공지 
	@GetMapping("selectNoticeAll")
	public String selectNoticeAll(Model model, PostVO postVO) {
		List<PostVO> list = postService.selectNoticeAll(postVO);
		model.addAttribute("postMain",list);
		
		return "post/postNotice";
		
	}
	// 등록 -페이지
	@GetMapping ("postInsert")
	public String postInsertForm() {
		return "post/postInsert";
	}
	
	//등록 - 처리
	@PostMapping("postInsert")
	public String postInsertProcess (PostVO postVO) {
		postService.postInsert(postVO);
		return "redirect:postList";
	}
	
	//수정 - 페이지
	@GetMapping("postUpdate")
	public String postUpdateForm(PostVO postVO, Model model) {
		PostVO findVO = postService.postInfo(postVO);
		model.addAttribute("post",findVO);
		return "post/postUpdate";
	}
	//수정 - 처리
	@PostMapping("postUpdate")
	@ResponseBody
	public Map<String,Object>postUpdateAJAXJSON(@RequestBody PostVO postVO){
		return postService.postUpdate(postVO);
	}
	//삭제 - 처리 
	@GetMapping("postDelete")
	public String postDelete(@RequestParam Integer postId) {
		postService.postDelete(postId);
		return "redirect:postList";
	}
	
}