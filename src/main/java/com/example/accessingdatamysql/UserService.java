package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.hibernate.*;


import javax.persistence.EntityManager;



@Service("userService")
public class UserService {
	
	@Autowired
	private UserRepository user;
	@Autowired
	private EntityManager manager;
	public String updatePassword(String uname,String pass) {
		String emailreg="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
		User object=null;
		Session current=manager.unwrap(Session.class);
		if(uname.matches(emailreg))
		{
			object=user.findByEmail(uname);
		}
		else
			{object=user.findByUname(uname);
			}
		if(object==null)
		{
			return "User Not Registered";
		}
		else {
			
		String oldpass=object.getPassword();
		if(oldpass.equals(pass)) 
			 return "New Password can not be same as old";
		else
			{object.setPassword(pass);
			
			current.update(object);
			
			return "Password Updated successfully";
			}
			
	     
}
}
}