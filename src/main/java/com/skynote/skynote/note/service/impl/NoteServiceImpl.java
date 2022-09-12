package com.skynote.skynote.note.service.impl;

import com.skynote.skynote.note.service.*;
import com.skynote.skynote.note.dto.*;
import com.skynote.skynote.note.dao.entity.*;
import com.skynote.skynote.note.dao.repo.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.core.ParameterizedTypeReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;

    private CommentRepository commentRepository;

    @Autowired
    public NoteServiceImpl(
        NoteRepository noteRepository,
        CommentRepository commentRepository) {
        this.noteRepository = noteRepository;
        this.commentRepository = commentRepository;
    }

    private Note validateNoteOwner(NoteRequestDto noteDto) throws Exception {

        Note note = noteRepository.findByNoteId(noteDto.getNoteId())
        .orElseThrow(()-> new Exception("등록되지 않은 메모입니다."));

        if (!note.getUserWalletAddress().equals(noteDto.getUserWalletAddress())) {
            throw new Exception("수정 및 삭제 권한이 없는 유저입니다.");
        };

        return note;
    
    }

    
    private Comment validateCommentOwner(CommentRequestDto commentDto) throws Exception {

        Comment comment = commentRepository.findByCommentId(commentDto.getCommentId())
        .orElseThrow(()-> new Exception("등록되지 않은 댓글입니다."));

        if (!comment.getUserWalletAddress().equals(commentDto.getUserWalletAddress())) {
            throw new Exception("수정 및 삭제 권한이 없는 유저입니다.");
        };

        return comment;
    
    }

    @Override
    public List<NoteResponseDto> getNoteAll(int offset, int limit) throws Exception {        
        try {

            List<Note> noteList = noteRepository.findAll(PageRequest.of(offset, limit, Sort.by("noteId").descending())).getContent();

            List<NoteResponseDto> noteResponseList = new ArrayList<>();

            noteList.forEach(note -> noteResponseList.add(NoteResponseDto.from(note)));

            return noteResponseList;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }            
    }

    @Override
    public NoteResponseDto getNoteById(long noteId) throws Exception {
        try{    

            Note note = noteRepository.findById(noteId)
            .orElseThrow(()-> new Exception("존재하지 않는 메모입니다."));

            return NoteResponseDto.from(note);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    
    @Transactional
    @Override
    public ResponseDto createNote(NoteRequestDto noteDto) throws Exception {
        try{    

            Note note = Note.builder()
            .title(noteDto.getTitle())
            .content(noteDto.getContent())
            .userWalletAddress(noteDto.getUserWalletAddress())
            .comments(null)
            .build();

            noteRepository.save(note);

            return new ResponseDto("200", "메모 등록이 완료되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public ResponseDto updateNote(NoteRequestDto noteDto) throws Exception {
        try{    

            Note noteToUpdate = validateNoteOwner(noteDto);

            noteToUpdate.changeTitleAndContent(
                noteDto.getTitle(),
                noteDto.getContent()
            );

            noteRepository.save(noteToUpdate);

            return new ResponseDto("200", "메모 수정이 완료되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public ResponseDto deleteNote(NoteRequestDto noteDto) throws Exception {
        try{    

            noteRepository.delete(
                validateNoteOwner(noteDto));

            return new ResponseDto("200", "메모 삭제가 완료되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<CommentResponseDto> getCommentsByNoteId(long noteId) throws Exception {
        try{    

            List<Comment> comments = commentRepository.findAllByNoteId(noteId);

            List<CommentResponseDto> commentResponseList = new ArrayList<>();

            comments.forEach(comment -> commentResponseList.add(CommentResponseDto.from(comment)));

            return commentResponseList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    
    @Transactional
    @Override
    public ResponseDto createComment(CommentRequestDto commentDto) throws Exception {
        try{    

            Comment comment = Comment.builder()
            .content(commentDto.getContent())
            .userWalletAddress(commentDto.getUserWalletAddress())
            .build();
           
            comment.setParent(
                noteRepository.findByNoteId(
                    commentDto.getNoteId()).get()
            );

            commentRepository.save(comment);

            return new ResponseDto("200", "댓글 등록이 완료되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public ResponseDto updateComment(CommentRequestDto commentDto) throws Exception {
        try{    

            Comment commentToUpdate = validateCommentOwner(commentDto);

            commentToUpdate.changeContent(
                commentDto.getContent()
            );

            commentRepository.save(commentToUpdate);

            return new ResponseDto("200", "댓글 수정이 완료되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public ResponseDto deleteComment(CommentRequestDto commentDto) throws Exception {
        try{    

            commentRepository.delete(
                validateCommentOwner(commentDto));

            return new ResponseDto("200", "댓글 삭제가 완료되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
