package ch.hevs.wiggerberret;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.ws.Response;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.convert.Jsr310Converters.PeriodToStringConverter;

import ch.hevs.wiggerberret.db.Nutrient;

public class RestClient {
	
	private static final String URL = "http://localhost:8080/products";
	private static boolean sortASC = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Simple method to test if REST Client is ok
		 */
		//getHello();
		
		/*
		 * Method to test the creation of a product
		 */
		//post();
		
		/*
		 * Method to test the update of a product
		 */
		//put("idProduit");
		
		/*
		 * Method to test the suppression of a product
		 */
		//delete("592448fa3f92691320836eea");
		
		/*
		 * Method to get products (with sort & filter)
		 */
		//getAll();
		//getAllSortQuantity();
		//getAllByName("Mangue+:+en+tranches");
		//getAllByNameQuantity("Mangue+:+en+tranches",245);
		post();
	}
	
	public static void getHello(){
		try{
			try{
				String newURL = URL.concat("/hello");
				URL url = new URL(newURL);
				URLConnection connection = url.openConnection();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String answer = null;
				
				while((answer = in.readLine()) != null){
					System.out.println("\nWebservice says : "+answer);
				}
				
				in.close();
				
			} catch (Exception e) {
				System.out.println("\nError while calling REST Service");
				System.out.println(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getAll(){
		try{
			try{
				URL url = new URL(URL);
				URLConnection connection = url.openConnection();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String answer = null;
				
				while((answer = in.readLine()) != null){
					System.out.println("\nHere are the webservice's products : "+answer);
				}
				
				in.close();
				
			} catch (Exception e) {
				System.out.println("\nError while calling REST Service");
				System.out.println(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getAllSortQuantity(){
		try{
			try{
				String newURL;
				if(sortASC)
					newURL = URL.concat("?sort=%2Bquantity");
				else
					newURL = URL.concat("?sort=%2Dquantity");
				
				URL url = new URL(newURL);
				URLConnection connection = url.openConnection();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String answer = null;
				
				while((answer = in.readLine()) != null){
					System.out.println("\nHere are the webservice's products sorted by quantity : "+answer);
				}
				
				in.close();
				
			} catch (Exception e) {
				System.out.println("\nError while calling REST Service");
				System.out.println(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getAllByName(String name){
		try{
			try{
				String newURL = URL.concat("?name="+name);
				
				URL url = new URL(newURL);
				URLConnection connection = url.openConnection();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String answer = null;
				
				while((answer = in.readLine()) != null){
					System.out.println("\nHere are the webservice's products filtered by name : "+answer);
				}
				
				in.close();
				
			} catch (Exception e) {
				System.out.println("\nError while calling REST Service");
				System.out.println(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getAllByNameQuantity(String name, int quantity){
		try{
			try{
				String newURL = URL.concat("?name="+name+"&quantity="+quantity);
				
				URL url = new URL(newURL);
				URLConnection connection = url.openConnection();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String answer = null;
				
				while((answer = in.readLine()) != null){
					System.out.println("\nHere are the webservice's products filtered by name and quantity : "+answer);
				}
				
				in.close();
				
			} catch (Exception e) {
				System.out.println("\nError while calling REST Service");
				System.out.println(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void post(){
		JSONObject product = new JSONObject();
		
		Scanner scanString = new Scanner(System.in);
		Scanner scanFigures = new Scanner(System.in);
		
		try {
			// récupération de toutes les valeurs nécessaires
			System.out.println("Entrez le nom du nouveau produit...");
			String name = scanString.nextLine();
			
			System.out.println("Entrez les ingrédients...");
			String ingredients = scanString.nextLine();
			
			System.out.println("Entrez la quantité...");
			int quantity = scanFigures.nextInt();
			
			System.out.println("Entrez l'unité...");
			String unit = scanString.nextLine();
			
			System.out.println("Entrez la quantité de la portion...");
			int portionQuantity = scanFigures.nextInt();
			
			System.out.println("Entrez l'unité de la portion...");
			String portionUnit = scanString.nextLine();
			
			JSONArray nutrients = new JSONArray();
			
			System.out.println("Entrez le nom du nutriment suivant... (exit pour quitter)");
			
			String nutrientName;
			
			while(!(nutrientName = scanString.nextLine()).equals("exit")){
				// creation d'un objet nutrient
				JSONObject nutrient = new JSONObject();			
				
				// récupération de toutes les valeurs nécessaires
				System.out.println("Entrez l'unité...");
				String unitNutrient = scanString.nextLine();
				
				System.out.println("Entrez la proportion en %age...");
				double perHundred = scanFigures.nextDouble();
				
				System.out.println("Entrez la proportion par portion...");
				double perPortion = scanFigures.nextDouble();
				
				System.out.println("Entrez la proportion par jour...");
				int perDay = scanFigures.nextInt();
				
				// ajout de tous les éléments
				nutrient.put("name", nutrientName);
				nutrient.put("unit", unitNutrient);
				nutrient.put("perHundred", perHundred);
				nutrient.put("perPortion", perPortion);
				nutrient.put("perDay", perDay);
				
				// ajout du nutriment dans le tableau de nutrients
				nutrients.put(nutrient);
				
				System.out.println("Entrez le nom du nutriment suivant... (exit pour quitter)");
			}
			
			// ajout de tous les éléments
			product.put("name", name);
			product.put("ingredients", ingredients);
			product.put("quantity", quantity);
			product.put("unit", unit);
			product.put("portionQuantity", portionQuantity);
			product.put("portionUnit", portionUnit);
			product.put("nutrients", nutrients);
			
			System.out.println(product.toString());
			
			scanString.close();
			scanFigures.close();
			
			// Ajout dans la db de l'élément via le webservice
			HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

		    HttpPost request = new HttpPost(URL);
		    StringEntity params =new StringEntity(product.toString(), "UTF-8");
		    request.addHeader("content-type", "application/json;charset=UTF-8");
		    request.setEntity(params);
		    httpClient.execute(request);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void put(String idProduct){
		// Récupération de l'élément souhaité via la méthode get
		String answer = null;	
		
		try{
			String newURL = URL.concat("/"+idProduct);
			
			URL url = new URL(newURL);
			URLConnection connection = url.openConnection();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			answer = in.readLine();
			
			in.close();			
		} catch (Exception e) {
			System.out.println("\nError while calling REST Service");
			System.out.println(e);
		}
		
		if(answer != null){			
			try {
				JSONObject product = new JSONObject(answer);
				
				// Modification des valeurs si nécessaire
				Scanner scanString = new Scanner(System.in);
				Scanner scanFigures = new Scanner(System.in);
				
				// récupération de toutes les valeurs nécessaires et update si désiré par user
				System.out.println("Entrez le nom du produit [" + product.getString("name") + "] (appuyer sur entrée pour conserver la valeur actuelle...)");
				String name = scanString.nextLine();
				name = name.length() == 0 ? product.getString("name") : name;
				
				System.out.println("Entrez les ingrédients [" + product.getString("ingredients") + "] (appuyer sur entrée pour conserver la valeur actuelle...)");
				String ingredients = scanString.nextLine();
				ingredients = ingredients.length() == 0  ? product.getString("ingredients") : ingredients;
				
				System.out.println("Entrez la quantité [" + product.getString("quantity") + "] (appuyer sur entrée pour conserver la valeur actuelle...)");
				int quantity = scanFigures.nextInt();
				quantity = quantity == 0 ? product.getInt("quantity") : quantity;
				
				System.out.println("Entrez l'unité [" + product.getString("unit") + "] (appuyer sur entrée pour conserver la valeur actuelle...)");
				String unit = scanString.nextLine();
				unit = unit.length() == 0  ? product.getString("unit") : unit;
				
				System.out.println("Entrez la quantité de la portion [" + product.getString("portionQuantity") + "] (appuyer sur entrée pour conserver la valeur actuelle...)");
				int portionQuantity = scanFigures.nextInt();
				portionQuantity = portionQuantity == 0 ? product.getInt("portionQuantity") : portionQuantity;
				
				System.out.println("Entrez l'unité de la portion [" + product.getString("portionUnit") + "] (appuyer sur entrée pour conserver la valeur actuelle...)");
				String portionUnit = scanString.nextLine();
				portionUnit = portionUnit.length() == 0  ? product.getString("portionUnit") : portionUnit;
				
				JSONArray nutrientsOld = (JSONArray) product.get("nutrients");
				JSONArray nutrientsNew = new JSONArray();
				
				for (int i = 0; i < nutrientsOld.length(); i++) {
					// récupération du nutrient
					JSONObject nutrient = nutrientsOld.getJSONObject(i);
					
					// récupération de toutes les valeurs nécessaires
					System.out.println("Entrez l'unité [" + nutrient.getString("unitNutrient") + "] (appuyer sur entrée pour conserver la valeur actuelle...)");
					String unitNutrient = scanString.nextLine();
					unitNutrient = unitNutrient.length() == 0 ? nutrient.getString("unitNutrient") : unitNutrient;
					
					System.out.println("Entrez la proportion en %age [" + nutrient.getDouble("perHundred") + "] (appuyer sur entrée pour conserver la valeur actuelle...)");		
					double perHundred = scanFigures.nextDouble();
					perHundred = perHundred == 0 ? nutrient.getDouble("perHundred") : perHundred;
					
					System.out.println("Entrez la proportion par portion [" + nutrient.getString("perPortion") + "] (appuyer sur entrée pour conserver la valeur actuelle...)");				
					double perPortion = scanFigures.nextDouble();
					perPortion = perPortion == 0 ? nutrient.getDouble("perPortion") : perPortion;
					
					System.out.println("Entrez la proportion par jour [" + nutrient.getString("perDay") + "] (appuyer sur entrée pour conserver la valeur actuelle...)");
					int perDay = scanFigures.nextInt();
					perDay = perDay == 0 ? nutrient.getInt("perDay") : perDay;
					
					// ajout de tous les éléments
					nutrient.put("name", name);
					nutrient.put("unit", unitNutrient);
					nutrient.put("perHundred", perHundred);
					nutrient.put("perPortion", perPortion);
					nutrient.put("perDay", perDay);
					
					// ajout du nutriment dans le tableau de nutrients
					nutrientsNew.put(nutrient);
				}			
				
				// ajout de tous les éléments
				product.put("name", name);
				product.put("ingredients", ingredients);
				product.put("quantity", quantity);
				product.put("unit", unit);
				product.put("portionQuantity", portionQuantity);
				product.put("portionUnit", portionUnit);
				product.put("nutrients", nutrientsNew);
				
				System.out.println(product.toString());
				
				scanString.close();
				scanFigures.close();
				
				// Modification dans la db de l'élément via le webservice
			    URL url = new URL(URL);
			    HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			    httpCon.setDoOutput(true);
			    httpCon.setRequestMethod("PUT");
			    httpCon.setRequestProperty("Content-Type", "application/json");
			    OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			    out.write(product.toString());
			    out.close();
			    //httpCon.getInputStream();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	public static void delete(String idProduct){
		try {
			try {
				URL url = new URL(URL.concat("/"+idProduct));
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("DELETE");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
 
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
 
				String response = null;
				while ((response = in.readLine()) != null) {
					System.out.println("\nWebservice says : " + response);
				}
				System.out.println("\nCrunchify REST Service Invoked Successfully..");
				in.close();
			} catch (Exception e) {
				System.out.println("\nError while calling Crunchify REST Service");
				System.out.println(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
