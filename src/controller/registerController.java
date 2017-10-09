package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.registerUserBean;
import model.signInBean;

@ManagedBean(name = "registerController")
@SessionScoped
public class registerController {
	
public String loginData(signInBean sign)
{
	
	 Connection con = null;
		try {
			//System.out.println("First name is "+ regUser.getFirstname());
			// Setup the DataSource object
			int port  = Integer.parseInt(System.getenv("ICSI518_PORT"));
			String servername = System.getenv("ICSI518_SERVER");
			String databasename = System.getenv("ICSI518_DB");
		    String user = System.getenv("ICSI518_USER");
		    String password = System.getenv("ICSI518_PASSWORD");
		    System.out.println("port number is "+System.getenv("ICSI518_PORT"));
			System.out.println("server name is "+System.getenv("ICSI518_SERVER"));
			System.out.println("database name is"+System.getenv("ICSI518_DB"));
			System.out.println("user is "+System.getenv("ICSI518_USER"));
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
			
			ds.setServerName(servername);
			ds.setPortNumber(port);
			ds.setDatabaseName(databasename);
			ds.setUser(user);
			ds.setPassword(password);
                       
			// Get a connection object
			con = ds.getConnection();
			String sql1 = "SELECT firstname, password from registeruser where username=? and password=?";
			PreparedStatement st1 = con.prepareStatement(sql1);
			System.out.println("Username is "+ sign.getUsername());
			System.out.println("password is "+ sign.getPassword());
			st1.setString(1, sign.getUsername());
			st1.setString(2, sign.getPassword());
			ResultSet rs2 = st1.executeQuery();
			System.out.println("result set  in sign in page"+rs2);
			//System.out.println("result set first"+rs2.first());
			//System.out.println("result set next"+ rs2.next());
			//System.out.println("result set next"+ rs2.next());

			//System.out.println("first name is db"+rs2.getString("firstname"));
			if(rs2.next())
			{  
			String	usersession = "hello, "+rs2.getString("firstname");	
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usersession",usersession);
				 //rs1.getString("password").equals(sign.getPassword());
				return "successloginpage";
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	return "invalidpage";
	
}
	
public String storeData(registerUserBean regUser)
 {
	 Connection con = null;
		try {
			//System.out.println("First name is "+ regUser.getFirstname());
			// Setup the DataSource object
			int port  = Integer.parseInt(System.getenv("ICSI518_PORT"));
			String servername = System.getenv("ICSI518_SERVER");
			String databasename = System.getenv("ICSI518_DB");
		    String user = System.getenv("ICSI518_USER");
		    String password = System.getenv("ICSI518_PASSWORD");
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
			
			ds.setServerName(servername);
			ds.setPortNumber(port);
			ds.setDatabaseName(databasename);
			ds.setUser(user);
			ds.setPassword(password);

			// Get a connection object
			con = ds.getConnection();
			String sql1 = "select firstname from registeruser where username = ?";
			
			PreparedStatement st1 = con.prepareStatement(sql1);
			//System.out.println("UID is "+ regUser.getUid());
			st1.setString(1, regUser.getUid());
			
			ResultSet rs = st1.executeQuery();
			//System.out.println("after rs");
			//System.out.println("First name is "+rs.getString(1));
			//System.out.println("REsult set is"+rs);
			
			if(rs.next())
			{ 
		      System.out.println("User already exists in database");
		      return "userexits";
		      
			}
			else 
			{	
			// Get a prepared SQL statement
			String sql = "insert into registeruser(firstname, lastname, username, password, email) values (?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			
			
			st.setString(1, regUser.getFirstname());
			st.setString(2, regUser.getLastname());
			st.setString(3, regUser.getUid());
			st.setString(4, regUser.getPassword());
			st.setString(5, regUser.getEmail());
                 
			// Execute the statement
			 st.executeUpdate();
			 return "signIn";
			}
			// Iterate through results
	
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return "signIn";
			
		
		

 }
}
