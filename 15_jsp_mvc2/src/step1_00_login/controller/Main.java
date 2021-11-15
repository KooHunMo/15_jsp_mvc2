package step1_00_login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/main.do")
public class Main extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();  //세션을 키고 연결한다
		
		String id = (String)session.getAttribute("id");  // 세션에 getAttribute를 사용하여 id를 넣고 String id에 넣는다
		request.setAttribute("id", id);
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_loginEx/01_main.jsp"); // 그것을 메인 페이지로 포워딩해준다.
		dis.forward(request, response); // 포워딩 코드
		
	}

}
