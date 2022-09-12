package com.skynote.skynote.note.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class NoteRequestDto {

    @NotBlank
    private long noteId;

    @NotBlank
    private String userWalletAddress;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public NoteRequestDto(
        long noteId, String userWalletAddress,
        String title, String content) {
        
        this.noteId = noteId;
        this.userWalletAddress = userWalletAddress;
        this.title = title;
        this.content = content;
        
    }
    
}
