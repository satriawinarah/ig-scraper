package id.co.scrapper.instagram.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.co.scrapper.instagram.engine.ScrapperEngine;
import id.co.scrapper.instagram.entity.CommentEntity;
import id.co.scrapper.instagram.entity.HashtagEntity;
import id.co.scrapper.instagram.entity.PostEntity;
import id.co.scrapper.instagram.model.Hashtag;
import id.co.scrapper.instagram.model.Post;
import id.co.scrapper.instagram.repository.CommentRepository;
import id.co.scrapper.instagram.repository.HashtagRepository;
import id.co.scrapper.instagram.repository.PostRepository;
import id.co.scrapper.instagram.service.ScrapperService;

@Service
public class ScrapperServiceImpl implements ScrapperService {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ScrapperEngine scrapperUtil;
	
	@Autowired
	private HashtagRepository hashtagRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public void processHashtag(String hashtag) {
		try {
			String url = String.format("https://www.instagram.com/explore/tags/%s/?__a=1", hashtag);
			log.info("Fixed url : {}", url);
			
			
			HttpEntity<String> result = restTemplate.getForEntity(url, String.class);
			Hashtag hashTag = completePostInHashtag(scrapperUtil.buildHashtag(result.getBody()));
			
			log.error("Result : {}", hashTag);
			save(hashTag.getEntity());
		} catch(JSONException e) {
			log.error("Error found: {}", e);
		}
	}
	
	@Override
	public void processPost(String shortCode) {
		try {
			Post post = getPostByShortCode(shortCode);
			log.info("Post : {}", post);
		} catch(JSONException e) {
			log.error("Error found : {}", e);
		}
	}
	
	private Hashtag completePostInHashtag(Hashtag hashtag) throws JSONException {
		List<Post> newPosts = new ArrayList<>();
		for(Post post: hashtag.getPosts()) {
			newPosts.add(getPostByShortCode(post.getShortCode()));
		}
		
		hashtag.setNewPosts(newPosts);
		
		return hashtag;
	}
	
	private Post getPostByShortCode(String shortCode) throws JSONException {
		String url = String.format("https://www.instagram.com/p/%s/?__a=1", shortCode);
		log.info("Fixed url: {}", url);
		
		HttpEntity<String> result = restTemplate.getForEntity(url, String.class);
		return scrapperUtil.buildPost(result.getBody());
	}
	
	private void save(HashtagEntity hashtag) {
		HashtagEntity hashtagEntity = hashtagRepository.save(hashtag);
		
		for(PostEntity postEntity: hashtag.getPosts()) {
			postEntity.setHashtagId(hashtagEntity.getId());
			PostEntity post = postRepository.save(postEntity);
			for(CommentEntity commentEntity: postEntity.getComments()) {
				commentEntity.setPostId(post.getPostId());
				commentRepository.save(commentEntity);
			}
		}
	}

}
