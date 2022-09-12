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
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findByNoteId(long noteId);
        
}
