package org.ict.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ReplyVO {
	private long rno;
	private long bno;
	private String reply;
	private String replyer;
	
	private Date replyDate;
	private Date updatedate;
}
