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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import java.util.Date;

import java.util.List;
import java.util.Collections;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
@Table(name = "note")
@Getter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id", unique = true)
    private Long noteId;

    @Column(name = "note_user_wallet_address")
    @NotBlank
    private String userWalletAddress;

    @Column(name = "note_title")
    @NotBlank
    private String title;

    @Column(name = "note_content")
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

    @JsonManagedReference
	@OneToMany(mappedBy = "note", targetEntity = Comment.class, cascade = CascadeType.ALL)
	private List<Comment> comments;

    @Builder
    public Note(String title, String content, String userWalletAddress, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.userWalletAddress = userWalletAddress;
        if (comments == null) {
			this.comments = Collections.EMPTY_LIST;
		} else {
			this.comments = comments;
			this.comments.forEach(comment -> comment.setParent(this));
		}
    }
    

    public void changeTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
