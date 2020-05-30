package com.example.accessingdatamysql;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","category"})
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int item_id;
	@NotNull(message="Item Name can not be empty")
	private String itemName;
	private int itemQantity;
	private int itemPrice;
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="category_id", nullable=false)
	private Category category;
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getItem_name() {
		return itemName;
	}
	public void setItem_name(String item_name) {
		this.itemName = item_name;
	}
//	public int getCategory_id() {
//		return category_id;
//	}
//	public void setCategory_id(int category_id) {
//		this.category_id = category_id;
	//}
	public int getItem_quantity() {
		return itemQantity;
	}
	public Item() {
		
	}
	public void setItem_quantity(int item_quantity) {
		this.itemQantity = item_quantity;
	}
	public int getItem_price() {
		return itemPrice;
	}
	public Item(@NotNull(message = "Item Name can not be empty") String item_name, int item_quantity, int item_price,Category cat) {
		super();
		this.itemName = item_name;
		this.itemQantity = item_quantity;
		this.itemPrice = item_price;
		this.category=cat;
	}
	public void setItem_price(int item_price) {
		this.itemPrice = item_price;
	}

}
