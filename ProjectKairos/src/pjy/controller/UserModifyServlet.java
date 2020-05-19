package pjy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.service.UserService;
import user.vo.User;

/**
 * Servlet implementation class UserModifyServlet
 */
@WebServlet(name = "UserModify", urlPatterns = { "/userModify" })
public class UserModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = new User();
		u.setUserId(request.getParameter("userId"));
		u.setUserPw(request.getParameter("userPw"));
		u.setUserName(request.getParameter("userName"));
		u.setUserNick(request.getParameter("userNick"));
		u.setPhone(request.getParameter("phone"));
//		String email1 = request.getParameter("mail1");
//		String email2 = request.getParameter("mail2");
//		System.out.println(email2);
//		String email = email1+"@"+email2;
		u.setEmail(request.getParameter("modifyFullMail"));
		String addr1 = request.getParameter("postCode");
		String addr2 = request.getParameter("roadAddr");
		String addr3 = request.getParameter("detailAddr");
		String addr = addr1+"/"+addr2+"/"+addr3;
		u.setAddr(addr);
		System.out.println(u.getUserId());
		System.out.println(u.getUserPw());
		System.out.println(u.getUserName());
		System.out.println(u.getUserNick());
		System.out.println(u.getPhone());
		System.out.println(u.getEmail());
		System.out.println(u.getAddr());
		int result = new UserService().modifyUser(u);
		System.out.println(result);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/mypageFrm");
		if(result>0) {
			request.setAttribute("msg", "수정완료");
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
		}else {
			request.setAttribute("msg", "수정실패");
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
