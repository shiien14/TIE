package CornSalad.TIE.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "MEMBER")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long ID;
    private String USER_NAME;
    private String USER_NICKNAME;
    private String USER_PASSWORD;
    private String USER_EMAIL;
//    private String USER_GENDER;
//    private String USER_BIRTH_YEAR;

    @Embedded
    private Status USER_STATUS; // [ACTIVE, INACTIVE]
    private Board_code USER_SNS; // 사용자 SNS 리스트 [YouTube, Instagram, TikTok]

    @OneToMany(mappedBy = "USER")
    private List<Comment> Comment = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Hash_tag TAG_ID; // 이부분 보완필요.. hashtag 단방향 연관을 만들고싶어요 ㅠㅅㅠ

    private Boolean ADMIN_YN;
    // 관리자 여부를 확인하고 회원과 관리자를 구분헤 로그인 및 관리
    // 저희 관리자 계정 화면도 없으니까 이렇게 하는 게 좋을 것 같아요!
}