package id.co.scrapper.instagram.engine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import id.co.scrapper.instagram.model.Comment;
import id.co.scrapper.instagram.model.Hashtag;
import id.co.scrapper.instagram.model.Post;

@Component
public class ScrapperEngine {
	
	public Hashtag buildHashtag(String jsonObject) throws JSONException {
		return buildHashtag(new JSONObject(jsonObject));
	}
	
	public Hashtag buildHashtag(JSONObject jsonObject) throws JSONException {
		JSONObject graphql = jsonObject.getJSONObject("graphql");
		JSONObject hashtag = graphql.getJSONObject("hashtag");
		List<String> shortCodes = getShortCodes(hashtag.getJSONObject("edge_hashtag_to_media"));
		
		Hashtag hashTag = new Hashtag();
		hashTag.setId(hashtag.getString("id"));
		hashTag.setName(hashtag.getString("name"));
		hashTag.setPosts(shortCodes);
		
		return hashTag;
	}
	
	private List<String> getShortCodes(JSONObject jsonObject) throws JSONException {
		JSONArray edges = jsonObject.getJSONArray("edges");
		
		List<String> shortCodes = new ArrayList<>();
		for(int i=0; i<edges.length(); i++) {
			JSONObject edge = edges.getJSONObject(i);
			JSONObject node = edge.getJSONObject("node");
			
			shortCodes.add(node.getString("shortcode"));
		}
		
		return shortCodes;
	}
	
	private List<Comment> getComments(JSONObject json) throws JSONException {
		JSONArray edges = json.getJSONArray("edges");
		List<Comment> comments = new ArrayList<>();
		for(int i=0; i<edges.length(); i++) {
			JSONObject edge = edges.getJSONObject(i);
			JSONObject node = edge.getJSONObject("node");
			
			Comment comment = new Comment();
			comment.setId(node.getString("id"));
			comment.setText(node.getString("text"));
			comment.setCreatedAt(node.getLong("created_at") + "");
			comment.setUserId(node.getJSONObject("owner").getString("id"));
			comment.setUsername(node.getJSONObject("owner").getString("username"));
			
			comments.add(comment);
		}
		
		return comments;
	}
	
	public Post buildPost(JSONObject json) throws JSONException {
		JSONObject graphql = json.getJSONObject("graphql");
		JSONObject shortCodeMedia = graphql.getJSONObject("shortcode_media");
		
		Post post = new Post();
		post.setId(shortCodeMedia.getString("id"));
		post.setShortCode(shortCodeMedia.getString("shortcode"));
		post.setDisplayUrl(shortCodeMedia.getString("display_url"));
		post.setIsVideo(shortCodeMedia.getBoolean("is_video"));
		if(shortCodeMedia.getBoolean("is_video")) {
			post.setVideoUrl(shortCodeMedia.getString("video_url"));
			post.setVideoViewCount(shortCodeMedia.getInt("video_view_count") + "");
		}
		post.setLikes(getLike(shortCodeMedia.getJSONObject("edge_media_preview_like")));
		post.setComments(getComments(shortCodeMedia.getJSONObject("edge_media_to_parent_comment")));
		
		return post;
	}
	
	public Post buildPost(String json) throws JSONException {
		return buildPost(new JSONObject(json));
	}
	
	private String getLike(JSONObject json) throws JSONException {
		return json.getInt("count") + "";
	}

}
