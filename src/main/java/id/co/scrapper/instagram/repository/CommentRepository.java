package id.co.scrapper.instagram.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.scrapper.instagram.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {

}
