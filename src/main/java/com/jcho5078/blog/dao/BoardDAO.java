package com.jcho5078.blog.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jcho5078.blog.vo.BoardNumVO;
import com.jcho5078.blog.vo.BoardVO;
import com.jcho5078.blog.vo.CommVO;
import com.jcho5078.blog.vo.PageVO;

public interface BoardDAO {
	
	//게시판 리스트 출력
	public List<BoardVO> BoardList(BoardNumVO vo);
	//게시글 카운팅
	public int getCount();
	//선택한 게시판 내용 출력
	public BoardVO selectBoard(int bdnum);
	//게시글 작성
	public void insertBoard(BoardVO vo);
	//게시글 삭제(유저)
	public void deleteBoardUser(BoardVO vo);
	//게시글 삭제(게스트)
	public void deleteBoard(BoardVO vo);
	//게시판 조회수
	public void countView(int bdnum);
	//댓글 리스트 출력
	public List<CommVO> CommList(int bdnum);
	//댓글 카운트
	public int countComm(int bdnum);
	//댓글 작성
	public void insertComm(CommVO vo);
	//게시글에 보여줄 댓글갯수
	public void insertBoardCommCount(int bdnum);
	//게시글 댓글갯수 카운트(댓글 삭제시)
	public void insertBoardCommCountMin(int bdnum);
	//댓글 번호 증가를 위해 현재 게시글의 댓글 번호 최대값 가져오기
	public int getMaxCommNo(int bdnum);
	//댓글 삭제(게스트)
	public void deleteComm(CommVO vo);
	//댓글 삭제(유저)
	public void deleteCommUser(CommVO vo);
	//파일 업로드
	public void insertFile(Map<String, Object> map);
	//파일 조회
	public List<Map<String, Object>> viewFile(int bdnum) throws Exception;
	//파일 다운로드
	public Map<String, Object> downloadFile(Map<String, Object> map) throws Exception;
}
