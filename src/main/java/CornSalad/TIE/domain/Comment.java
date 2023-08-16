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
}
