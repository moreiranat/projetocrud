package br.edu.ifpb.dac.nataly.projetocrud2.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.edu.ifpb.dac.nataly.projetocrud2.model.enums.StatusTask;

@Entity
public class Task implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String priority;
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	@Enumerated(EnumType.STRING)
	private StatusTask status;
	
	public Task() {
		
	}
	
	public Task(Long id, String description, String priority, Category category, StatusTask status) {
		this.id = id;
		this.description = description;
		this.priority = priority;
		this.category = category;
		this.status = status;
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public StatusTask getStatus() {
		return status;
	}
	public void setStatus(StatusTask status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(category, description, id, priority, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(category, other.category) && Objects.equals(description, other.description) 
				&& Objects.equals(id, other.id) && Objects.equals(priority, other.priority) 
				&& status == other.status;
	}

	
	
	
}
