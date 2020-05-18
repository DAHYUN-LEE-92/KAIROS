package likelist.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import likelist.service.LikelistService;
import playlist.vo.Playlist;
import user.vo.User;

/**
 * Servlet implementation class LikeListServlet
 */
@WebServlet(name = "LikeList", urlPatterns = { "/likeList" })
public class LikeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User u = (User)session.getAttribute("user");
		RequestDispatcher rd =null;
		if(u==null) {
		  rd=request.getRequestDispatcher("/loginFrm");
		}else {
			ArrayList<Playlist> list = new LikelistService().likeListView(u);
			rd=request.getRequestDispatcher("/WEB-INF/views/myMusic/likeList.jsp");
			request.setAttribute("list", list);
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
