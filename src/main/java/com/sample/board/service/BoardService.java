package com.sample.board.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sample.board.entity.Board;
import com.sample.board.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public void write(Board board, MultipartFile file) throws Exception {
		String projectPath = System.getProperty("user.dir")
				+ "//src//main//resources//static//files";
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + "_" + file.getOriginalFilename();
		java.io.File saveFile = new java.io.File(projectPath, fileName);
		file.transferTo(saveFile);
		board.setFilename(fileName);
		board.setFilepath("/files/" + fileName);
		boardRepository.save(board);
	}

	public Page<Board> boardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	public Board getBoardById(Integer id) {
		Board result = boardRepository.findById(id).get();
		return result;
	}

	public void deleteBoardById(Integer id) {
		boardRepository.deleteById(id);
	}

	public Page<Board> searchList(String searchKeyword, Pageable pageable) {
		return boardRepository.findByTitleContaining(searchKeyword, pageable);
	}

}
