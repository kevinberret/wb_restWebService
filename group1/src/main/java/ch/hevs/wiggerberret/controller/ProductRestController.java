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

@RestController
@RequestMapping("/products") 
public class ProductRestController {
	
	@Autowired
	private ProductRepository repo;
		
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello(){
		return "hello";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Product> getAllProducts() {
		return repo.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, params = {"sort"})
	public List<Product> getAllProductsSortedByQuantity(@RequestParam(value = "quantity") String quantity) {
		if(quantity.equals("+quantity"))
			return repo.findAll(new Sort(Sort.Direction.ASC,"quantity"));
		else
			return repo.findAll(new Sort(Sort.Direction.DESC,"quantity"));
	}
	
	@RequestMapping(method=RequestMethod.GET, params = {"name"})
	public List<Product> getAllProductsByName(@RequestParam(value = "name") String name) {
		return repo.findByName(name);
	}
	
	@RequestMapping(method=RequestMethod.GET, params = {"name", "quantity"})
	public List<Product> getAllProductsByNameAndQuantity(@RequestParam(value = "name") String name, @RequestParam(value = "quantity") int quantity) {
		return repo.findByNameAndQuantity(name, quantity);
	}
	  
	@RequestMapping(method=RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		return repo.save(product);
	}
	  
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
	  
	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public void delete(@PathVariable String id) {
		repo.delete(id);
	}
}
