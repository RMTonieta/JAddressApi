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
import org.junit.Test;

import whs.jaddressapi.base.Address;
import whs.jaddressapi.exceptions.AddressNotFoundException;


/**
 * @author WilliamStenico 17/09/2013
 * 
 * Classe responsável por interagir com o site dos correios 
 * Chamado de MOBILE pois a url dos correios é mobile
 * 
 */
public class CorreiosMobileCrawler {

	final String url = "http://www.buscacep.correios.com.br/sistemas/buscacep/resultadoBuscaCepEndereco.cfm"; 

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
				.getEntity().getContent(),"ISO-8859-1"));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		Document doc = Jsoup.parse(result.toString());

		if (doc.select(".erro").size() > 0)
			throw new InvalidParameterException();

		Elements div = doc.select(".tmptabela");
		
		Elements tableRowElements = div.select(":not(thead) tr");
		
//		Debug do tableRowElements
//        for (int i = 0; i < tableRowElements.size(); i++) {
//           Element row = tableRowElements.get(i);
//           Elements rowItems = row.select("td");
//           for (int j = 0; j < rowItems.size(); j++) {
//        	  System.out.println(j + "==>");
//              System.out.println(rowItems.get(j).text());
//           }
//           System.out.println();
//        }
		
		if (tableRowElements == null || tableRowElements.isEmpty()) {
			throw new AddressNotFoundException();
		}
		
		//charactere que persiste ao final das pesquisas e que não é espaço
		String stringWeirdFinalSpace = " ";
		
		Elements address = tableRowElements.get(1).select("td");
        
		String typeOfStreet = address.get(0).text().split(" ", 2)[0].trim();
		String street = address.get(0).text().split(" ", 2)[1].trim().replace(stringWeirdFinalSpace, "");
		String neighborHood = address.get(1).text().trim().replace(stringWeirdFinalSpace, "");
		String city = address.get(2).text().split("/")[0].trim().replace(stringWeirdFinalSpace, "");
		String estate = address.get(2).text().split("/")[1].trim().replace(stringWeirdFinalSpace, "");
		String cep = address.get(3).text().trim().replace(stringWeirdFinalSpace, "");
		
		return new Address(cep, typeOfStreet, street, neighborHood, city, estate);
		    		
	}

	/*
	 * Retorna a lista dos parametros usados no site dos correios. No caso o
	 * campo cepEntrada pode ser um endereço por exemplo nomeRua, Cidade
	 */
	public List<NameValuePair> getInputParams(String endereco)
			throws UnsupportedEncodingException {

		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		paramList.add(new BasicNameValuePair("relaxation", endereco));
		paramList.add(new BasicNameValuePair("tipoCep", "ALL"));
		paramList.add(new BasicNameValuePair("semelhante", "N"));
		

		return paramList;
	}
	
	@Test
	public void test(){
		try {
			System.out.println(getAddress("13171805"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
