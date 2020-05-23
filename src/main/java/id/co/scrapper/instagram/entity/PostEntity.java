package id.co.scrapper.instagram.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity(name = "PostEntity")
@Table(name = "post")
@Data
public class PostEntity {
	@Id
	private String postId;
	
	@Column(name = "short_code")
	private String shortCode;
	
	@Column(name = "caption", length = 2000)
	private String caption;
	
	@Column(name = "display_url")
	private String displayUrl;
	
	@Column(name = "video_url")
	private String videoUrl;
	
	@Column(name = "video_view_count")
	private String videoViewCount;
	
	@Column(name = "is_video")
	private Boolean isVideo;
	
	@Column(name = "likes")
	private String likes;
	
	@Column(name = "hashtag_id")
	private String hashtagId;
	
	@Transient
	private List<CommentEntity> comments;
	
}
