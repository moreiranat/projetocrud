package br.edu.ifpb.dac.nataly.projetocrud2.presentation.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.nataly.projetocrud2.business.service.CategoryService;
import br.edu.ifpb.dac.nataly.projetocrud2.business.service.impl.ConverterService;
import br.edu.ifpb.dac.nataly.projetocrud2.model.entity.Category;
import br.edu.ifpb.dac.nataly.projetocrud2.presentation.dto.CategoryDto;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private ConverterService converterService;
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity save(@RequestBody CategoryDto dto) {
		try {
			Category entity = converterService.dtoToCategory(dto);
			entity = categoryService.save(entity);
			dto = converterService.categoryToDto(entity);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);
			
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody CategoryDto dto) {
		try {
			dto.setId(id);
			Category entity = converterService.dtoToCategory(dto);
			entity = categoryService.update(entity);
			dto = converterService.categoryToDto(entity);
			
			return ResponseEntity.ok(dto);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			categoryService.delete(id);
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity find (
				@RequestParam(value = "id", required = false) Long id,
				@RequestParam(value = "name", required = false) String name
			) {
		
		try {
			
			Category filter = new Category();
			filter.setId(id);
			filter.setName(name);
			
			List<Category> entities = categoryService.find(filter);
			List<CategoryDto> dtos = converterService.categoryToDto(entities);
			
			return ResponseEntity.ok(dtos);
			
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
