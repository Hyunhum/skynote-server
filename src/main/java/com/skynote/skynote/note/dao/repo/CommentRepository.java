package com.skynote.skynote.note.dao.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;

import com.skynote.skynote.note.dao.entity.*;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByCommentId(long commentId);

    @Query(value = "SELECT *FROM COMMENT AS C WHERE C.NOTE_ID = ?1", 
            nativeQuery = true)
    List<Comment> findAllByNoteId(long noteId);

}
