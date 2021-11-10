package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Article;

/**DBと接続（article)
 *
 * @author kaki0
 *
 */
@Repository
public class ArticleRepository {
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER
	=new BeanPropertyRowMapper<>(Article.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**Articleの内容をDBから全件取得
	 * @return
	 */
	public List<Article> findAll(){
		String spl="SELECT * FROM articles ORDER BY id DESC";
		List<Article> articleList = template.query(spl, ARTICLE_ROW_MAPPER);
		return articleList;
		
	}
	/**articlesの内容をDBへ挿入
	 * @param article
	 */
	public void insert(Article article) {
		String insertSql="INSERT INTO articles(name,content) VALUES(:name,:content)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		template.update(insertSql,param);
	}
	/**idをもとにデータを削除
	 * @param id
	 */
	public void deleteById(int id) {
		String deleteSql = "DELETE FROM articles WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource("id",id);
		template.update(deleteSql, param);
		
	}
}
