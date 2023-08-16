package CornSalad.TIE.service;

import CornSalad.TIE.domain.Board;
import CornSalad.TIE.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void saveBoard(Board board){
        boardRepository.save(board);
    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public Board findOne(Long boardId){
        return boardRepository.findOne(boardId);
    }

}
