package CornSalad.TIE.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//효정 수정 버전
@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    private Long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User User;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board Board;

    //private Review_tag tag_count; 아직 review tag 만들지 못함

    private String POST_TITLE;
    private String POST_CONTENTS;

    private String POST_PHOTO; // 데이터 타입이 이게 맞을까요? _String

    //private String CHANNEL_CATEGORY; // board를 받아오는데 필요할까요?

    private LocalDateTime CREATE_DATE;
    private LocalDateTime MODIFY_DATE;
    private Boolean DELETE_YN;


    @OneToMany(mappedBy = "POST")
    private List<Hash_tag> Hash_tag = new ArrayList<>();

    @OneToMany(mappedBy = "POST")
    private List<Comment> Comment = new ArrayList<>();


    //==연관관계 메서드==//
    public void setUser(User user) {
        this.User = user;
        user.getPost().add(this);
    }
    public void setBoard(Board board) {
        this.Board = board;
        board.getPost().add(this);
    }

    public void addComment(Comment comment) {
        Comment.add(comment);
        comment.setPost(this);
    }
    public void addHashtag(Hash_tag hash_tag) {
        Hash_tag.add(hash_tag);
        hash_tag.setPOST(this);
    }


    //==생성 메서드==//
    public static Post createPost(User user, Board board, Hash_tag... hash_tag, Comment... comment) {
        Post post = new Post();
        post.setUser(user);
        post.setBoard(board);

        for (Hash_tag Hash_tag : hash_tag) {
            post.addHashtag(Hash_tag);
        }
        for (Comment Comment : comment) {
            post.addComment(Comment);
        }
        post.setPOST_TITLE(post.POST_TITLE);
        post.setPOST_CONTENTS(post.POST_CONTENTS);
        post.setPOST_PHOTO(post.POST_PHOTO);

        post.setCREATE_DATE(LocalDateTime.now());
        return post;
    }

}
