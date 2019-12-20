package com.bigdata2019.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata2019.guestbook.dao.GuestbookDao;
import com.bigdata2019.guestbook.vo.GuestbookVo;

public class GuestbookServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("a");
		if ("form".equals(action)) {
			// 포워딩
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
		} 
		else if ("insert".equals(action)) {
			request.setCharacterEncoding("UTF-8");
			
//			String no = request.getParameter("no");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");
			//String regDate = request.getParameter("regDate;");
			GuestbookVo vo = new GuestbookVo();
//			vo.setNo(Long.parseLong(no));
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			//vo.setRegDate(regDate);
		
			new GuestbookDao().insert(vo);
			response.sendRedirect("/guestbook02/gb");

		} 
		else if ("deleteform".equals(action)) {
			request.setCharacterEncoding("UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);

		}
		else if ("delete".equals(action)) {
			request.setCharacterEncoding("UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/delete.jsp");
			rd.forward(request, response);

		}
		
		else {
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.findAll();

			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
			
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
