package com.skynote.skynote.note.service;

import com.skynote.skynote.note.dto.*;

import java.util.List;

public interface NoteService {

    List<NoteResponseDto> getNoteAll(int offset, int limit) throws Exception;

    NoteResponseDto getNoteById(long noteId) throws Exception;

    ResponseDto createNote(NoteRequestDto noteDto) throws Exception;

    ResponseDto updateNote(NoteRequestDto noteDto) throws Exception;

    ResponseDto deleteNote(NoteRequestDto noteDto) throws Exception;

    List<CommentResponseDto> getCommentsByNoteId(long noteId) throws Exception;

    ResponseDto createComment(CommentRequestDto commentDto) throws Exception;

    ResponseDto updateComment(CommentRequestDto commentDto) throws Exception;

    ResponseDto deleteComment(CommentRequestDto commentDto) throws Exception;
}