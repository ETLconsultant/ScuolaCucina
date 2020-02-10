package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Utente;
import exceptions.DAOException;
import validator.*;
import service.*;

/**
 * Servlet implementation class LoginUtenteServlet
 */
@WebServlet("/LoginUtenteServlet")
public class LoginUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtenteService userservice;
	Utente user = new Utente();
	String messageLogin="  ";
	String messageArea=" ";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginUtenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//		// TODO Auto-generated method stub
	//		response.getWriter().append("Served at: ").append(request.getContextPath());
	//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userservice = new UtenteServiceImpl();
		List<ErroreValidazione> lista = Validatore.validazioneUtente(request);

		if(lista.size()!=0){
			request.setAttribute("lista", lista);
			System.out.println(lista);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		} 

		request.removeAttribute("nome");
		request.removeAttribute("cognome");
		System.out.println("Entrato nella servlet login");
		HttpSession session = request.getSession();
		session.setAttribute("messageArea", messageArea);
		session.setAttribute("messageLogin", messageLogin);
		String adm =(String.valueOf(session.getAttribute("admin")));
		String idGenerico = request.getParameter("username");
		String password = request.getParameter("password");

		session.setAttribute("username", idGenerico);
		session.setAttribute("admin", adm);
		user.setIdUtente(idGenerico);
		user.setPassword(password);
		user.setAdmin(Boolean.parseBoolean(adm));

		String submit=request.getParameter("bottone");

		if (submit.equalsIgnoreCase("amministratore")) {
			System.out.println("if submit.equalsIgnoreCase(\"amministratore\") " + submit);
			try {
				if (userservice.checkCredenziali(idGenerico, password)!= null){
					//				messageLogin="idAmministratore o password corretti";
					//				session.setAttribute("messageLogin", messageLogin);
					System.out.println("nell'if di amministratore login5");
					RequestDispatcher rd = request.getRequestDispatcher("/areaPersonaleAdmin.jsp");
					rd.forward(request, response);
					return;

				}else {
					messageLogin="idAmministratore o password errati";
					session.setAttribute("messageLogin", messageLogin);
					RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
					return;
				}
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("else login");
			try {
				if (userservice.checkCredenziali(idGenerico, password)!=null) {
					RequestDispatcher rd = request.getRequestDispatcher("/areaPersonale.jsp");
					rd.forward(request, response);
					return;

				}else {
					messageLogin="idUtente o password errati";
					session.setAttribute("messageLogin", messageLogin);
					RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
					return;

				}
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


	}
}


