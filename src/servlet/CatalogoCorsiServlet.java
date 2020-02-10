package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Corso;
import exceptions.ConnessioneException;
import exceptions.DAOException;
import service.CorsoService;
import service.CorsoServiceImpl;


/**
 * Servlet implementation class CalendarioCorsi
 */
@WebServlet("/CatalogoCorsi")
public class CatalogoCorsiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CorsoServiceImpl corsoService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogoCorsiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sessione=request.getSession();
		String idUtente = (String) sessione.getAttribute("username");
		
		try {
			corsoService = new CorsoServiceImpl();
		} catch (ConnessioneException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<Corso> corsi= new ArrayList<Corso>();
		try {
			corsi = corsoService.visualizzaCatalogoCorsi();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("corsi", corsi);
		RequestDispatcher rd= request.getRequestDispatcher("/catalogo.jsp");
		rd.forward(request, response);
		
	}


}
