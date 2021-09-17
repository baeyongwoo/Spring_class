package org.ict.service;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.ict.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// Service테스트는 BoardServiceImpl 내부 기능을
// 서버 가동없이 테스트하기 위해 작성
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	// 다형성 원리에 의해서 BoardService로 만들어도
	// BoardviceImpl이 주입됨
	@Autowired
	private BoardService service;
//	@Autowired
//	private test test;
	
	// 서비스가 제대로 주입되었는지 여부만 확인
	//@Test
	public void testExist() {
		log.info(service);
		// assertNotNull은 해당 객체가 주입이 되지 않아 
		// null인 경우 에러를 발생시킴
		assertNotNull(service);
	}
	
	//@Test
	public void testRegister() {
		//register로직이 BoardVO를 필요로 하므로
		//먼저 vo부터 생성해서 자료 입력 후 전달
		BoardVO vo = new BoardVO();
		vo.setTitle("서비스작성글");
		vo.setContent("서비스본문글");
		vo.setWriter("서비스작성자");
		service.register(vo);
		
	}
	
	//@Test
	public void testgetList() {
		service.getList();
		//test.getList();
	}
	
	//@Test
	public void testselect() {
		service.get(3L);
	}
	
	//@Test
	public void testdelete() {
		service.remove(8L);
	}
	
	@Test
	public void testmodify() {
		BoardVO vo = new BoardVO();
		vo.setBno(4L);
		vo.setTitle("서비스테스트");
		vo.setContent("서비스테스트작성");
		vo.setWriter("서비스관리자");
		
		service.modify(vo);
	
	}
	
	
	
}
