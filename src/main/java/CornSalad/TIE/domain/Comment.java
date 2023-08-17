package CornSalad.TIE.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


//효정 수정 버전
@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User USER;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post POST;

    private String COMMENT_CONTENTS;
    private LocalDateTime CREATE_DATE;
    private LocalDateTime MODIFY_DATE;
    private Boolean DELETE_YN;

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.USER = user;
        user.getComment().add(this);
    }
    public void setPost(Post post) {
        this.POST = post;
        post.getComment().add(this);
    }

    //==생성 메서드==//
    public static Comment createComment(User user, Post post) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);

        comment.setCOMMENT_CONTENTS(comment.COMMENT_CONTENTS);

        comment.setCREATE_DATE(LocalDateTime.now());
        return comment;
    }

    //==수정 메서드==//
    // 댓글 내용 업데이트, 수정 일시를 현재 시각으로 업데이트
    public void updateComment(String newComment) {
        this.COMMENT_CONTENTS = newComment;
        this.MODIFY_DATE = LocalDateTime.now();
    }


    //==삭제 메서드==//
    // 댓글 삭제, 삭제 일시를 현재 시각으로 업데이트
    public void deleteComment() {
        this.DELETE_YN = true;
        this.MODIFY_DATE = LocalDateTime.now();
    }



    //==조회 메서드==//
    // 삭제된 댓글인지 Y/N
    public Boolean isDeleted() {
        return DELETE_YN;
    }

    public String getContents() {
        return COMMENT_CONTENTS;
    }

    public LocalDateTime getCreateDate() {
        return CREATE_DATE;
    }

    public LocalDateTime getModifyDate() {
        return MODIFY_DATE;
    }
}
