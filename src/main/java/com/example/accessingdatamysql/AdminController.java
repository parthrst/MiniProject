package com.example.accessingdatamysql;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/admin")
public class AdminController {
	@Autowired 
private UserRepository userRepository;
	@Autowired
	private CategoryRepository category;
	@Autowired
	private ItemRepository item;

	@Autowired
	private UserService service;

	@PostMapping(path="/signup") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestBody User register) {
		String uname=register.getUname();
		String phone=register.getPhone();
		User una=userRepository.findByUname(uname);
		User phon=userRepository.findByPhone(phone);
		
		if(una != null) {
			  return "Username already exists";
			  }
		else if(phon!=null)
			{return "phone number already taken";}
		
		 else {	 	
		
		userRepository.save(register);
		return "Saved";
			 }
	}
@GetMapping(path="/viewall")
public @ResponseBody Iterable<User> getAllUsers() {
// This returns a JSON or XML with the users
return userRepository.findByAdminTrue();
}
@PostMapping(path="/login")
public @ResponseBody String login(@RequestBody Login login,HttpServletRequest request) {
	String emailreg="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
	int flag=0;
	User user;
	String email=login.getUname();
	String password=login.getPassword();
	if(email.matches(emailreg)) {
		user=userRepository.findByEmail(email);}
	else
	{user=userRepository.findByUname(email);}
	if(user!=null) {
	if(user.getPassword().equals(password))
	{        user.setActive(true);
			if(flag==1)
				request.getSession().setAttribute("uname", email);
			else
				request.getSession().setAttribute("email",email);
			return "LoginSuccesfull";
	}
	else
		return "password incorrect";
	}
	else
		
		
		return "user not registered";
}
@PostMapping(path="/addcategory")
public @ResponseBody String addCategory(@RequestParam String name)
  {
	 Category cat=category.findByName(name);
	if(cat!=null)
		return "Category Exist";
	else
	{  Category obj=new Category();
	   obj.setCategory_name(name);
	   category.save(obj);
	return "Category Saved";
	}
	
}
@GetMapping(path="/showcategory")
public Iterable<Category> showCtaegory() {
	return category.findAll();
	
}

@PostMapping(path="/logout")
public @ResponseBody String logout(HttpSession session,HttpServletRequest request) {
String email=(String)session.getAttribute("email");
String uname=(String)session.getAttribute("uname");
session.invalidate();
User user;
if(email==null||email=="")
	{user=userRepository.findByUname(uname);
	user.setActive(false);
	return uname+"has been succefully looged out";
	
	}
else
	{user=userRepository.findByEmail(email);
	user.setActive(false);
	return email+"has been succefully logged out";
	}
	}
@PostMapping(path="/forgotpassword")
public @ResponseBody String passwordreset(@RequestParam String uname,@RequestParam String pass)
{
	return service.updatePassword(uname, pass);
}
@PostMapping(path="/additem")
public @ResponseBody String addItem(@RequestParam String categoryName ,@RequestParam String itemName,@RequestParam int itemPrice,@RequestParam int itemQuantity)
{
	Category cat=category.findByName(categoryName);
	if(cat==null)
	 return "Category not registered";
	else
	{    Item temp=new Item(itemName,itemQuantity,itemPrice,cat);
		item.save(temp);
		cat.add(temp);
		return "Item Saved";
	}
	
}
@GetMapping(path="showList")
public Iterable<Item> getList(){
	return item.findAll();
}
}
