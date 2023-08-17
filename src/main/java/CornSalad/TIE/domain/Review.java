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

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.USER = user;
        user.getReview().add(this);
    }
    public void setChannel(Channel channel) {
        this.Channel = channel;
        channel.getReview().add(this);
    }

    public void addReviewTag(Review_tag review_tag) {
        Review_tag.add(review_tag);
        review_tag.setReview(this);
    }

    //==생성 메서드==//
    public static Review createReview(User user, Channel channel, Review_tag... review_tag) {
        Review review = new Review();
        review.setUser(user);
        review.setChannel(channel);

        review.setREVIEW_CONTENTS(review.REVIEW_CONTENTS);

        for (Review_tag Review_tag : review_tag) {
            review.addReviewTag(Review_tag);
        }

        review.setCREATE_DATE(LocalDateTime.now());

        return review;
    }


    //==삭제 메서드==//
    // 댓글 삭제, 삭제 일시를 현재 시각으로 업데이트
    public void deleteReview() {
        this.DELETE_YN = true;
    }


    //==조회 메서드==//
    // 삭제된 댓글인지 Y/N
    public Boolean isDeleted() {
        return DELETE_YN;
    }

    public String getContents() {
        return REVIEW_CONTENTS;
    }

    public LocalDateTime getCreateDate() {
        return CREATE_DATE;
    }

}