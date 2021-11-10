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

import com.example.demo.domain.Comment;

/**DBと接続（article)
 * @author kaki0
 *
 */
@Repository
public class CommentRepository {
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER
	=new BeanPropertyRowMapper<>(Comment.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**articleIdをもとにcommentsデータを取得
	 * @param articleId
	 * @returnF
	 */
	public List<Comment> findByArticleId(int articleId){
		String findSql="SELECT * FROM comments WHERE article_id=:articleId;";
		SqlParameterSource param = new MapSqlParameterSource("articleId",articleId);
		List<Comment> commentList= template.query(findSql, param,COMMENT_ROW_MAPPER);
		return commentList;
	}
	
	/**　param内容を挿入
	 * @param comment
	 */
	public void insert(Comment comment) {
		String insertSql="INSERT INTO comments(name,content,article_id) VALUES(:name,:content,:articleId);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		template.update(insertSql, param);
	}
	/**articleIdをもとにデータを削除
	 * @param articleId
	 */
	public void deleteByarticleId(int articleId) {
		String deleteSql="DELETE FROM comments WHERE article_id=:articleId;";
		SqlParameterSource param = new MapSqlParameterSource("articleId",articleId);
		template.update(deleteSql, param);
	}
}
