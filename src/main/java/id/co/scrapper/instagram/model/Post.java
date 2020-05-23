package id.co.scrapper.instagram.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import id.co.scrapper.instagram.entity.CommentEntity;
import id.co.scrapper.instagram.entity.PostEntity;
import lombok.Data;

@Data
public class Post {
	
	private String id;
	private String shortCode;
	private String caption;
	private String displayUrl;
	private String videoUrl;
	private String videoViewCount;
	private Boolean isVideo;
	private String likes;
	private List<Comment> comments;

	public Post() {
		
	}
	
	public Post(String shortCode) {
		this.shortCode = shortCode;
	}
	
	public List<CommentEntity> getCommentEntities() {
		List<CommentEntity> entities = new ArrayList<>();
		for(Comment comment: comments) {
			entities.add(comment.getEntity());
		}
		
		return entities;
	}
	
	public PostEntity getEntity() {
		PostEntity entity = new PostEntity();
		entity.setPostId(id);
		entity.setShortCode(shortCode);
		entity.setCaption(caption);
		entity.setDisplayUrl(displayUrl);
		entity.setVideoUrl(videoUrl);
		entity.setVideoViewCount(videoViewCount);
		entity.setIsVideo(isVideo);
		entity.setLikes(likes);
		entity.setComments(getCommentEntities());
		
		return entity;
	}
	
}
