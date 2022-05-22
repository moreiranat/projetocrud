package br.edu.ifpb.dac.nataly.projetocrud2.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.nataly.projetocrud2.business.service.CategoryService;
import br.edu.ifpb.dac.nataly.projetocrud2.business.service.TaskService;
import br.edu.ifpb.dac.nataly.projetocrud2.model.entity.Category;
import br.edu.ifpb.dac.nataly.projetocrud2.model.entity.Task;
import br.edu.ifpb.dac.nataly.projetocrud2.presentation.dto.CategoryDto;
import br.edu.ifpb.dac.nataly.projetocrud2.presentation.dto.TaskDto;

@Service
public class ConverterService {

	@Autowired
	private TaskService taskService;
	@Autowired
	private CategoryService categoryService;

	public List<TaskDto> taskToDto(List<Task> tasks) {
		List<TaskDto> tasksDto = new ArrayList<>();

		for (Task task : tasks) {
			TaskDto dto = taskToDto(task);
			tasksDto.add(dto);
		}
		return tasksDto;
	}

	public TaskDto taskToDto(Task task) {

		TaskDto dto = new TaskDto(task);
		
		dto.setId(task.getId());
		dto.setDescription(task.getDescription());
		dto.setPriority(task.getPriority());
		dto.setStatus(task.getStatus());
		dto.setCategoryId(task.getCategory().getId());
		
		return dto;
	}

	public Task dtoToTask(TaskDto dto) {
		
		//Optional<Category> category = categoryService.findById(dto.getCategoryId()); 

		Task task = new Task();

		task.setId(dto.getId());
		task.setDescription(dto.getDescription());
		task.setPriority(dto.getPriority());
		task.setStatus(dto.getStatus());
		task.setCategory((categoryService.findById(dto.getCategoryId())));
//		task.setCategoryId(dto.getCategory().getId()); 
		
		return task;
	}

	public List<CategoryDto> categoryToDto(List<Category> categories) {
		List<CategoryDto> categoriesDto = new ArrayList<>();

		for (Category category : categories) {
			CategoryDto dto = categoryToDto(category);
			categoriesDto.add(dto);
		}
		return categoriesDto;
	}

	public CategoryDto categoryToDto(Category category) {
		CategoryDto dto = new CategoryDto();
		
		dto.setId(category.getId());
		dto.setName(category.getName());

		return dto;
	}

	public Category dtoToCategory(CategoryDto dto) {

		Category category = new Category();

		category.setId(dto.getId());
		category.setName(dto.getName());

		return category;
	}

}
