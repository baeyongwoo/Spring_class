package org.ict.controller;


import java.util.List;

import org.ict.domain.ReplyVO;
import org.ict.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replies/*")
public class ReplyController {

	@Autowired
	private ReplyService service;

	// consumes는 이 메서드의 파라미터를 넘겨줄때 어떤 형식으로 넘겨줄지
	// 를 설정하는데 기본적으로 rest방식에서는 json을 사용
	// produces는 입력받은 데이터를 토대로 로직을 실행한 다음
	// 사용자에게 결과를 보여줄 데이터의 형식을 나타냄
	@PostMapping(value = "", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	// produces에 TEXT_PLAIN_VALUE를 줬으므로 결과코드와 문자열을 넘김
	public ResponseEntity<String> register(
			// rest컨트롤러에서 받는 파라미터 앞에
			// @RequestBody를 붙어야
			// consumes와 연결됨
			@RequestBody ReplyVO vo) {

		ResponseEntity<String> entity = null;

		try {
			service.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// 단일 숫자 데이터만 bno만 넣기도 하고
	// PathVariable로 이미 입력데이터가
	// 명시되어있으므로 consumes는 따로 안줘도 됨
	@GetMapping(value = "/all/{bno}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Long bno) {
		ResponseEntity<List<ReplyVO>> entity = null;

		try {
			entity = new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	// 일반 방식이 아닌 rest방식에서는 삭제로직을 post가 아닌
	// delete 방식으로 요청하기 때문에 @deletemapping
	@DeleteMapping(value = "/{rno}", produces = { MediaType.TEXT_PLAIN_VALUE }) // 성공 실패 여부를 알기 위해 medaitype
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		ResponseEntity<String> entity = null;
		try {
			service.removeReply(rno);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(method = { RequestMethod.PUT,
			RequestMethod.PATCH }, value = "/{rno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	
	public ResponseEntity<String> modify(
			// VO는 우선 payload에 적힌 데이터로 받아옴
			// @RequestBody가 붙은 VO는
			// payload에 적힌 데이터를 VO로 환산해서 가져옴
			@RequestBody ReplyVO vo,
			// 단, 댓글번호는 주소에 기입된 숫자를 지원으로 받아옴
			@PathVariable("rno") Long rno) {
		ResponseEntity<String> entity = null;

		try {
			// payload에는 reply만 넣어줘도 되는데 그 이유는
			// rno는 요청주소로 받아오기 때문
			// 단 rno를 주소로 받아오는 경우는 아직 replyVO에
			// rno가 세팅되지 않은 상태이므로 setter로 rno까지 지정
			vo.setRno(rno);
			service.modifyReply(vo);

			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

}
