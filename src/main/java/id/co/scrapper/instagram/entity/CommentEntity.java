package id.co.scrapper.instagram.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "CommentEntity")
@Table(name = "comment")
@Data
public class CommentEntity {
	@Id
	private String commentId;
	
	@Column(name = "text", length = 2000)
	private String text;
	
	@Column(name = "created_at")
	private String createdAt;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "post_id")
	private String postId;

}
