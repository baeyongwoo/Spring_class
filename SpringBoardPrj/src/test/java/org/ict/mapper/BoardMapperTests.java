package org.ict.mapper;

import javax.inject.Inject;

import org.ict.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// 테스트코드 기본세팅(RunWith, ContextConfiguration, Log4j)해주세요.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		"file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	
	StringBuffer bf = new StringBuffer();
	// 이 테스트코드 내에서는 Mapper테스트를 담당합니다.
	// 따라서 BoardMapper내부의 메서드를 실행할 예정이고
	// BoardMapper 타입의 변수가 필요하니
	// 선언해주시고 자동 주입으로 넣어주세요.
	
	//아래와 같이 inject도 사용이 가능하나, 의존성 관계가 명확한 것은
	//autowired를 권장
	//@Autowired
	@Inject
	private BoardMapper mapper;
	
	// 테스트용 메서드의 이름은 testGetList입니다.
	// 테스트 코드가 실행될 수 있도록 만들어주세요.
	//@Test
	public void testGetList() {
		// mapper 내부의 getList 메서드를 호출하려면?
		log.info(mapper.getList());
	}
	
	//@Test
	public void testinsert() {
		// 글 입력을 위해서 BoardVO 타입을 매개로 사용함
		// 따라서 BoardVO를 만들어놓고
		// setter로 글제목, 글본문, 글쓴이만 저장해둔 채로
		// mapper.insert(vo);를 호출해서 실행여부를 확인하면 됨
		// 위 설명을 토대로 아래 vo에 6번글에 대한 제목 본문 글쓴이를 넣고
		//호출 해준다음 실제로 db에 들어갔는지 확인
		BoardVO vo = new BoardVO();
		
	
		vo.setContent("test1");
		//vo.setTitle(bf.append("test1"));
		vo.setWriter("test1");

		//log.info(vo);
		mapper.insert(vo);
	}
	
	//@Test
	public void testselect() {
		
		mapper.select(3L);
		
	}
	
	// 글 번호(Long bno)를 파라미터로 받아
	// 해당 글 번호에 해당하는 글을 삭제해주는 메서드를 작성
	// 메서드 이름은 delete
	// xml파일에 쿼리문도 작성하고, 테스트 코드까지
	
	//@Test
	public void testdelete() {
		
		mapper.delete(8L);
		log.info("게시글 삭제 완료");
	}
	
	// 글 수정 로직을 작성
	// BoardVO 를 받아서 수정
	// 바꿀 내역은, title, content, writer는 vo에서 받아서
	// updatedate는 sysdate로
	// where 구문은 bno로 구분해서 처리
	
	@Test
	public void testupdate() {
		
		BoardVO vo = new BoardVO();
		vo.setBno(3L);
		//vo.setTitle(bf.replace(0, 100,"테스트용 제목" ));
		vo.setTitle("테스트용 제목");
		vo.setContent("테스트용수정 내용");
		vo.setWriter("테스트자");
		
		log.info(vo);
		
		mapper.update(vo);
		
		
	}
	
	
	
}
