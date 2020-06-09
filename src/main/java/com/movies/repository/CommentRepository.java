package com.movies.repository;

import com.movies.entity.dao.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> getAllByFilm_IdAndStatusTrueOrderByTimeCreateDesc(Integer filmId);
}
