package com.example.demo.form;

/**　Articleフォームの内容を格納
 * @author kaki0
 *
 */
public class ArticleForm {
	private String name;
	private String content;
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
	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}
	
	

}
