
package com.yedam.app.post.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.board.service.BoardVO;
import com.yedam.app.board.web.BoardController;
import com.yedam.app.common.service.CommonVO;
import com.yedam.app.common.util.AuthUtil;
import com.yedam.app.post.service.PostService;
import com.yedam.app.post.service.PostVO;

@Controller
public class PostController {
	
	Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	PostService postService;
	
	//상단공지조회
	@GetMapping("postMainNotice")
	public String postList(Model model,PostVO postVO) {
		List<PostVO> list = postService.selectMainNotice(postVO);
		model.addAttribute("postUpMain",list);
		
		List<PostVO> depList = postService.selectDeptAll(postVO);
		model.addAttribute("deptMain",depList);
		
		return "post/MainNotice";
	}
	
	//단건조회
	@GetMapping("postInfo")
	public String postInfo (Model model, PostVO postVO) {
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
	//전체부서별
	@GetMapping("selectDeptAll")
	public String selectDeptAll(Model model, PostVO postVO) {
		List<PostVO> list = postService.selectDeptAll(postVO);
		model.addAttribute("postMain",list);
		return "post/postDept";	
	}	
	//전체익명	
	@GetMapping("selectAnoyAll")
	public String selectAnoyAll(Model model, PostVO postVO) {
		List<PostVO> list = postService.selectAnoyAll(postVO);
		model.addAttribute("postMain",list);
		return "post/postAnoy";			
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
	
}