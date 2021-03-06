package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Chat;
import beans.Message;

/**
 * Servlet implementation class ServletChat
 */
@WebServlet("/ServletChat")
public class ServletChat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletChat() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ServletChat.java doGet() in if");
			
		request.getRequestDispatcher("/WEB-INF/chat.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
			System.out.println("ServletChat.java doPost() in if");
			// doGet(request, response);
			Chat.getInstance().setDateSession(new Date());

			// R�cup�ration des param�tres
			String nom = request.getParameter("nom");
			String message = request.getParameter("msg");
			Date d = new Date();

			// Ajout de l'utilisateur � la session si null et affecte le message
			Chat.getInstance().ajoutMessageUtilisateurChat(nom, message, d);

			// R�cup�ration des messages de la session et trie des messages par
			// date
			Map<String, Message> m = Chat.getInstance()
					.sortHashMap((HashMap<String, Message>) Chat.getInstance().getMessagesSession());

			request.setAttribute("dicoUserMessage", m);
			request.getRequestDispatcher("/WEB-INF/chat.jsp").forward(request, response);
			//request.getRequestDispatcher("/chat").forward(request, response);
	
	}

}
