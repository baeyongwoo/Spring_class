package org.ict.service;

import java.util.List;

import org.ict.domain.ReplyVO;
import org.ict.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	ReplyMapper rm;
	
	@Override
	public void addReply(ReplyVO vo) {
			rm.create(vo);
	}

	@Override
	public List<ReplyVO> listReply(Long bno) {
		return rm.getList(bno); 
	}

	@Override
	public void modifyReply(ReplyVO vo) {

			rm.update(vo);
	}

	@Override
	public void removeReply(Long rno) {
			rm.delete(rno);
		
	}

}
