import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Group implements Serializable{

	protected String name;
	protected String description;
	protected ArrayList<User> enrolledUsers;
	
	/*
	 * The constractor of the group
	 */
	public Group(String aName,String aDescription) {
		this.name = aName;
		this.description = aDescription;
		enrolledUsers = new ArrayList<User>();
		
	}
	
	/*
	 * Checks if a user us a member of the group if so returns true else returns false
	 */
	public boolean isMember(User aUser) {
		return enrolledUsers.contains(aUser);
	}
	
	
	
	/*
	 * Adds a user to the group if he isnt already enrolled in and also add the group to users group list
	 */
	public void addUser(User aUser) {
		if(!isMember(aUser)) {
			enrolledUsers.add(aUser);
			aUser.enrollToGroup(this);
			JOptionPane.showMessageDialog(null,aUser.getName() + " has been successfully enrolled in group " + this.name);
		}
		else {
			JOptionPane.showMessageDialog(null, "You are already enrolled in this group");
		}
	}
	
	
	
	/*
	 * Prints the members of the group
	 */
	public void printMembers() {
		System.out.println("************************");
		System.out.println("Members of group " + this.name);
		System.out.println("************************");
		for(User user: enrolledUsers) {
			System.out.println(user.getName());
		}
		System.out.println("--------------------------------------\n");
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<User> getEnrolledUsers() {
		return enrolledUsers;
	}

}
