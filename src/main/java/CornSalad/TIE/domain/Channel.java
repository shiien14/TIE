package CornSalad.TIE.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//효정 수정 버전
@Entity(name = "CHANNEL")
@Getter
@Setter
public class Channel {
    @Id
    @GeneratedValue
    @Column(name = "CHANNEL_ID")
    private Long ID;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "ADMIN_ID")
    //private Admin Admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board BOARD_ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CH_REC_ID")
    private Channel_recommend Channel_recommend;

    //private Review_tag tag_count; 아직 review tag 만들지 못함

    private String CHANNEL_TITLE;
    private String CHANNEL_CONTENTS;
    private String CHANNEL_PHOTO; // 데이터 타입이 이게 맞을까요?

    //private String CHANNEL_CATEGORY; // board를 받아오는데 필요할까요? _ 필요 없을 거 같아요!

    private LocalDateTime CREATE_DATE;
    private LocalDateTime MODIFY_DATE;
    private Boolean DELETE_YN; // 채널 삭제 여부 필요할까요..?
    private String TAG; // 리스트로 타입변경 ?

    @OneToMany(mappedBy = "Channel")
    private List<Review> Review = new ArrayList<>();

    //==연관관계 메서드==//
    public void setBoard(Board board) {
        this.BOARD_ID = board;
        board.getChannel().add(this);
    }
    public void addReview(Review review) {
        Review.add(review);
        review.setChannel(this);
    }

    //==생성 메서드==//
    public static Channel createChannel(Board board, Review... review) {
        Channel channel = new Channel();
        channel.setBoard(board);
        for (Review Review : review) {
            channel.addReview(Review);
        }
        channel.setCHANNEL_TITLE(channel.CHANNEL_TITLE);
        channel.setCHANNEL_CONTENTS(channel.CHANNEL_CONTENTS);
        channel.setCHANNEL_PHOTO(channel.CHANNEL_PHOTO);

        channel.setCREATE_DATE(LocalDateTime.now());
        return channel;
    }
}
