package com.example.jwttest.repository;

import com.example.jwttest.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long>{

    List<Board> findAllByOrderByCreateAtDesc();

    List<Board> findAllByTitleContainsOrderByModifiedAtDesc(String id);

    Board findByIdAndUsername(Long id,String username);
}
