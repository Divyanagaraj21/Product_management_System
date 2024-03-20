package com.jsp.spring.pmsm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.spring.pmsm.dto.ProductRequest;
import com.jsp.spring.pmsm.model.Product;
import com.jsp.spring.pmsm.service.ProductService;
import com.jsp.spring.pmsm.utility.ErrorStructure;
import com.jsp.spring.pmsm.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class ProductController {

	private ProductService productService;
	public ProductController(ProductService productService)
	{
		this.productService=productService;
	}
	
	@Operation(description = "The endpoint is used to add a new product to the database",responses= {
			@ApiResponse(responseCode = "200",description = "Product saved successFully"),
			@ApiResponse(responseCode = "400",description = "Invalid inputs")
	})

	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody ProductRequest productRequest)
	{
		return productService.saveProduct(productRequest);
	}

	@PutMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable int productId,@RequestBody ProductRequest updatedProductRequest)
	{
		return productService.updateProduct(productId, updatedProductRequest);
	}

	@DeleteMapping("products/{productId}")
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@PathVariable int productId)
	{
		return productService.deleteProduct(productId);
	}
	
	@Operation(description = "The endpoint is used to fetch the product based on ID",responses= {
			@ApiResponse(responseCode = "200",description = "Product Found"),
			@ApiResponse(responseCode = "404",description = "Product not found by given ID",content= {
					@Content(schema = @Schema(implementation = ErrorStructure.class))
					
			})
	})
	@GetMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable int productId){

		return productService.findProductById(productId);
	}

	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct(){

		return productService.findAllProduct();
	}




}