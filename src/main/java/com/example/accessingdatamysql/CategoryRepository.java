package com.example.accessingdatamysql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
	public Category findByName(String name);
	

}
