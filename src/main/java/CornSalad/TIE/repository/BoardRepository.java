package CornSalad.TIE.repository;

import CornSalad.TIE.domain.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Board board) {
        if (board.getID() == null) {
            em.persist(board);
        } else {
            em.merge(board);
        }
    }

    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll() {
        return em.createQuery("select m from BOARD m", Board.class).getResultList();
    }

}
