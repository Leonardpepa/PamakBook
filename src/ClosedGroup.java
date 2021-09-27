import java.io.Serializable;

import javax.swing.JOptionPane;

public class ClosedGroup extends Group implements Serializable {
	
	/*
	 * The constractor for the closedGroup that extends the Group
	 */
	public ClosedGroup(String aName, String aDescription) {
		super(aName, aDescription);
	}
	
	
	/*
	 * to be enrolled in the group otherwise it checks if any of the user friends is enrolled in so he can be added to the group 
	 * Overrides the addUser function of the class Group and checks if the user is the first person
	 */
	@Override
	public void addUser(User aUser) {
		boolean flag = false;
		if(enrolledUsers.size() == 0) {
			flag = true;
		}
		else {
			for(User user: this.enrolledUsers) {
				if(user.isFriend(aUser) && !isMember(aUser)) {
					flag = true;
					break;
				}
			}
		}
		
		if(flag) {
			this.enrolledUsers.add(aUser);
			aUser.enrollToGroup(this);
			JOptionPane.showMessageDialog(null,aUser.getName() + " has been successfully enrolled in group " + this.name);
		}
		else if(isMember(aUser)) {
			JOptionPane.showMessageDialog(null, "You are already enrolled in this group");
		}
		else {
			JOptionPane.showMessageDialog(null,"FAILED: "+ aUser.getName() +" cannot be enrolled in group " + this.name);
		}
		
	}

	
}
