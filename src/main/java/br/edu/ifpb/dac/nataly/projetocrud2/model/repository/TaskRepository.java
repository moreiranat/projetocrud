package br.edu.ifpb.dac.nataly.projetocrud2.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.nataly.projetocrud2.model.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	//Task findByNome(String categoryName);

}
