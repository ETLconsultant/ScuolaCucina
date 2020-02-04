package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

import service.UtenteServiceImpl;

/**
 * Servlet implementation class UpdateUtenteServlet
 */
@WebServlet("/UpdateUtenteServlet")
public class UpdateUtenteServlet extends HttpServlet {
	
	Utente ub = new Utente();
	UtenteServiceImpl us = new UtenteServiceImpl();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUtenteServlet() {
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
		
		
	    List<validator.ErroreValidazione> lista = validator.Validatore.validazioneUtente(request);
		
			if(lista.size()!=0){
				request.setAttribute("lista", lista );
				System.out.println(lista);
				getServletContext().getRequestDispatcher("/updateDati.jsp").forward(request, response);
				return;
			}
			
	        HttpSession sessione = request.getSession();
			
	        String idUtente = (String) sessione.getAttribute("id_utente");
			
			String cognome = request.getParameter("cognome");
			String password = request.getParameter("password");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String telefono = request.getParameter("telefono");
			
			
			SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy"); 
			Date dataNascita = null;
			try {
				dataNascita = formatter1.parse(request.getParameter("dataNascita"));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		
			
			
			//setto il nuovo utente
			ub.setIdUtente(idUtente);
			ub.setCognome(cognome);
			ub.setPassword(password);
			ub.setNome(nome);
			ub.setDataNascita(dataNascita);
			ub.setTelefono(telefono);
			ub.setEmail(email);


					try {
							us.modificaDatiUtente(ub);
						
							sessione.setAttribute("id_utente", ub.getIdUtente());
							sessione.setAttribute("nome", ub.getNome());
							sessione.setAttribute("cognome", ub.getCognome());
							sessione.setAttribute("password", ub.getPassword());
							sessione.setAttribute("email", ub.getEmail());
							sessione.setAttribute("dataNascita", ub.getDataNascita());
							sessione.setAttribute("telefono", ub.getTelefono());
							
							request.setAttribute("msg", "Aggiornamento dei dati avvenuto con successo!");
							RequestDispatcher rd =request.getRequestDispatcher("/areaPersonale.jsp"); 
							rd.forward(request, response);

					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}

	

}
