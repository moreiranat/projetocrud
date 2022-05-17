package br.edu.ifpb.dac.nataly.projetocrud2.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.nataly.projetocrud2.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);

}
