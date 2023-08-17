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
public class ChannelRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Channel createChannel(Board board,String contents, String title, String photo, Review... review) {
        Channel channel = new Channel();
        channel.setBoard(board);
        channel.setCHANNEL_TITLE(title);
        channel.setCHANNEL_PHOTO(photo);
        channel.setCHANNEL_CONTENTS(contents);
        channel.setCREATE_DATE(LocalDateTime.now());
        entityManager.persist(channel);
        return channel;
    }

    public Channel updateChannel(Channel channel, String newContents) {
        channel.setCHANNEL_CONTENTS(newContents);
        channel.setMODIFY_DATE(LocalDateTime.now());
        entityManager.merge(channel);
        return channel;
    }

    public void deleteChannel(Channel channel) {
        channel.setDELETE_YN(true);
        channel.setMODIFY_DATE(LocalDateTime.now());
        entityManager.merge(channel);
    }

    public Channel findById(Long channelId) {
        return entityManager.find(Channel.class, channelId);
    }
}
