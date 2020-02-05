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
	Utente amministratore= new Utente();
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
			request.setAttribute("lista", lista );
			System.out.println(lista);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		request.removeAttribute("nome");
		request.removeAttribute("cognome");
		
		HttpSession session = request.getSession();
		String idUtente = request.getParameter("id_utente");
		String password = request.getParameter("password");
		String idAdmin = request.getParameter("id_amministratore");
		
		
		
		session.setAttribute("messageArea", messageArea);
		session.setAttribute("messageLogin", messageLogin);
	
					
					try {
						if(user.isAdmin()) {
							System.out.println("ma davvero?" + userservice.checkCredenziali(idAdmin, password));
							if(userservice.checkCredenziali(idAdmin, password).equals(user)) {
								System.out.println("errore nel if del try");
								messageLogin="idAmministratore o password errati";
								session.setAttribute("messageLogin", messageLogin);
								RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
								rd.forward(request, response);
								return;
							
						}else {
							System.out.println(" ma davvero?" + userservice.checkCredenziali(idUtente, password));
							if(userservice.checkCredenziali(idUtente, password).equals(user)) {
								System.out.println("errore nel if del try");
								messageLogin="idUtente o password errati";
								session.setAttribute("messageLogin", messageLogin);
								RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
								rd.forward(request, response);
								return;
							}
						}
							
						}
						
					} catch (DAOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}

        	
        
	
	}
}
	
	
