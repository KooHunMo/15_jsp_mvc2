package step1_00_login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import step1_00_login.dao.MemberDao;
import step1_00_login.dto.MemberDto;

@WebServlet("/apply.do")
public class Apply extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");

		if (id != null) {
			MemberDto memberDto = MemberDao.getInstance().getOneMemberInfo(id);
			request.setAttribute("memberDto", memberDto);
		} 
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_loginEx/07_apply.jsp");
		dis.forward(request, response);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		String field = request.getParameter("field");
		String major = request.getParameter("major");

		String[] temp = request.getParameterValues("skill");
		String skill = "";
		for (int i=0; i<temp.length; i++) {
			skill += temp[i];
			if (i != temp.length - 1) {
				skill += ",";
			}
		}
		
		MemberDao.getInstance().apply(id, field, skill, major);
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_loginEx/08_applyAction.jsp");
		dis.forward(request, response);
		
	}

}
