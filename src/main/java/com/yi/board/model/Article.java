package com.yi.board.model;

import java.util.Date;

public class Article {
	private int article_no;
	private String writer_id;
	private String writer_name;
	private String title;
	private Date regdate;
	private Date moddate;
	private int read_cnt;
	
	public Article() {
		// TODO Auto-generated constructor stub
	}

	
	public Article(int article_no, String writer_id, String writer_name, String title, Date regdate, Date moddate,
			int read_cnt) {
		this.article_no = article_no;
		this.writer_id = writer_id;
		this.writer_name = writer_name;
		this.title = title;
		this.regdate = regdate;
		this.moddate = moddate;
		this.read_cnt = read_cnt;
	}


	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getWriter_name() {
		return writer_name;
	}

	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getModdate() {
		return moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}

	public int getRead_cnt() {
		return read_cnt;
	}

	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
	}

	@Override
	public String toString() {
		return String.format(
				"Article [article_no=%s, writer_id=%s, writer_name=%s, title=%s, regdate=%s, moddate=%s, read_cnt=%s]",
				article_no, writer_id, writer_name, title, regdate, moddate, read_cnt);
	}
}
