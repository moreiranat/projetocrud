package br.edu.ifpb.dac.nataly.projetocrud2.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.nataly.projetocrud2.model.entity.Task;
import br.edu.ifpb.dac.nataly.projetocrud2.model.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Task save(Task task) {
		return taskRepository.save(task);
	}
	
	public Task update(Task task) {
		return taskRepository.save(task);
	}
	
	public Task update(Long id) {
		Task taskSave = taskRepository.getById(id);
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}		
		return taskRepository.save(taskSave);
	}
	
	public void delete(long id) {
		
		Optional<Task> task = findById(id);
		
		if(task == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%1", id));
		}
		
		taskRepository.deleteById(id);
	}
	
	public Optional<Task> findById(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		
		return taskRepository.findById(id);
	}
	
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	public List<Task> find(Task filter) {
		Example example = Example.of(filter, ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return taskRepository.findAll(example);
	}
	
}
