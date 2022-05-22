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
import br.edu.ifpb.dac.nataly.projetocrud2.business.service.TaskService;
import br.edu.ifpb.dac.nataly.projetocrud2.business.service.impl.ConverterService;
import br.edu.ifpb.dac.nataly.projetocrud2.model.entity.Category;
import br.edu.ifpb.dac.nataly.projetocrud2.model.entity.Task;
import br.edu.ifpb.dac.nataly.projetocrud2.model.enums.StatusTask;
import br.edu.ifpb.dac.nataly.projetocrud2.presentation.dto.TaskDto;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
	private ConverterService converterService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity save(@RequestBody TaskDto dto) {

		try {
			
			if (dto.getCategoryId() == null) {
				throw new IllegalStateException("categoryId cannot be null");
			}	
			
			Long categoryId = dto.getCategoryId();
			Category category = categoryService.findById(categoryId);
			
			if(category == null) {
				throw new IllegalStateException(String.format("Cound not find any category with id=%1", categoryId));
			}
			
			Task entity = converterService.dtoToTask(dto);
			entity.setCategory(category);
			entity = taskService.save(entity);
			dto = converterService.taskToDto(entity);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);
			
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody TaskDto dto) {
		try {
			dto.setId(id);
			Long categoryId = dto.getCategoryId();
			Category category = categoryService.findById(categoryId);

			if (category == null) {
				throw new IllegalStateException(String.format("Cound not find any departament with id=%1", id));
			}

			Task entity = converterService.dtoToTask(dto);
			entity.setCategory(category);
			entity = taskService.update(entity);
			dto = converterService.taskToDto(entity);

			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			taskService.delete(id);

			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity findByFilter(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "priority", required = false) String priority,
			@RequestParam(value = "categoryId", required = false) Long categoryId,
			@RequestParam(value = "status", required = false) StatusTask status

	) {

		try {

			Task filter = new Task();
			filter.setId(id);
			filter.setDescription(description);
			filter.setPriority(priority);
			filter.setStatus(status);

			Category category = categoryService.findById(categoryId);

			if (category == null) {
				throw new IllegalStateException(String.format("Cound not find any category whit id=%1", categoryId));
			}

			filter.setCategory(category);

			List<Task> entities = taskService.find(filter);
			List<TaskDto> dtos = converterService.taskToDto(entities);

			return ResponseEntity.ok(dtos);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/all")
	public List<Task> findAll() throws Exception {

		List<Task> list = taskService.findAll();

		if (list.isEmpty()) {
			throw new Exception("List is empty!");

		} else {
			return taskService.findAll();
		}
	}

}
