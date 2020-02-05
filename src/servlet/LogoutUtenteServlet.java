package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutUtenteServlet
 */
@WebServlet("/LogoutUtenteServlet")
public class LogoutUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String messageLogout=" ";
	String messageArea=" ";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		messageArea= " ";
		messageLogout="Arrivederci, alla prossima!";
		request.setAttribute("messageLogout", messageLogout);
		session.setAttribute("messageArea", messageArea);

		System.out.println("logout effettuato");
		session.invalidate();
		RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
