package com.skynote.skynote.note.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

import com.skynote.skynote.note.dao.entity.Note;

import java.util.Date;
import java.util.stream.Collectors;

@Getter
public class NoteResponseDto {

    @NotBlank
    private long noteId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String userWalletAddress;

    @NotBlank
    private Date noteCreateAt;

    public NoteResponseDto(long noteId, String title, String content,
    String userWalletAddress, Date noteCreateAt) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
        this.userWalletAddress = userWalletAddress;
        this.noteCreateAt = noteCreateAt;
    }

    public static NoteResponseDto from(Note note) {
        return new NoteResponseDto(
            note.getNoteId(),
            note.getTitle(), 
            note.getContent(),
            note.getUserWalletAddress(),
            note.getCreateAt()/*,
            note.getComments().stream()
            .map(comment -> CommentResponseDto.from(comment))
            .collect(Collectors.toList())*/
            );
    }
    
}
