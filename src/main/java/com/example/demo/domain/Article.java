package com.example.demo.domain;

import java.util.List;

/**Articleドメインを作成
 * 
 * @author kaki0
 *
 */
public class Article {
	private Integer id;
	private String name;
	private String content;

	/**記事ドメインにてコメントの内容を利用するために
	 * 
	 */
	private List<Comment> commentList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", commentList=" + commentList + "]";
	}


	
	
	
}
