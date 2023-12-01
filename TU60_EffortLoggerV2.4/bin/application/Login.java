/*
 Written by:
 -Tiago Behar
 -...
 */
package application;

import java.util.ArrayList;

public class Login {
	private String name;
	private String password;
	private ArrayList<UserData> data = new ArrayList<UserData>(); //placeholder data for each user
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getPassword()
	{
		return this.password;
	}

	
	public int getPasswordLength()
	{
		return this.password.length();
	}
	
	public void addData(UserData d) {
		data.add(d);
	}
	
	public ArrayList<UserData> getData(){
		return this.data;
	}
	public void deleteData(int index) {
		data.remove(index);
	}

}
