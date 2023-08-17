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

    //==수정 메서드==//
    // 댓글 내용 업데이트, 수정 일시를 현재 시각으로 업데이트
    public void updateChannel(String newChannel) {
        this.CHANNEL_CONTENTS = newChannel;
        this.MODIFY_DATE = LocalDateTime.now();
    }



    //==삭제 메서드==//
    // 댓글 삭제, 삭제 일시를 현재 시각으로 업데이트
    public void deleteChannel() {
        this.DELETE_YN = true;
        this.MODIFY_DATE = LocalDateTime.now();
    }



    //==조회 메서드==//
    // 삭제된 댓글인지 Y/N
    public Boolean isDeleted() {
        return DELETE_YN;
    }
    public String getTitle() {
        return CHANNEL_TITLE;
    }

    public String getChannel() {
        return CHANNEL_CONTENTS;
    }

    public String getPhotos() {
        return CHANNEL_PHOTO;
    }

    public LocalDateTime getCreateDate() {
        return CREATE_DATE;
    }

    public LocalDateTime getModifyDate() {
        return MODIFY_DATE;
    }
}
