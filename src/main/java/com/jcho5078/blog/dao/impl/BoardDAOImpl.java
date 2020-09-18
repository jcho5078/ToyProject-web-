package com.jcho5078.blog.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcho5078.blog.dao.BoardDAO;
import com.jcho5078.blog.vo.BoardNumVO;
import com.jcho5078.blog.vo.BoardVO;
import com.jcho5078.blog.vo.PageVO;

public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<BoardVO> BoardList(BoardNumVO vo) {
		
		List<BoardVO> result = sqlSession.selectList("board.BoardList", vo);
		
		return result;
	}

	@Override
	public BoardVO selectBoard(int bdnum) {
		
		BoardVO result = sqlSession.selectOne("board.selectBoard", bdnum);
		
		return result;
	}

	@Override
	public void insertBoard(BoardVO vo) {
		
		sqlSession.insert("board.insertBoard", vo);
	}

	@Override
	public void deleteBoardUser(BoardVO vo) {
		
		sqlSession.delete("board.deleteBoardUser", vo);
	}
	
	@Override
	public void deleteBoard(BoardVO vo) {
		
		sqlSession.delete("board.deleteBoard", vo);
	}

	@Override
	public int getCount() {
		
		return sqlSession.selectOne("board.boardCount");
	}
}
