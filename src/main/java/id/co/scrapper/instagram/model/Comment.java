package id.co.scrapper.instagram.model;

import id.co.scrapper.instagram.entity.CommentEntity;
import lombok.Data;

@Data
public class Comment {
	
	private String id;
	private String text;
	private String createdAt;
	private String username;
	private String userId;
	
	public CommentEntity getEntity() {
		CommentEntity entity = new CommentEntity();
		entity.setCommentId(id);
		entity.setText(text);
		entity.setCreatedAt(createdAt);
		entity.setUserId(userId);
		entity.setUsername(username);
		return entity;
	}
	
}
