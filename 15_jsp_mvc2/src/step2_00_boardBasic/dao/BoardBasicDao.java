package step2_00_boardBasic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import step2_00_boardBasic.dto.BoardBasicDto;

public class BoardBasicDao {

	private BoardBasicDao() {}
	private static BoardBasicDao instance = new BoardBasicDao();
	public static BoardBasicDao getInstance() {
		return instance;
	}

	Connection conn         = null;
	PreparedStatement pstmt = null;
	ResultSet rs            = null;

	public Connection getConnection() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 전체 게시글을 조회하는 DAO
	public ArrayList<BoardBasicDto> getAllBoard() {

		ArrayList<BoardBasicDto> boardList = new ArrayList<BoardBasicDto>();
		BoardBasicDto model = null;
		
		try {
			
			conn  = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD");
			rs    = pstmt.executeQuery();
			
			while (rs.next()) {

				model = new BoardBasicDto();
				model.setNum(rs.getInt("NUM"));
				model.setWriter(rs.getString("WRITER"));
				model.setEmail(rs.getString("EMAIL"));
				model.setSubject(rs.getString("SUBJECT"));
				model.setPassword(rs.getString("PASSWORD"));
				model.setRegDate(rs.getDate("REG_DATE").toString());
				model.setReadCount(rs.getInt("READ_COUNT"));
				model.setContent(rs.getString("CONTENT"));

				boardList.add(model); // boardList를 select(조회)할때만 model을 넣고 데이터 수정 할 때는 model을 사용하지 않는다 - DB 전체 줄을 담아놓을 그릇이 model이기 때문이다
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return boardList;
		
	}
	
	// 한개의 게시글을 조회하는 DAO
	public BoardBasicDto getOneBoard(int num) {

		BoardBasicDto model = new BoardBasicDto();

		try {
			
			conn = getConnection();

			pstmt = conn.prepareStatement("UPDATE BOARD SET READ_COUNT=READ_COUNT+1 WHERE NUM=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate(); // 업데이트를 먼저하고

			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();	// 조회를 나중에 한다 따라서 pstmt가 꽉 차있기 때문에 model을 만들어서 DB를 넣고 가져오는 것이다.
			
			if (rs.next()) {
				model.setNum(rs.getInt("NUM"));
				model.setWriter(rs.getString("WRITER"));
				model.setEmail(rs.getString("EMAIL"));
				model.setSubject(rs.getString("SUBJECT"));
				model.setPassword(rs.getString("PASSWORD"));
				model.setRegDate(rs.getDate("REG_DATE").toString());
				model.setReadCount(rs.getInt("READ_COUNT"));
				model.setContent(rs.getString("CONTENT"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return model; // 모델을 반환하여 Dto에 넣는다 -> 다음 조회때 이미 정보가 저장되어 있을 수 있도록!
		
	}

	// 업데이트할 데이터를 조회하는 DAO
	public BoardBasicDto getOneUpdateBoard(int num) {

		BoardBasicDto model = new BoardBasicDto();

		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				model.setNum(rs.getInt(1));
				model.setWriter(rs.getString(2));
				model.setEmail(rs.getString(3));
				model.setSubject(rs.getString(4));
				model.setPassword(rs.getString(5));
				model.setRegDate(rs.getDate(6).toString());
				model.setReadCount(rs.getInt(7));
				model.setContent(rs.getString(8));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return model;
		
	}

	
	// 비밀번호가 일치하는지 검증하는 DAO
	public boolean validMemberCheck(BoardBasicDto boardDTO) {

		boolean isValidMember = false;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement( "SELECT * FROM BOARD WHERE NUM=? AND PASSWORD=?");
			pstmt.setInt(1, boardDTO.getNum());
			pstmt.setString(2, boardDTO.getPassword());
			rs = pstmt.executeQuery();

			if (rs.next()) 	isValidMember = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}

		return isValidMember;
		
	}
	
	// 게시글을 추가하는 DAO
	public void insertBoard(BoardBasicDto bdto) {

		try {
			
				conn = getConnection();
				pstmt = conn.prepareStatement("INSERT INTO BOARD(WRITER,EMAIL,SUBJECT,PASSWORD,REG_DATE,READ_COUNT,CONTENT) VALUES(?, ?, ?, ?, now(), 0, ?)");
				pstmt.setString(1, bdto.getWriter());
				pstmt.setString(2, bdto.getEmail());
				pstmt.setString(3, bdto.getSubject());
				pstmt.setString(4, bdto.getPassword());
				pstmt.setString(5, bdto.getContent());
				pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}	
		
	}
	
	// 게시글을 수정하는 DAO
	public boolean updateBoard(BoardBasicDto bdto) {

		boolean isUpdate = false;
		
		try {
			
			if (validMemberCheck(bdto)) {
				conn = getConnection();
				pstmt = conn.prepareStatement("UPDATE board SET SUBJECT=?, CONTENT=? WHERE NUM=?");
				pstmt.setString(1, bdto.getSubject());
				pstmt.setString(2, bdto.getContent());
				pstmt.setInt(3, bdto.getNum());
				pstmt.executeUpdate();
				isUpdate = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return isUpdate;
		
	}

	// 게시글을 삭제하는 DAO
	public boolean deleteBoard(BoardBasicDto bdto) {

		boolean isDelete = false;
		
		try {
			
			if (validMemberCheck(bdto)) {
				conn = getConnection();
				pstmt = conn.prepareStatement("DELETE FROM BOARD WHERE NUM=?");
				pstmt.setInt(1, bdto.getNum());
				pstmt.executeUpdate();
				isDelete = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return isDelete;
		
	}
	
	
}
