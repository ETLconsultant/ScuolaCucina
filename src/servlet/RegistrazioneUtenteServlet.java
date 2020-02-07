package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Utente;
import exceptions.ConnessioneException;
import exceptions.DAOException;
import service.UtenteService;
import service.UtenteServiceImpl;
import validator.ErroreValidazione;
import validator.Validatore;


@WebServlet("/regUtente")
public class RegistrazioneUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		//invocazione al validatore per il controllo dei campi
		List<ErroreValidazione> lista = Validatore.validazioneUtente(request);
		if(lista.size()!=0){
			request.setAttribute("lista", lista );
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/registraUtente.jsp").forward(request, response);
		}
		
		

		UtenteService serviceU;
		serviceU = new UtenteServiceImpl();

		Utente u = this.getUtenteFromQueryString(request);


		try {
			serviceU.registrazioneUtente(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("user", u);
		getServletContext().getRequestDispatcher("/registrazioneUtenteOk.jsp").forward(request, response);


	}

	private Utente getUtenteFromQueryString(HttpServletRequest request){
		String idGenerico= request.getParameter("username");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String dataNascita = request.getParameter("dataNascita");

		//		String giorno = request.getParameter("giorno");
		//		String mese = request.getParameter("mese");
		//		String anno = request.getParameter("anno");
		//		LocalDate l = LocalDate.of(Integer.parseInt(anno), Integer.parseInt(mese), Integer.parseInt(giorno));
		//		Date d = java.util.Date.from(l.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


		try {
			date = sdf.parse(dataNascita);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");

		boolean adm = Boolean.valueOf(request.getParameter("admin"));
//		HttpSession sessione = request.getSession();
//		sessione.setAttribute("admin", adm);
		System.out.println("stampa: " + adm);
		System.out.println(new Utente(idGenerico, password, nome, cognome, date, email, telefono, adm));

		return new Utente(idGenerico, password, nome, cognome, date, email, telefono, adm);












	}

}
