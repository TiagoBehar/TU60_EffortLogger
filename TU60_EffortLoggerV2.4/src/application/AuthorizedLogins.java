/*
 Written by:
 -Tiago Behar
 -Wyatt Mahony
 */

package application;

import java.util.ArrayList;

public class AuthorizedLogins {
	private ArrayList<Login> list = new ArrayList<Login>();
	
	
	public void addLogin(Login login){
		list.add(login);
	}
	
	public Login getLogin(int i) {
		return list.get(i);
	}
	
	public int checkLogin(Login login){//returns -1 if login is not in list, returns the index if found
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(login.getName())) {
				if(list.get(i).getPassword().equals(login.getPassword())) {
					return i;
				}
			}
		}
		return -1;
	}
	
	
	
	public void testLogins() { //adds a few test logins for testing
		
		Login user1 = new Login();
		user1.setName("Tiago Behar");
		user1.setPassword("abcd1234");
		UserData data1 = new UserData("Project1", "Step1", "Category2", "Deliverable3", "This deliverable is part of category 2 and went smoothly", "2023-10-21", 143, 56);
		user1.addData(data1);
		UserData data3 = new UserData("Project1", "Step2", "Category1", "Deliverable1", "This was part of step 2 and I spent a while fixing bugs", "2023-10-30", 215, 21);
		user1.addData(data3);
		UserData data2 = new UserData("Project3", "Step3", "Category3", "Deliverable3", "This deliverable is part of category 3 and I had a lot of trouble with it", "2022-06-05", 956, 13);
		user1.addData(data2);
		
		Login user2 = new Login();
		user2.setName("Michael Jackson");
		user2.setPassword("HeeHee");

		
		Login user3 = new Login();
		user3.setName("Wyatt Mahony");
		user3.setPassword("password");
		
		Login user4 = new Login();
		user3.setName("MakaylahG");
		user3.setPassword("12345");
		
		
		list.add(user1);
		list.add(user2);
		list.add(user3);
		list.add(user4);
	}
}
