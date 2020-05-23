package id.co.scrapper.instagram.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity(name = "HashtagEntity")
@Table(name = "hashtag")
@Data
public class HashtagEntity {
	@Id
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Transient
	private List<PostEntity> posts;

}
