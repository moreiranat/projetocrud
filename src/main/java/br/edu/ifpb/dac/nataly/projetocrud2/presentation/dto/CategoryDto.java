package br.edu.ifpb.dac.nataly.projetocrud2.presentation.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.nataly.projetocrud2.model.entity.Category;

public class CategoryDto {

	private Long id;
	private String name;

	public CategoryDto() {

	}

//	public CategoryDto(Long id, String name) {
//		this.id = id;
//		this.name = name;
//	}

	public CategoryDto (Category category) {
		this.id = category.getId();
		this.name = category.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static List<CategoryDto> converter(List<Category> categories) {
		return categories.stream().map(CategoryDto::new).collect(Collectors.toList());
	}

}
