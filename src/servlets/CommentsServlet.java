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

import dao.CommentsDAO;
import models.Comments;


@WebServlet("/comments")
public class CommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CommentsDAO commentsDAO;
	private static Comments comments;
	private static ObjectMapper objectMapper;
	private static PrintWriter out;
	private static String jsonString;

       
    public CommentsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		commentsDAO = new CommentsDAO();
		objectMapper = new ObjectMapper();
		String params = request.getQueryString();  
		if (params != null) {
			String sid=request.getParameter("id");
			int id=Integer.parseInt(sid); 
			Optional<Comments> comments = commentsDAO.get(id);
			jsonString = comments.isPresent() 
					? objectMapper.writeValueAsString(comments.get())
					: String.valueOf(HttpServletResponse.SC_NOT_FOUND);
		} else {
			List<Comments> comments = commentsDAO.getAll();
			jsonString = objectMapper.writeValueAsString(comments);
		}
	    out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        out.print(jsonString);
        out.flush();   
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		objectMapper = new ObjectMapper();
		comments = objectMapper.readValue(request.getInputStream(), Comments.class);
		commentsDAO = new CommentsDAO();
		commentsDAO.add(comments);
		out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        out.print(HttpServletResponse.SC_CREATED);
        out.flush();   
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		objectMapper = new ObjectMapper();
		comments = objectMapper.readValue(request.getInputStream(), Comments.class);
		String sid=request.getParameter("id");  
        int id=Integer.parseInt(sid);  
        comments.setId(id);
        commentsDAO = new CommentsDAO();
        commentsDAO.update(comments);
		out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        out.print(HttpServletResponse.SC_NO_CONTENT);
        out.flush();   
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid=request.getParameter("id");  
        int id=Integer.parseInt(sid); 
        commentsDAO = new CommentsDAO();
        commentsDAO.delete(id);
		out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
		out.print(HttpServletResponse.SC_NO_CONTENT);
        out.flush(); 
	}

}
