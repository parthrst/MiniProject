package com.example.accessingdatamysql;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","items"})
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
private int id;
	@NotNull(message="Category Name is required")
private String name;
	public Category() {
		
	}
	@OneToMany(cascade = {CascadeType.ALL},
            mappedBy="category" ,fetch=FetchType.LAZY)
	private List<Item> items;
	public int getCategory_id() {
		return id;
	}
	public void setCategory_id(int category_id) {
		this.id = category_id;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public String getCategory_name() {
		return name;
	}
	public void setCategory_name(String category_name) {
		this.name = category_name;
	}
	public void add(Item temp) {
		if(items==null)
		{
			items=new ArrayList<>();
		}
		items.add(temp);
		temp.setCategory(this);
		
	}
}
