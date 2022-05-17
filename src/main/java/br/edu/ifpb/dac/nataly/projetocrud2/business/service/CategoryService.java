package br.edu.ifpb.dac.nataly.projetocrud2.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.nataly.projetocrud2.model.entity.Category;
import br.edu.ifpb.dac.nataly.projetocrud2.model.entity.Task;
import br.edu.ifpb.dac.nataly.projetocrud2.model.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	public Category update(Category task) {
		return categoryRepository.save(task);
	}

	public void delete(long id) {
		categoryRepository.deleteById(id);
	}

	public Iterable<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	public Category findById(Long id) {
		return categoryRepository.findById(id).get();
	}

	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	public List<Category> find(Category filter) {
		Example example = Example.of(filter, ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return categoryRepository.findAll(example);
	}
	
}
