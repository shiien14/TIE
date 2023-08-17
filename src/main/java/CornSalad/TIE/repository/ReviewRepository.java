package CornSalad.TIE.repository;

import CornSalad.TIE.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@Transactional
@RequiredArgsConstructor
public class ReviewRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Review createReview(User user, Channel channel, String contents, Review_tag... review_tag) {
        Review review = new Review();
        review.setUser(user);
        review.setChannel(channel);
        review.setREVIEW_CONTENTS(contents);
        review.setCREATE_DATE(LocalDateTime.now());
        for (Review_tag Review_tag : review_tag) {
            review.addReviewTag(Review_tag);
        }
        entityManager.persist(review);
        return review;
    }

//    public Review updateReview(Review review, String newContents) {
//        review.setREVIEW_CONTENTS(newContents);
//        review.setMODIFY_DATE(LocalDateTime.now());
//        entityManager.merge(review);
//        return review;
//    }

    public void deleteReview(Review review) {
        review.setDELETE_YN(true);
//        review.setMODIFY_DATE(LocalDateTime.now());
        entityManager.merge(review);
    }

    public Review findById(Long reviewId) {
        return entityManager.find(Review.class, reviewId);
    }
}