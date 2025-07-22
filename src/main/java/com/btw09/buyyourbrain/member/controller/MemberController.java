package com.btw09.buyyourbrain.member.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.btw09.buyyourbrain.member.service.MemberService;
import com.btw09.buyyourbrain.member.vo.Member;
import com.btw09.buyyourbrain.member.vo.MemberExpert;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	 private MemberService service;

	 //@Autowired
	 //private BCryptPasswordEncoder bcrypt;
	 
	 //일반 유저 회원가입으로 이동
	 @GetMapping("/register")
	 public String showRegisterForm(Model model) {
	     // 빈 Member 객체를 미리 넘겨줘야 th:object 사용 가능함
	     model.addAttribute("member", new Member());
	     return "member/memberEnrollForm"; // 타임리프에서는 .html 생략해서 보냄
	 }

	 @PostMapping("/register")
	    public String insertMember(@ModelAttribute Member m,
	                                HttpSession session,
	                                Model model) {
	        // 비밀번호 암호화
	        //String encPwd = bcrypt.encode(m.getUserPwd());
	        //m.setUserPwd(encPwd);
		 
		 	// 필수 기본값 세팅
		    m.setPoints(0);
		    m.setIsDelete("N");

		    Date now = new Date();
		    m.setEnrollDate(now);   // JOINED_AT
		    m.setCreateAt(now);     // CREATED_AT
		    m.setUpdateAt(now);     // UPDATED_AT

	        // 회원가입 처리
	        int result = service.insertMember(m);

	        if (result > 0) {
	            session.setAttribute("alertMsg", "회원가입을 환영합니다.");
	            return "redirect:/";
	        } else {
	            model.addAttribute("errorMsg", "회원가입에 실패하였습니다.");
	            return "common/errorPage"; // errorPage.html을 만들 예정이라면
	        }
	    }
	 
	 	//고수 회원가입
	 	@GetMapping("/expert/register")
		public String showExpertRegisterForm(Model model) {
			model.addAttribute("expert", new MemberExpert());
			return "member/expertEnrollForm"; // 타임리프 폼 위치
		}

		@PostMapping("/expert/register")
		public String insertExpert(@ModelAttribute MemberExpert expert,
								   HttpSession session,
								   Model model) {

			expert.setIsDelete("N");

			Date now = new Date();
			expert.setEnrollDate(now);
			expert.setCreateAt(now);
			expert.setUpdateAt(now);

			int result = service.insertExpert(expert); // 고수용 서비스 호출

			if (result > 0) {
				session.setAttribute("alertMsg", "고수 회원가입이 완료되었습니다.");
				return "redirect:/";
			} else {
				model.addAttribute("errorMsg", "고수 회원가입에 실패했습니다.");
				return "common/errorPage";
			}
		}
	 
	 
	 
	 //로그인 페이지 이동
	 @GetMapping("/login")
	 public String showLoginPage() {
	     return "member/loginPage"; // 로그인 폼 위치
	 }
	 
	 
	 //로그인
	 @PostMapping("/login")
		public String loginMember(@ModelAttribute Member m
								 ,HttpSession session
								 ,Model model) {
			
			//사용자가 입력한 아이디만 가지고 일치한 회원 정보 조회
			Member loginUser = service.loginMember(m);			
			
			//성공 실패 처리 
			if (loginUser != null && m.getUserPwd().equals(loginUser.getUserPwd())) {
		        session.setAttribute("alertMsg", "로그인 성공!");
		        session.setAttribute("loginUser", loginUser);
				
				//메인페이지로 재요청
				return "redirect:/";
				
			}else {//실패시
				model.addAttribute("errorMsg","로그인 실패!!");
				
				return "member/loginPage";
				
			}
		}
		
		//로그아웃
		@GetMapping("/logout")
		public String logoutMember(HttpSession session) {
			
			//loginUser 삭제
			session.removeAttribute("loginUser");
			
			session.setAttribute("alertMsg", "로그아웃 되었습니다.");
			
			//메인페이지로
			return "redirect:/";
		}
	 
	 

}
