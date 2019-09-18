package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import dao.NewsDAO;
import models.News;


@WebServlet("/news")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static NewsDAO newsDAO;
	private static News news;
	private static ObjectMapper objectMapper;
	private static PrintWriter out;
	private static String jsonString;

       
    public NewsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		newsDAO = new NewsDAO();
		objectMapper = new ObjectMapper();
		String params = request.getQueryString();  
		if (params != null) {
			String sid=request.getParameter("id");
			int id=Integer.parseInt(sid); 
			Optional<News> news = newsDAO.get(id);
			jsonString = news.isPresent() 
					? objectMapper.writeValueAsString(news.get())
					: String.valueOf(HttpServletResponse.SC_NOT_FOUND);
		} else {
			List<News> news = newsDAO.getAll();
			jsonString = objectMapper.writeValueAsString(news);
		}
	    out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        out.print(jsonString);
        out.flush();   
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		objectMapper = new ObjectMapper();
		news = objectMapper.readValue(request.getInputStream(), News.class);
		newsDAO = new NewsDAO();
		newsDAO.add(news);
		out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        out.print(HttpServletResponse.SC_CREATED);
        out.flush();   
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		objectMapper = new ObjectMapper();
		news = objectMapper.readValue(request.getInputStream(), News.class);
		String sid=request.getParameter("id");  
        int id = Integer.parseInt(sid);  
        news.setId(id);
		newsDAO = new NewsDAO();
		newsDAO.update(news);
		out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        out.print(HttpServletResponse.SC_NO_CONTENT);
        out.flush();   
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid=request.getParameter("id");  
        int id = Integer.parseInt(sid); 
        newsDAO = new NewsDAO();
		newsDAO.delete(id);
		out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        out.print(HttpServletResponse.SC_NO_CONTENT);
        out.flush(); 
	}
	

}
