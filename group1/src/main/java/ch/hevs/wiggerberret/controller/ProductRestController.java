package ch.hevs.wiggerberret.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.hevs.wiggerberret.db.Product;
import ch.hevs.wiggerberret.db.ProductRepository;

/*
 * REST Web Service Controller
 */

@RestController
@RequestMapping("/products") 
public class ProductRestController {
	
	@Autowired
	private ProductRepository repo;
	
	//Simple getHello Method
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello(){
		return "hello";
	}
	
	//Get Product by ID
	@RequestMapping(method=RequestMethod.GET, value="{id}")
	public Product get(@PathVariable String id) {
		return repo.findOne(id);
	}
	
	//Get All Products
	@RequestMapping(method=RequestMethod.GET)
	public List<Product> getAllProducts() {
		return repo.findAll();
	}
	
	//Get All Products Sorted by Quantity
	@RequestMapping(method=RequestMethod.GET, params = {"quantity"})
	public List<Product> getAllProductsSortedByQuantity(@RequestParam(value = "quantity") String quantity) {
		if(quantity.equals("+quantity"))
			return repo.findAll(new Sort(Sort.Direction.ASC,"quantity"));
		else
			return repo.findAll(new Sort(Sort.Direction.DESC,"quantity"));
	}
	
	//Get All Products by Name
	@RequestMapping(method=RequestMethod.GET, params = {"name"})
	public List<Product> getAllProductsByName(@RequestParam(value = "name") String name) {
		return repo.findByName(name);
	}
	
	//Get All Products by Name and Quantity
	@RequestMapping(method=RequestMethod.GET, params = {"name", "quantity"})
	public List<Product> getAllProductsByNameAndQuantity(@RequestParam(value = "name") String name, @RequestParam(value = "quantity") int quantity) {
		return repo.findByNameAndQuantity(name, quantity);
	}
	
	//Create a new product
	@RequestMapping(method=RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		return repo.save(product);
	}
	
	//Update an existing product
	@RequestMapping(method=RequestMethod.PUT, value="{id}")
	public Product update(@PathVariable String id, @RequestBody Product product) {
		Product update = repo.findOne(id);
		
		update.setIngredients(product.getIngredients());
		update.setName(product.getName());
		update.setNutrients(product.getNutrients());
		update.setPortionQuantity(product.getPortionQuantity());
		update.setPortionUnit(product.getPortionUnit());
		update.setQuantity(product.getQuantity());
		update.setUnit(product.getUnit());
		
		return repo.save(update);
	}
	
	//Delete an existing product
	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public void delete(@PathVariable String id) {
		repo.delete(id);
	}
}
