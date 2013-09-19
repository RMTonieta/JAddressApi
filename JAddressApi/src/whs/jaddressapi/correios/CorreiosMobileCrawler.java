/**
 * 
 */
package whs.jaddressapi.correios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import whs.jaddressapi.base.Address;


/**
 * @author WilliamStenico 17/09/2013
 * 
 * Classe responsável por interagir com o site dos correios Chamado de
 * MOBILE pois a url dos correios é mobile
 */
public class CorreiosMobileCrawler {

	final String url = "http://m.correios.com.br/movel/buscaCepConfirma.do"; 

	List<NameValuePair> postParams;

	
	public CorreiosMobileCrawler() {

		
	}

	public Address getAddress(String addressInput) throws Exception {

		HttpPost post = new HttpPost(url);
		
		postParams = getInputParams(addressInput);

		post.setEntity(new UrlEncodedFormEntity(postParams));

		HttpClient client = HttpClientBuilder.create().build();

		HttpResponse response = client.execute(post);

		//int responseCode = response.getStatusLine().getStatusCode();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		Document doc = Jsoup.parse(result.toString());

		if (doc.select(".erro").size() > 0)
			throw new InvalidParameterException();

		Elements div = doc.select(".respostadestaque");		

		String typeOfStreet = div.eq(0).text().trim().split(" ")[0];

		String[] streetNode = div.eq(0).text().trim().split(" ");
		String street = "";
		for (int i = 0; i < streetNode.length; i++) {
			if (i <= 0)
				continue;
			if (streetNode[i] == "-")
				break;
			street += streetNode[i];
			street += " ";
		}
		street = street.trim();

		String neighborHood = div.eq(1).text().trim();

		String city = div.eq(2).text().trim().split("/")[0].trim();
		String estate = div.eq(2).text().trim().split("/")[1].trim();

		String cep = div.eq(3).text().trim();	

		return new Address(cep, typeOfStreet, street, neighborHood,
				city, estate);		
	}

	/*
	 * Retorna a lista dos parametros usados no site dos correios. No caso o
	 * campo cepEntrada pode ser um endereço por exemplo nomeRua, Cidade
	 */
	public List<NameValuePair> getInputParams(String endereco)
			throws UnsupportedEncodingException {

		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		paramList.add(new BasicNameValuePair("cepEntrada", endereco));
		paramList.add(new BasicNameValuePair("tipoCep", ""));
		paramList.add(new BasicNameValuePair("cepTemp", ""));
		paramList.add(new BasicNameValuePair("metodo", "buscarCep"));

		return paramList;
	}

}
