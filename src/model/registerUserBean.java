package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.registerController;
@SessionScoped
@ManagedBean
public class registerUserBean {
	
private String firstname;
private String lastname;
private String password;
private String email;
private String username;
private String uid;


public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;	
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String createData()
{
	registerController regi = new registerController();
	registerUserBean re =new registerUserBean();
	//System.out.println(firstname);
	re.setFirstname(firstname);
	re.setEmail(email);
	re.setLastname(lastname);
	re.setPassword(password);
	re.setEmail(email);
	re.setUid(uid);
	re.setUsername(username);
	String ab= regi.storeData(re);
	System.out.println("value of ab in register user bean is "+ab);
	System.out.println(ab.equals("signIn"));
	
	if(ab.equals("signIn"))
	{
		System.out.println("we are in signIn");
		return "signIn";
	}
	else
	{
		return "userexists";
	}
}

}
