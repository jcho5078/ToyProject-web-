package com.jcho5078.blog.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jcho5078.blog.service.UserService;
import com.jcho5078.blog.user.CustomUserDetailsService;
import com.jcho5078.blog.vo.UserVO;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	//로그인 중첩시 오류페이지로 이동
	@RequestMapping("login_duplicate")
	public String login_duplicate() {
		System.out.println("login_duplicate!");
		return "user/login_duplicate";
	}
	
	//회원가입
	@RequestMapping(value = "signUp", method = RequestMethod.POST)
	public String signUpUser(UserVO vo, 
			@RequestParam String id, @RequestParam String pw, @RequestParam String name) {
		
		String encPassword = userDetailsService.EncodingPw(vo.getPw());//회원가입 하면서 입력된 비밀번호 암호화.
		
		vo.setPw(encPassword);
		
		System.out.println(vo.getId()+","+vo.getPw()+","+vo.getName());
		
		userService.insertUser(vo);
		
		userDetailsService = null;
		
		return "redirect:/login";
	}
	
	//개인정보 조회
	@RequestMapping("user/userForm")
	public String moveUserForm(Model model, Principal principal) {
		
		String id = principal.getName();//스프링 시큐리티로 로그인한 아이디 가져오기.
		
		System.out.println(id);
		
		model.addAttribute("userForm", userService.getUserForm(id));
		
		return "user/UserForm";
	}
	
	//유저의 개인 정보 수정
	@RequestMapping("user/updateUserForm")
	public String updateUserForm(UserVO vo,
			@RequestParam String id ,@RequestParam String pw) {
		
		vo.setId(id);
		String encPw = userDetailsService.EncodingPw(pw);
	
		vo.setPw(encPw);
		
		userService.updatePrivateUser(vo);
		
		userDetailsService = null;
		
		return "redirect:/";
	}
	
	//유저의 개인 탈퇴
	@RequestMapping("user/deleteUser")
	public String deletePrivateUser(UserVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);//로그아웃 후 탈퇴
        }
		
		userService.deleteUser(vo);
		return "user/delete";
	}
	
	//모든 회원 정보 조회
	@RequestMapping("manage/viewAllUser")
	public String viewAllUser(Model model, UserVO vo) {
		
		model.addAttribute("viewAllUser", userService.selectUser(vo));
		
		return "manager/viewAllUser";
	}
	
	//모든 회원 정보 조회창에서 수정버튼 누르면 해당 회원 정보 가지고 수정 페이지로 이동.
	@RequestMapping(value = "manage/updateAllUserForm")
	public String getUpdateFormAllUser(Model model, UserVO vo,
			@RequestParam String id, @RequestParam String name, @RequestParam String hiredate) {
		
		vo.setId(id);
		vo.setName(name);
		vo.setHiredate(hiredate);
		
		model.addAttribute("User", vo);
		
		return "manager/updateForm";
	}
	
	//관리자가 선택한 회원 정보 수정.
	@RequestMapping("manage/updateAllUser")
	public String updateAllUser(UserVO vo,
			@RequestParam String id ,@RequestParam String pw) {
		
		vo.setId(id);
		String encPw = userDetailsService.EncodingPw(pw);
		
		vo.setPw(encPw);
		
		userDetailsService = null;
		
		userService.updateUser(vo);
		return "redirect:/manage/viewAllUser";
	}
	
	//관리자가 선택한 회원 탈퇴.
	@RequestMapping("manage/deleteAllUser")
	public String deleteAllUser(UserVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);//로그아웃 후 탈퇴
        }
		
		userService.deleteUser(vo);
		return "redirect:/manage/viewAllUser";
	}
}
