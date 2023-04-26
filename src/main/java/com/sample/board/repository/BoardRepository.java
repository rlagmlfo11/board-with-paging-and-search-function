package com.sample.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

	Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable );

}
