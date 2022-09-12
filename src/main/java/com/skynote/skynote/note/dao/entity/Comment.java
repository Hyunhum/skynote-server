package com.skynote.skynote.note.dao.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
@Table(name = "comment")
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", unique = true)
    private Long commentId;

    @Column(name = "comment_user_wallet_address")
    @NotBlank
    private String userWalletAddress;

    @Column(name = "comment_content")
    @NotBlank
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;
    /* 객체의 불변성이 비밀번호 변경 시 불가피하게 깨지므로,
       유지보수 등을 위해 수정되는 시점을 기록해야 함 */
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateAt;

    @JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "note_id", updatable = false, nullable = false)
    private Note note;


    @Builder
    public Comment(String userWalletAddress, String content) {
        this.content = content;
        this.userWalletAddress = userWalletAddress;
    }
    
    public void setParent(Note note) {
        this.note = note;
    }

    public void changeContent(String content) {
        this.content = content;
    }

}
