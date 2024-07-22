
package com.yedam.app.post.web;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.attend.emp.service.PageDTO;
import com.yedam.app.attend.security.service.LoginUserVO;
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

	// 상단공지조회
	@GetMapping("postMainNotice")
	public String postList(Model model, Integer page, Integer cnt, PostVO postVO, SearchVO searchVO, Integer boardId, @AuthenticationPrincipal LoginUserVO principal) {
		String departmentId = principal.getUserVO().getDepartmentId();
		searchVO.setDepartmentId(departmentId);
		postVO.setBoardId(1);
		page = page == null ? 1 : page;
		cnt = cnt == null ? 10 : cnt;

		// 페이징
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO, searchVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page, postCnt, cnt); // 페이징

		List<PostVO> list = postService.selectMainNotice(postVO);
		model.addAttribute("postUpMain", list);

		// postVO.setBoardId(2);
		List<PostVO> depList = postService.selectDeptAll(postVO, searchVO);
		model.addAttribute("deptMain", depList);
		model.addAttribute("pg", pg);
		return "post/MainNotice";
	}

	// 단건조회
	@GetMapping("postInfo")
	public String postInfo(Model model, PostVO postVO, @RequestParam int postId) {
		postVO.setPostId(postId);
		postService.updateViewCnt(postVO);
		PostVO findVO = postService.postInfo(postVO);
		model.addAttribute("post", findVO);
		return "post/postInfo";
	}
	
	//익명단건조회
		@GetMapping("anoyInfo")
		public String AnoyInfo(Model model, PostVO postVO, @RequestParam int postId) {
			postVO.setPostId(postId);
			postService.updateViewCnt(postVO);
			PostVO findVO = postService.anoyInfo(postVO);
			model.addAttribute("post", findVO);
			return "post/anoyInfo";
		}

	// 전체공지
	@GetMapping("selectNoticeAll")
	public String selectNoticeAll(Model model, Integer page, Integer cnt, PostVO postVO, SearchVO searchVO ,@AuthenticationPrincipal LoginUserVO principal) {
		postVO.setBoardId(1);
		page = page == null ? 1 : page;
		cnt = cnt == null ? 10 : cnt;

		// 페이징
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO, searchVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page, postCnt, cnt); // 페이징

		List<PostVO> list = postService.selectNoticeAll(postVO, searchVO);
		model.addAttribute("postMain", list);
		model.addAttribute("pg", pg);
		return "post/postNotice";
	}
	
	// 전체부서별
		@GetMapping("selectDeptAll")
		public String selectDeptAll(Model model, Integer page, Integer cnt, PostVO postVO, SearchVO searchVO, @AuthenticationPrincipal LoginUserVO principal) {
			String departmentId = principal.getUserVO().getDepartmentId();
			searchVO.setDepartmentId(departmentId);
			postVO.setDepartmentId(departmentId);
			postVO.setBoardId(2);
			page = page == null ? 1 : page;
			cnt = cnt == null ? 10 : cnt;

			postVO.setPage(page);
			postVO.setCnt(cnt);
			int postCnt = postService.postCnt(postVO, searchVO); // 게시물 개수 세기
			PageDTO pg = new PageDTO(page, postCnt, cnt); // 페이징

			List<PostVO> list = postService.selectDeptAll(postVO, searchVO);
			model.addAttribute("postMain", list);
			model.addAttribute("pg", pg);
			model.addAttribute("departmentId", departmentId);

			return "post/postDept";
		}

		// 전체익명
		@GetMapping("selectAnoyAll")
		public String selectAnoyAll(Model model, Integer page, Integer cnt, PostVO postVO, SearchVO searchVO) {
			postVO.setBoardId(3);
			page = page == null ? 1 : page;
			cnt = cnt == null ? 10 : cnt;

			postVO.setPage(page);
			postVO.setCnt(cnt);
			int postCnt = postService.postCnt(postVO, searchVO); // 게시물 개수 세기
			PageDTO pg = new PageDTO(page, postCnt, cnt); // 페이징

			List<PostVO> list = postService.selectAnoyAll(postVO, searchVO);
			model.addAttribute("postMain", list);
			model.addAttribute("pg", pg);

			return "post/postAnoy";
		}


	@PostMapping("selectNoticeByView")
	// @ResponseBody
	public String selectNoticeByView(Model model, Integer page, Integer cnt, PostVO postVO, SearchVO searchVO) {
		postVO.setBoardId(1);
		page = page == null ? 1 : page;
		cnt = cnt == null ? 10 : cnt;

		// 페이징
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO, searchVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page, postCnt, cnt); // 페이징
		List<PostVO> list = postService.selectNoticeAll(postVO, searchVO);
		model.addAttribute("postMain", list);
		model.addAttribute("pg", pg);

		return "post/postNotice :: noticeList";

	}

	@PostMapping("selectDeptByView")
	// @ResponseBody
	public String selectDeptByView(Model model, Integer page, Integer cnt, PostVO postVO, SearchVO searchVO, @AuthenticationPrincipal LoginUserVO principal) {
		String departmentId = principal.getUserVO().getDepartmentId();
		searchVO.setDepartmentId(departmentId);
		postVO.setDepartmentId(departmentId);
		postVO.setBoardId(2);
		page = page == null ? 1 : page;
		cnt = cnt == null ? 10 : cnt;

		// 페이징
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO, searchVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page, postCnt, cnt); // 페이징
		List<PostVO> list = postService.selectDeptAll(postVO, searchVO);
		model.addAttribute("postMain", list);
		model.addAttribute("pg", pg);

		return "post/postDept :: noticeList";

	}

	@PostMapping("selectAnoyByView")
	// @ResponseBody
	public String selectAnoyByView(Model model, Integer page, Integer cnt, PostVO postVO, SearchVO searchVO) {
		postVO.setBoardId(3);
		page = page == null ? 1 : page;
		cnt = cnt == null ? 10 : cnt;

		// 페이징
		postVO.setPage(page);
		postVO.setCnt(cnt);
		int postCnt = postService.postCnt(postVO, searchVO); // 게시물 개수 세기
		PageDTO pg = new PageDTO(page, postCnt, cnt); // 페이징
		List<PostVO> list = postService.selectAnoyAll(postVO, searchVO);
		model.addAttribute("postMain", list);
		model.addAttribute("pg", pg);

		return "post/postAnoy :: noticeList";

	}

	
	// 등록 -페이지
	@GetMapping("postInsert")
	public String postInsertForm(Model model,PostVO postVO, @RequestParam Integer boardId ) {
		List<CommonVO> departmentList = postService.departmentList();
		List<BoardVO> selectBoard = postService.selectBoard();
		
		/*
		 * for (int i = 1 ; i <=3; i++) { selectBoard
		 * 
		 * }
		 */
		model.addAttribute("bid", boardId );
		model.addAttribute("post", postVO);
		model.addAttribute("department", departmentList);
		model.addAttribute("board", selectBoard);
		return "post/postInsert";
	}

	// 등록 - 처리
	@PostMapping("postInsert")
	@ResponseBody
	public PostVO postInsertProcess(PostVO postVO, @RequestParam Integer boardId) {
		// int boardType = postService.selectBoard(codeId);
		postVO.setEmpId(AuthUtil.getEmpId());
		postVO.setBoardId(boardId); // postVO에 boardId 설정
		postService.postInsert(postVO);

		 return postVO;
		 
	}

	// 수정 - 페이지
	@GetMapping("postUpdate")
	public String postUpdateForm(PostVO postVO, Model model) {
		PostVO findVO = postService.postInfo(postVO);
		List<CommonVO> departmentList = postService.departmentList();
		model.addAttribute("post", findVO);
		model.addAttribute("department", departmentList);
		return "post/postUpdate";
	}

	// 수정 - 처리
	@PostMapping("postUpdate")
	@ResponseBody
	public int postUpdate(PostVO postVO) {
		return postService.postUpdate(postVO);

	}

	// 삭제 - 처리
	@GetMapping("postDelete")
	public String postDelete(@RequestParam Integer postId , @RequestParam Integer boardId) {
		postService.postDelete(postId);
	//	postVO.setBoardId(boardId);
		if (boardId == 1) { 
			return "redirect:selectNoticeAll";
		}else if(boardId == 2) {
			return "redirect:selectDeptAll";
		}else if(boardId == 3) {
		return  "redirect:selectAnoyAll";
		}
		return null;
	}

	// 추천수 업뎃
	@PostMapping("likeUpdate")
	@ResponseBody
	public PostVO likeUpdate(PostVO postVO) {
		postVO.setEmpId(AuthUtil.getEmpId());
		postService.updateLikeCnt(postVO);
		postService.insertRecommend(postVO);
		return postService.postInfo(postVO);
	}

	// 추천취소 업뎃
	@PostMapping("likeDown")
	@ResponseBody
	public PostVO likeDown(PostVO postVO) {
		postVO.setEmpId(AuthUtil.getEmpId());
		postService.downLikeCnt(postVO);
		postService.deleteRecommend(postVO);
		return postService.postInfo(postVO);
	}

}