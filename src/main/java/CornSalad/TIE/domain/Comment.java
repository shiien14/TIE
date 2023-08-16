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
}
