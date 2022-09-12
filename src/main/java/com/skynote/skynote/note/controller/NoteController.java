package com.skynote.skynote.note.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;

import com.skynote.skynote.note.service.*;
import com.skynote.skynote.note.dto.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(
        NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<NoteResponseDto>> getNoteAll(
        @RequestParam int offset, @RequestParam int limit
    ){

        try {

            return ResponseEntity.ok()
            .body(
                noteService.getNoteAll(offset, limit)
                );

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    @GetMapping("{noteId}")
    public ResponseEntity<NoteResponseDto> getNoteById(
        @PathVariable long noteId
    ){

        try {

            return ResponseEntity.ok()
            .body(
                noteService.getNoteById(noteId)
                );

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    @PostMapping("/creation")
    public ResponseEntity<ResponseDto> createNote(
        @RequestBody NoteRequestDto noteDto){

        try {

            return ResponseEntity.ok()
            .body(
                noteService.createNote(noteDto)
                );

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    
    @PostMapping("/update")
    public ResponseEntity<ResponseDto> updateNote(
        @RequestBody NoteRequestDto noteDto){

        try {

            return ResponseEntity.ok()
            .body(
                noteService.updateNote(noteDto)
                );

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }
    
    @PostMapping("/deletion")
    public ResponseEntity<ResponseDto> deleteNote(
        @RequestBody NoteRequestDto noteDto){

        try {

            return ResponseEntity.ok()
            .body(
                noteService.deleteNote(noteDto)
                );

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    @GetMapping("{noteId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByNoteId(
        @PathVariable long noteId) {
            try {

                return ResponseEntity.ok()
                .body(
                    noteService.getCommentsByNoteId(noteId)
                    );
    
            } catch (Exception e) {
    
                e.printStackTrace();
                return null;
    
            }    
        }
    
    @PostMapping("/comment/creation")
    public ResponseEntity<ResponseDto> createComment(
        @RequestBody CommentRequestDto commentDto) {
            try {

                return ResponseEntity.ok()
                .body(
                    noteService.createComment(commentDto)
                    );
    
            } catch (Exception e) {
    
                e.printStackTrace();
                return null;
    
            }    
        }

    @PostMapping("/comment/update")
    public ResponseEntity<ResponseDto> updateComment(
        @RequestBody CommentRequestDto commentDto) {
            try {

                return ResponseEntity.ok()
                .body(
                    noteService.updateComment(commentDto)
                    );
    
            } catch (Exception e) {
    
                e.printStackTrace();
                return null;
    
            }    
        }

    @PostMapping("/comment/deletion")
    public ResponseEntity<ResponseDto> deleteComment(
        @RequestBody CommentRequestDto commentDto) {
            try {

                return ResponseEntity.ok()
                .body(
                    noteService.deleteComment(commentDto)
                    );
    
            } catch (Exception e) {
    
                e.printStackTrace();
                return null;
    
            }    
        }

}
