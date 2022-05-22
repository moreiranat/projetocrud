package br.edu.ifpb.dac.nataly.projetocrud2.presentation.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.nataly.projetocrud2.model.entity.Task;
import br.edu.ifpb.dac.nataly.projetocrud2.model.enums.StatusTask;
import br.edu.ifpb.dac.nataly.projetocrud2.model.repository.TaskRepository;

public class TaskDto {

	private Long id;
	private String description;
	private String priority;
	private Long categoryId;
	private StatusTask status;

	public TaskDto() {

	}

	public TaskDto(Task task) {
		this.id = task.getId();
		this.description = task.getDescription();
		this.priority = task.getPriority();
		this.categoryId = task.getCategory().getId();
		this.status = task.getStatus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public StatusTask getStatus() {
		return status;
	}

	public void setStatus(StatusTask status) {
		this.status = status;
	}

	public static List<TaskDto> converter(List<Task> tasks) {
		return tasks.stream().map(TaskDto::new).collect(Collectors.toList());
	}
}
