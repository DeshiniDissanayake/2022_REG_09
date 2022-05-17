package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class StockController {
	@Autowired
	private StockRepository stockRepository;

	@GetMapping("/stocks")
	public List<Stock> getAllStocks() {
		return stockRepository.findAll();
	}

	@GetMapping("/stock/{id}")
	public ResponseEntity<Stock> getStockById(@PathVariable(value = "id") Long stockId)
			throws ResourceNotFoundException {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + stockId));
		return ResponseEntity.ok().body(stock);
	}

	@PostMapping("/Stocks")
	public Stock createStock(@RequestBody Stock stock) {
		return stockRepository.save(stock);
	}

	@PutMapping("/stocks/{id}")
	public ResponseEntity<Stock> updateStock(@PathVariable(value = "id") Long stockId,
			  @RequestBody Stock stockDetails) throws ResourceNotFoundException {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + stockId));

		stock.setId(stockDetails.getId());
		stock.setName(stockDetails.getName());
		stock.setAmount(stockDetails.getAmount());
		final Stock updatedStock = stockRepository.save(stock);
		return ResponseEntity.ok(updatedStock);
	}

	@DeleteMapping("/stocks/{id}")
	public Map<String, Boolean> deleteStock(@PathVariable(value = "id") Long stockId)
			throws ResourceNotFoundException {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + stockId));

		stockRepository.delete(stock);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
