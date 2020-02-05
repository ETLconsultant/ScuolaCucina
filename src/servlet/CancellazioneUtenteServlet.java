package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Utente;
import exceptions.ConnessioneException;
import exceptions.DAOException;
import service.UtenteServiceImpl;

/**
 * Servlet implementation class CancellazioneUtenteServlet
 */
@WebServlet("/CancellazioneUtenteServlet")
public class CancellazioneUtenteServlet extends HttpServlet {
	
	Utente ub = new Utente();
	UtenteServiceImpl us = new UtenteServiceImpl();
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellazioneUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession sessione = request.getSession();
		
		String idUtente = (String) sessione.getAttribute("id_utente");
		String idAdmin = (String) sessione.getAttribute("id_amministratore");
	
		
		
		
		if(ub.isAdmin()) {
			ub.setIdUtente(idAdmin);
			try {
				us.cancellaRegistrazioneUtente(idAdmin);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("msg1", "Cancellazione avvenuta con successo. Torna presto a trovarci!");
			sessione.invalidate();
			RequestDispatcher rd =request.getRequestDispatcher("/Home.jsp"); 
			rd.forward(request, response); 
		}else {
			ub.setIdUtente(idUtente);
			try {
				us.cancellaRegistrazioneUtente(idUtente);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("msg1", "Cancellazione avvenuta con successo. Torna presto a trovarci!");
			sessione.invalidate();
			RequestDispatcher rd =request.getRequestDispatcher("/Home.jsp"); 
			rd.forward(request, response); 
			
		}
	
		
		

		
	}

}
