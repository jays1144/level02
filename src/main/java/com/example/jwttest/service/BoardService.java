package com.example.jwttest.service;

import com.example.jwttest.dto.BoardRequestDto;
import com.example.jwttest.dto.BoardResponseDto;
import com.example.jwttest.entity.Board;
import com.example.jwttest.jwt.JwtUtil;
import com.example.jwttest.repository.BoardRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;
    public List<BoardResponseDto> getBoards() {
        return boardRepository.findAllByOrderByCreateAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    public BoardResponseDto create(String token, BoardRequestDto requestDto) {
        String username = tokenCheck(token);

        Board board = new Board(requestDto,username);

        Board saveBoard = boardRepository.save(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);

        return boardResponseDto;


    }

    public List<BoardResponseDto> getBoardsByKey(String id) {
        return boardRepository.findAllByTitleContainsOrderByModifiedAtDesc(id).stream().map(BoardResponseDto::new).toList();
    }



    @Transactional
    public Board update(String token, BoardRequestDto requestDto,Long id) {
       String username = tokenCheck(token);
       Board board = findBoard(id,username);
       board.update(requestDto,username);

       return board;
    }


    public String delete(String token, Long id) {
        String user = tokenCheck(token);
        Board board = findBoard(id,user);
        boardRepository.delete(board);
        return "삭제";
    }




    public String tokenCheck(String token){
        String subedToken = jwtUtil.substringToken(token);

        if(!jwtUtil.validateToken(subedToken)){
            throw new IllegalArgumentException("토큰문제 발견");
        }

        Claims info = jwtUtil.getUserInfoFromToken(subedToken);

        String username = info.getSubject();
        System.out.println("username = " + username);

        return username;

    }



    private Board findBoard(Long id,String username){
        return boardRepository.findByIdAndUsername(id,username);
    }



}
