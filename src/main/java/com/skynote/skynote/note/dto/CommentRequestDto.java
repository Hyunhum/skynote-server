package com.skynote.skynote.note.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank
    private long noteId;

    @NotBlank
    private long commentId;

    @NotBlank
    private String userWalletAddress;

    @NotBlank
    private String content;

    public CommentRequestDto(
        long noteId, long commentId, 
        String userWalletAddress, String content) {
        
        this.noteId = noteId;
        this.commentId = commentId;
        this.userWalletAddress = userWalletAddress;
        this.content = content;
        
    }
    
}
