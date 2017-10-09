package model;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import controller.registerController;

@ManagedBean(name="signInBean")
public class signInBean {
private String username;
private String password;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public String login()
{
	System.out.println("we are in login page");
	signInBean si = new signInBean();
	registerController register = new registerController();
	si.setUsername(username);
	si.setPassword(password);
	String pagereturn= register.loginData(si);
	System.out.println(pagereturn);
	String usersession;
	
	if(pagereturn.equals("successloginpage"))
	{
		
		return "successloginpage";
	}
	else
	   return "failure";
}
}
