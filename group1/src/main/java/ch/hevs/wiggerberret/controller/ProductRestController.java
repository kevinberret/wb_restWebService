package ch.hevs.wiggerberret.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.hevs.wiggerberret.db.Product;
import ch.hevs.wiggerberret.db.ProductRepository;

@RestController
@RequestMapping("/products") 
public class ProductRestController {
	
	@Autowired
	private ProductRepository repo;
		
	@RequestMapping(method=RequestMethod.GET)
	public List<Product> getAllProducts() {
		return repo.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Product> getAllProductsSortedByQuantity() {
		return repo.findAll(new Sort(Sort.Direction.ASC,"quantity"));
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Product> getAllProductsByName(@PathVariable String name) {
		return repo.findByName(name);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Product> getAllProductsByNameAndQuantity(@PathVariable String name,@PathVariable int quantity) {
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
