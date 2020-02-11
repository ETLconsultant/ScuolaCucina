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
 * Servlet implementation class CatalogoCorsiServlet
 */
@WebServlet("/CatalogoCorsiServlet")
public class CatalogoCorsiServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    CorsoService corsoService;
	
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
		ArrayList<Corso> corsi= new ArrayList<Corso>();
		HttpSession sessione=request.getSession();
		String idUtente = (String) sessione.getAttribute("username");
		
		try {
			corsoService = new CorsoServiceImpl();
			corsi = corsoService.visualizzaCatalogoCorsi();
			
		} catch (ConnessioneException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();	
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("corsi", corsi);
		RequestDispatcher rd= request.getRequestDispatcher("/catalogo.jsp");
		rd.forward(request, response);
	}



}
