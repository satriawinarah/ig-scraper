package id.co.scrapper.instagram.model;

import java.util.ArrayList;
import java.util.List;

import id.co.scrapper.instagram.entity.HashtagEntity;
import id.co.scrapper.instagram.entity.PostEntity;
import lombok.Data;

@Data
public class Hashtag {
	
	private String id;
	private String name;
	private List<Post> posts;
	
	public void setPosts(List<String> shortCodes) {
		if(posts==null) {
			posts = new ArrayList<>();
		}
		
		for(String shortCode: shortCodes) {
			posts.add(new Post(shortCode));
		}
	}
	
	public void setNewPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public List<PostEntity> getPostEntities() {
		List<PostEntity> entities = new ArrayList<>();
		for(Post post: posts) {
			entities.add(post.getEntity());
		}
		
		return entities;
	}
	
	public HashtagEntity getEntity() {
		HashtagEntity entity = new HashtagEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setPosts(getPostEntities());
		
		return entity;
	}

}
