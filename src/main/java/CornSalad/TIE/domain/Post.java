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
}
