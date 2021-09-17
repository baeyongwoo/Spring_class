package org.ict.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

//기본 테스트 셋팅
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	{"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
@WebAppConfiguration	//웹 사이트 모의접속용 아노테이션
public class BoardControllerTests {
	
	// 아래 나오는 MockMvc를 만들기 위해 생성하는 객체
	@Autowired
	private WebApplicationContext ctx;
	
	//브라우저 없이 모의로 접속하는 기능을 가진 객체
	private MockMvc mockMvc;
	
	//@Test 이전에 실행할 내용을 기술하는 아노테이션
	//주의 ! org.junit.before로 입력
	@org.junit.Before
	public void setup() {
		//객체를 빌드하겠다?
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	//@Test
	public void testList() throws Exception{
		
		log.info(
				// .get(접속주소)/.post(접속주소)를 제외한 나머지는 다 고정
				// .get(접속주소)를 입력하면 get방식으로 해당 주소에 접속
				mockMvc.perform(
				MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
		
	}
	// /board/register 주소로 파라미터값을 post방식으로 넘겼을떄
	// 글이 써지는지 안써지는지 테스트
	//@Test
	public void testRegister() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "테스트코드제목")
				.param("content", "테스트코드본문")
				.param("writer", "테스트코드글쓴이")
				).andReturn().getModelAndView().getViewName();
		
		//변수에 저장된 값을 다시 로깅해서 출력함
		log.info(resultPage);
	}
	
	// .param("bno", "글번호")로 파라미터를 줬을떄
	// 해당 글이 잘 얻어와있는지 체크
	// 참고로 .param()으로 전달하는 자료는 자료형을 막론하고 무조건
	// " "로 감싸서 문자화 시켜야하는데 이유는
	// url에는 자료형 구분이 없고 오직 String뿐이기때문
	//@Test
	public void testGet() throws Exception{
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/board/get?")
				.param("bno", "3")
				).andReturn().getModelAndView().getViewName();
		
		log.info(result);
		log.error("bno가 공백입니다.");
	}
	
	//@Test
	public void remove() throws Exception{
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "15")
				).andReturn().getModelAndView().getViewName();
		
		log.info(result);
	}
	
	@Test
	public void testModify() throws Exception{
		// 실제로 실행될 쿼리문과 비교해서 데이터를 날려주면 됨
		// 현재 수정로직은 bno를 Where 조건으로,
		// title, content, wirter를 수정내역으로 받았으니
		// 파라미터도 위 4개 항목을 전달
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "4")
				.param("title", "수정로직")
				.param("content", "수정로직테스트")
				.param("writer", "수정자")
				).andReturn().getModelAndView().getViewName();
		
		log.info(result);
	}
	
}
