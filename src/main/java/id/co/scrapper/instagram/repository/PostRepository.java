package id.co.scrapper.instagram.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.scrapper.instagram.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {

}
