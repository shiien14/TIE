package CornSalad.TIE.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "REVIEW_ID")
    private Long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User USER;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHANNEL_ID")
    private Channel Channel;

    private int LIKE_YN;
    private String REVIEW_CONTENTS;
    private LocalDateTime CREATE_DATE;
    private Boolean DELETE_YN;

    @OneToMany(mappedBy = "Review")
    private List<Review_tag> Review_tag = new ArrayList<>();
}