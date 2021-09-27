import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

//ics20033

public class PamakBook {
	public static ArrayList<User> users;
	public static ArrayList<Group> allGroups;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		allGroups = new ArrayList<Group>();
		users = new ArrayList<User>();
		
		
		
		/*
		 *  All the initial users and groups are already saved in the file
		 */

		
		
//		Group g1 = new Group("WebGurus","A group for web passionates");
//		ClosedGroup g2 = new ClosedGroup("ExamSolutions","Solutions to common examquestions"); 
//		
//		allGroups.add(g1);
//		allGroups.add(g2);
//		
//		
//		User u1 = new User("Makis", "iis1998@uom.edu.gr");
//		User u2 = new User("Petros", "ics1924@uom.edu.gr");
//		User u3 = new User("Maria", "iis2012@uom.edu.gr");
//		User u4 = new User("Gianna", "iis19133@uom.edu.gr");
//		User u5 = new User("Nikos", "dai1758@uom.edu.gr");
//		User u6 = new User("Babis", "ics19104@uom.edu.gr");
//		User u7 = new User("Stella", "dai1827@uom.edu.gr");
//		@SuppressWarnings("unused")
//		User u8 = new User("Eleni", "ics2086@gmail.com");
//		
//		
//		
//		users.add(u1);
//		users.add(u2);
//		users.add(u3);
//		users.add(u4);
//		users.add(u5);
//		users.add(u6);
//		users.add(u7);
//		
//		
//		/*
//		 * Adding two users as friends
//		 */
//		u1.addFriend(u2);
//		u1.addFriend(u5);
//		u5.addFriend(u6);
//		u3.addFriend(u4);
//		u3.addFriend(u2);
//		u4.addFriend(u6);
//		u5.addFriend(u3);
//		u1.addFriend(u6);
//		u5.addFriend(u2);
//		u7.addFriend(u1);
//		
//		
//		/*
//		 * Prints common friends between two users
//		 */
//		u5.printCommonFriends(u4);
//		u1.printCommonFriends(u5);		
//		
//		/*
//		 * prints the list of friends for the user
//		 */
//		u1.printFriends();
//		u3.printFriends();
//		
//		/*
//		 * Adds user to the open group
//		 */
//		g1.addUser(u4);
//		g1.addUser(u3);
//		g1.addUser(u2);
//		
//		/*
//		 * Adds user to the closed group
//		 */
//		g2.addUser(u4);
//		g2.addUser(u5);
//		g2.addUser(u6);
//		g2.addUser(u5);
//		
//		
//		/*
//		 * Prints the groups that the user is enrolled in
//		 */
//		u4.printGroups();
//	
//		/*
//		 * Prints the members of the group
//		 */
//		g1.printMembers();
//		g2.printMembers();
//		
//		
//		/*
//		 * Prints the list with possible infected users
//		 */
//		u4.printPossibleInfectedUsers();
	
		
		
		
		// Reads the all the users and the groups from the file 
		
		try {
			FileInputStream inputStream = new FileInputStream("PamakBook-State.ser");
			ObjectInputStream input = new ObjectInputStream(inputStream);
			
				PamakBook.users = (ArrayList<User>) input.readObject();				
				PamakBook.allGroups = (ArrayList<Group>)  input.readObject();				
			
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		new MainWindow();
		
	}
	
	//returns the user if such user exists or null if he doenst
		public static User findUser(String name) {
			for(User user: PamakBook.users) {
				if(user.getName().equalsIgnoreCase(name)) {
					return user;
				}
			}
			return null;
		}

}
