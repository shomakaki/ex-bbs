package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Article;
import com.example.demo.domain.Comment;
import com.example.demo.form.ArticleForm;
import com.example.demo.form.CommentForm;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CommentRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;
	

	
	@ModelAttribute
	private ArticleForm setUpArticleForm() {
		ArticleForm articleForm = new ArticleForm();
		return articleForm;
	}
	
	/**ブラウザで入力してもらった内容をDBに格納するメソッド。
	 * Formで受けとった内容をドメインにコピーし、メソッド利用
	 * @param articleForm
	 * @return
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(ArticleForm articleForm) {
		Article article = new Article();
		
		BeanUtils.copyProperties(articleForm, article);
		System.out.println(article);
		articleRepository.insert(article);
		return "redirect:/article/index2";
	}
	/**格納されている内容をすべて表示
	 * @param model
	 * @return
	 */
	@RequestMapping("/index2")
	public String index2(Model model) {
			
		List<Article> articleList = articleRepository.findAll();
		//articlesテーブルのidとcommentsテーブルのarticleIdを紐づけて、CommentListにcommentsテーブルの内容を格納
		
		for(int i=0; i<articleList.size(); i++) {
			Article article = articleList.get(i);
			Integer articleId = article.getId();
			List<Comment> commentList = commentRepository.findByArticleId(articleId);
			article.setCommentList(commentList);
			System.out.println(articleList);
		}
		
		model.addAttribute("articleList", articleList);
		return "result";
		}
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(int id) {
		articleRepository.deleteById(id);
		commentRepository.deleteByarticleId(id);
		return "redirect:/article/index2";
	}
	@Autowired
	private CommentRepository commentRepository;
	
	@ModelAttribute
	private CommentForm setUpCommentForm() {
		CommentForm commentForm = new CommentForm();
		return commentForm;
	}
	
	/**コメント入力の内容をDBに入れる
	 * @param commentForm
	 * @return
	 */
	@RequestMapping("/insertComment")
	public String insertComment(CommentForm commentForm) {
		Comment comment = new Comment();
		
		BeanUtils.copyProperties(commentForm, comment);
		System.out.println(commentForm);
		comment.setArticleId(Integer.parseInt(commentForm.getArticleId()));
		commentRepository.insert(comment);
		return "redirect:/article/index2";
		
	}
	
	
}
