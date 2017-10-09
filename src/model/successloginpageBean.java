package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
@SessionScoped
@ManagedBean(name="successloginpageBean")
public class successloginpageBean {
	String abc;
public String getAbc() {
		return abc =(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usersession");
	}
	public void setAbc(String abc) {
		this.abc = abc;
	}
public String logout()
{
   
	//System.out.println("result of session map" +FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usersession"));
	System.out.println("before "+abc);
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	System.out.println("before "+abc);
	System.out.println("after invalidate" +FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usersession"));
	return "index";
  	
}
}
