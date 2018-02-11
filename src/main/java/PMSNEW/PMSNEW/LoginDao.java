package PMSNEW.PMSNEW;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import PMSNEW.PMSNEW.Login;
import PMSNEW.PMSNEW.Menu;

public class LoginDao {

JdbcTemplate template; 
	
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	//public int checkLogin(Login p){  
	public int checkLogin(String pusername,String ppassword){
		int cnt=0;
	//	String sql="select count(*) uCount from ohrm_user where user_name='"+pusername+"'";
		String sql="select user_name,user_password,apprempid  from view_userapprempid where user_name='"+pusername+"'";
//		String sql="select 'ABC','ABC'";
		Login glogin=template.queryForObject(sql,new LoginMapper());
		cnt=checkPassword(ppassword,glogin.getPassword());
		if (cnt==0)
			return 0;
		else
	    return glogin.getApprempid();  
	}  
	
	public List<Menu> getMenu(String pusername){  
	    return template.query("select * from view_usermenu where user_name='"+pusername+"'",new RowMapper<Menu>(){  
	        public Menu mapRow(ResultSet rs, int row) throws SQLException {  
	        	Menu e=new Menu();  
	            e.setModulename(rs.getString(1));  
	            e.setSubmodulename(rs.getString(2));  
	            e.setLink(rs.getString(4));
	            return e;  
	        }  
	    });  
	} 
	
	
	private static int checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;
		
		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			//throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
			return 0;
		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);
		return(password_verified?1:0);
	}
	
} 

class LoginMapper implements RowMapper<Login> {
	  public Login mapRow(ResultSet rs, int arg1) throws SQLException {
	    Login login = new Login();
	    login.setUsername(rs.getString("user_name"));
	    login.setPassword(rs.getString("user_password"));
	    login.setApprempid(rs.getInt("apprempid"));
	    return login;
	  }
}

