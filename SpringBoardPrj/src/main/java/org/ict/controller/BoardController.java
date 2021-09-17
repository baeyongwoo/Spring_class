package org.ict.controller;

import java.util.List;

import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.ict.domain.BoardVO;
import org.ict.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//의존성, 로깅기능 추가
@Controller
@Log4j
@RequestMapping("/board/*") //클래스 위에 작성시
// 이 클래스를 사용하는 모든 메서드의 연결주소 앞에 /board/ 추가
@AllArgsConstructor //의존성 주입 설정을 위해 생성자만 생성, getter, setter필요없을 때
public class BoardController {
	
	// 컨트롤러는 서비스를 호출 / 서비스는 매퍼를 호출
	@Autowired
	private BoardService service;
	
	@GetMapping("/list") //Get방식으로만 주소연결
	public void list(Model model, String keyword) {
		log.info(keyword);
		log.info("list로직 접속");
		// 전체 글 정보를 얻어와서
		if(keyword == null) {
			keyword = "";
		}
		
		keyword.toLowerCase();
		List<BoardVO>boardList = service.getList(keyword);
		
		// view파일에 list라는 이름으로 넘기기
		model.addAttribute("list", boardList);
		model.addAttribute("keyword", keyword);
		//1. views 하위에 경로에 맞게 폴더 및 .jsp파일 생성
		//2. 부트스트랩을 적용해 게시글 목록을 화면에 표시
	}
	
	// 아래 주소로 데이터를 보내줄수 있는 form을 작성
	// register.jsp 파일명으로 작성
	// @GetMapping으로 register.jsp에 접근할 수 있는
	// 컨트롤러 메서드도 아래에 작성
	
	@PostMapping("/register") //Post방식으로만 접속 허용
	public String register(BoardVO vo, RedirectAttributes rttr) {
		// 글 썻으면 상세페이지나 혹은 글 목록으로 이동시켜야 함
		// 1. 글쓰는 로직 실행후, 
		service.register(vo);
		// 2. list 주소로 강제로 이동을 시킴
		// 이동 시킬떄 몇 번 글을 썬느지 안내해주는 로직을 추가
		// addFlashAttribute는 redirect시에 컨트롤러에서
		// .jsp파일로 데이터를 보내줄 때 사용
		// model.addAttribute()를 쓴다면
		// 일반 이동이 아닌 redirect 이동시는 데이터가 소실됨
		// 이를 막기 위해 rttr.addFlashAttribute로 대체
		log.info("vo : " + vo);
		rttr.addFlashAttribute("bno", vo.getBno());
		
		// viewes폴더 하위 board폴더의 list.jsp 출력
		// redirect로 이동할 때는 redirect:파일명
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public void register() {//오버로딩
		
	}
	
	// 상세 페이지 조회는 Long bno에 적힌 글 번호를 이용해서 합니다.
	// /get?파라미터명=글번호 형식을 ㅗ가져옴
	// /get 을 주소로 getmapping을 사용하는 메서드 get을 만들기
	//service에서 get()을 호출해 가져온 글 하나의 정보를 
	// get.jsp로 보내기
	@GetMapping("/get")
	public String get(Long bno, Model model){
		
		log.info("form에서 받은 데이터 : " + bno);
		log.info("form에서 받은 데이터 타입 : " + bno.getClass());
		
		
		model.addAttribute("getboard", service.get(bno));
		
		return "/board/get";
		
	}
	
	// get방식으로 삭제를 허용하면 매크로 등을 이용해서
	// 마음대로 글 삭제하는 경우가 생길 수 있으므로
	// 무조건 삭제 버튼을 클릭해서 삭제할 수 있도록
	// post 방식 접근만 허용
	// bno를 받아서 삭제하고, 삭제 후에는 "success"라는 문자열을
	// .jsp로 보내주기
	// 삭제가 완료되면 redirect 기능을 이용해 list페이지로 넘어가게
	// 코드를 내부에 작성
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		log.info("form에서 받은 bno" + bno);
		service.remove(bno);
		rttr.addFlashAttribute("result", "success");
		rttr.addFlashAttribute("bno", bno);
		
		return "redirect:/board/list";
	}
	// 수정로직도 post방식으로 진행
	// /modify를 주소로 하고, 사용자가 수정할 수 있는 요소들을
	// BoardVO로 받아서 수정한 다음 수정한 글의 디테일페이지로 넘어오면 된다
	// 수정 후는 디테일페이지로 redirect
	@PostMapping("/modify")
	public String modify(BoardVO vo, RedirectAttributes rttr) {
		log.info("form에서 받은 vo" + vo);
		service.modify(vo);
		
		return "redirect:/board/get?bno=" + vo.getBno();
		
	}
	// 글을 수정할 때는 modify.jsp를 이용해 수정해야함
	// PostMapping을 이용해서 /boardmodify로 접속시 수정 폼으로 접근
	// 수정 폼은 register.jsp와 비슷한 양식으로 작성되어있지만
	// 해당 글이 몇 번인지에 대한 정보도 화면에 표출
	// 글쓴이는 readonly를 걸어서 수정 불가하게 만들기
	// 아래 메서드는 수정 폼으로 접근하도록 만들고
	// 수정 폼에는 내가 수정하고자 하는 글의 정보를 먼저 받아오고
	// model.addAttribute로 정보를 .jsp로 보내서 폼을 채우기
	@PostMapping("/boardmodify")
	public String modifyForm(Long bno, Model model) {
		
		//아무 글 번호나 하나를 입력해서 해당 글 정보를 얻어오는 로직
		BoardVO vo = service.get(bno);
		log.info(vo);
		
		model.addAttribute("getboard", vo);
		return "/board/modify";
	}
	
	
	
	
}
