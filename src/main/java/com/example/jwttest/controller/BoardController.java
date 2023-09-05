package com.example.jwttest.controller;

import com.example.jwttest.dto.BoardRequestDto;
import com.example.jwttest.dto.BoardResponseDto;
import com.example.jwttest.entity.Board;
import com.example.jwttest.jwt.JwtUtil;
import com.example.jwttest.service.BoardService;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boards")
    public List<BoardResponseDto> getBoards(){
        return boardService.getBoards();
    }

    @PostMapping("/board")
    public BoardResponseDto create(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String token, @RequestBody BoardRequestDto requestDto){
        System.out.println("requestDto.getContents() = " + requestDto.getContents());
        return boardService.create(token,requestDto);
    }

    @GetMapping("/board/{id}")
    public List<BoardResponseDto> getBoardsByKey(@PathVariable String id){
        return boardService.getBoardsByKey(id);
    }

    @PutMapping("/board/{id}")
    public Board update(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String token, @RequestBody BoardRequestDto requestDto, @PathVariable Long id){
       return boardService.update(token,requestDto,id);
    }

    @DeleteMapping("/board/{id}")
    public String delete(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String token,  @PathVariable Long id){
        return boardService.delete(token,id);
    }
}
