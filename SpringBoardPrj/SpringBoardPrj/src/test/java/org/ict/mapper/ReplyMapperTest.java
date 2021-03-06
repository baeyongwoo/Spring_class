package org.ict.mapper;

import org.ict.domain.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		"file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTest {
	
	@Autowired
	private ReplyMapper rm;
	
	@Test
	public void replyinsertTest() {
		
		
		ReplyVO vo = new ReplyVO();
		vo.setBno(18);
		vo.setReply("test");
		vo.setReplyer("test댓글자");
		
		rm.create(vo);
		
	};
}
