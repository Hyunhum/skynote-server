package com.skynote.skynote.note.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

import com.skynote.skynote.note.dao.entity.Comment;

import java.util.List;
import java.util.Date;

@Getter
public class CommentResponseDto {

    @NotBlank
    private long commentId;

    @NotBlank
    private String content;

    @NotBlank
    private String userWalletAddress;

    @NotBlank
    private Date commentCreateAt;

    public CommentResponseDto(long commentId, String content, 
    String userWalletAddress, Date commentCreatedAt) {
        this.commentId = commentId;
        this.content = content;
        this.userWalletAddress = userWalletAddress;
        this.commentCreateAt = commentCreatedAt;
    }

    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
            comment.getCommentId(),
            comment.getContent(),
            comment.getUserWalletAddress(),
            comment.getCreateAt()
            );
    }
    
}
