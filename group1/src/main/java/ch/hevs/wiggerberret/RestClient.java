package ch.hevs.wiggerberret;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.ws.Response;

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
		/*
		 * Wtf ?
		 */
	}
	
	public static void put(String idProduct){
		/*
		 * Wtf ?
		 */
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
