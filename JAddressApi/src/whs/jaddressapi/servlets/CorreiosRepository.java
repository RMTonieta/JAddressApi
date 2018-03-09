package whs.jaddressapi.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import whs.jaddressapi.base.Address;
import whs.jaddressapi.correios.CorreiosMobileCrawler;

/**
 * Servlet implementation class CorreiosRepository
 */
@CrossOrigin
@RestController
public class CorreiosRepository extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CorreiosRepository() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String endereco = request.getParameter("end");
			Address returnedAddress = new CorreiosMobileCrawler()
					.getAddress(endereco);

			Map<String, String> options = new LinkedHashMap<String, String>();
			options.put("zipCode", returnedAddress.getZipCode());
			options.put("typeOfStreet", returnedAddress.getTypeOfStreet());
			options.put("street", returnedAddress.getStreet());
			options.put("neighborhood", returnedAddress.getNeighborhood());
			options.put("city", returnedAddress.getCity());
			options.put("estate", returnedAddress.getEstate());

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(options);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		} catch (Exception e) {
			response.getWriter().print("ERRO!!");
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
