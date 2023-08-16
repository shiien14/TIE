package CornSalad.TIE.domain;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//효정 수정 버전
@Entity(name = "BOARD")
@Getter
@Setter

public class Board {
    @Id
    @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long ID;

    @Embedded
    private Board_code BOARD_CODE; // [YouTube, Instagram, TikTok]

    private String BOARD_NAME;

    private LocalDateTime CREATE_DATE;

    private LocalDateTime MODIFY_DATE;

    @Embedded
    private Status USER_STATUS; // [ACTIVE, INACTIVE]

    @OneToMany(mappedBy = "BOARD_ID")
    private List<Channel> Channel = new ArrayList<>();

    @OneToMany(mappedBy = "BOARD_ID")
    private List<Channel_recommend> Channel_recommend = new ArrayList<>();
}
