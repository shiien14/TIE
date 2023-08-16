package CornSalad.TIE.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review_tag {
    @Id
    @GeneratedValue
    @Column(name = "TAG_ID")
    private Long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_ID")
    private Review Review;
    private String TAG_CONTENTS;
    // private int TAG_COUNT;
}
