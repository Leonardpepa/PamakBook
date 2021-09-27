import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class User implements Serializable{
	private String name;
	private String email;
	private ArrayList<User> friends;
	private ArrayList<Group> groups;
	private ArrayList<Post> posts;
	
	
	/*
	 * The constractor for the user 
	 * With the help of isValid function checks if an email is valid before register the user
	 */
	public User(String aName,String anEmail) {
		if(isValid(anEmail,aName)) {
			this.name = aName;
			this.email = anEmail;
			friends = new ArrayList<User>();
			groups = new ArrayList<Group>();
			posts = new ArrayList<Post>();
		}
		
		
	}
	
	
	/*
	 * Returns True is two users are friends
	 */
	public boolean isFriend(User aUser) {
		return (friends.contains(aUser) && !aUser.equals(this));
	}
	
	
	/*
	 * Adds a user to friend list 
	 */
	public void addFriend(User aUser) {
		/*
		 * If the user isn't already in the friend list then adds the user to the friend list
		 */
		if(!friends.contains(aUser) && !aUser.equals(this)) {
			friends.add(aUser);
			aUser.acceptFriendRequest(this);
			JOptionPane.showMessageDialog(null,name + " and " + aUser.getName() + " are now friends!");
		}
		else if(friends.contains(aUser)) {
			JOptionPane.showMessageDialog(null,"You already are friends with " + aUser.getName());
		}
		else if(aUser.equals(this)) {
			JOptionPane.showMessageDialog(null, "You cant add your self");
		}
		
		
	}
	
	/*
	 * Adds the user that is calling the function addFriend to the friend list of the user who is passed as argument
	 */
	private void acceptFriendRequest(User aUser) {
		friends.add(aUser);
	}
	
	
	
	/*
	 * Finds and returns the common friends between two users
	 */
	public ArrayList<User> commonFriend(User aUser){
		ArrayList<User> commonFriends = new ArrayList<User>();
		for(User user: aUser.getFriends()) {
			if(isFriend(user)) {
				commonFriends.add(user);
			}
		}
		return commonFriends;
	}
	
	
	
	/*
	 * Prints the common friends between two users
	 */
	public void printCommonFriends(User aUser) {
		System.out.println("************************");
		System.out.println("Common friends of "+ this.name +" and " + aUser.getName());
		System.out.println("************************");
		for(User user: commonFriend(aUser)) {
			System.out.println(user.getName());
		}
		System.out.println("--------------------------------------\n");
	
	}
	
	
	
	/*
	 * Prints the friends of a user
	 */
	public void printFriends() {
		System.out.println("************************");
		System.out.println("Friends of " + this.name);
		System.out.println("************************");
		for(User user: friends) {
			System.out.println(user.getName());
		}
		System.out.println("--------------------------------------\n");
	}
	
	
	
	/*
	 * Prints the groups that the user is enrolled in
	 */
	public void printGroups() {
		System.out.println("************************");
		System.out.println("Groups that "+ this.name +" has been enrolled in");
		System.out.println("************************");
		for(Group group: groups) {
			System.out.println(group.getName());
		}
		System.out.println("--------------------------------------\n");
		
	}
	
	
	
	/*
	 * Finds and returns the users that are more possible to be infected if a user is infected
	 */
	public ArrayList<User> possibleInfectedUsers() {
		
		ArrayList<User> possibleEffectedFriends = new ArrayList<User>();

		for(User user: friends) {
			possibleEffectedFriends.add(user);
		}
		
		for(User user: friends) {
			for(User friendUser: user.getFriends()) {
				if(!friendUser.equals(this) && !possibleEffectedFriends.contains(friendUser)) {
					possibleEffectedFriends.add(friendUser);
				}
			}
		}
		
		return possibleEffectedFriends;
	}
	
	
	
	/*
	 * Prints the user that are more possible to be infected
	 */
	public void printPossibleInfectedUsers() {
		System.out.println("************************");
		System.out.println(this.name + " has been infected. The following users have to be tested");
		System.out.println("************************");
		for(User user: possibleInfectedUsers()) {
			System.out.println(user.getName());
		}
		System.out.println("--------------------------------------\n");		
	}
	
	
	
	
	/*
	 * Checks if the email format is valid
	 * is brakes the email in to 3 substrings the Department, the id of the user and the host
	 * then its checks if all three are valid input if so it return true else it returns false
	 */
	public boolean isValid(String email,String aName) {
		// check if the email pattern is correct using regex
		Pattern pattern  = Pattern.compile("(?:ics|iis|dai|)\\d{3,5}@uom\\.edu\\.gr");
		Matcher matcher = pattern.matcher(email);
		boolean flag = matcher.matches();
		if(!flag) {
			JOptionPane.showMessageDialog(null,"User " + aName + " has not been created. Email format is not acceptable.\n");
		}
		return flag;
	}
	
	
	public void addNewPost(Post aPost) {
			posts.add(aPost);
	}
	
	// return a list with users posts
	public ArrayList<Post> getUsersPosts(){
		return posts;
	}
	
	//returns a list with all the posts of the user and his friends
	public ArrayList<Post> getAllPosts(){
		ArrayList<Post> allPosts = new ArrayList<Post>();
		allPosts.addAll(posts);
		for(User friend: friends) {
			allPosts.addAll(friend.getUsersPosts());
		}
		
		Collections.sort(allPosts);
		 return allPosts;
	}
	
	//returns a list with suggested friends of a user
	public ArrayList<User> suggestedFriend() {
		ArrayList<User> suggestedFriends = new ArrayList<User>();
		for(User friend: friends) {
			for(User aUser: friend.getFriends()) {
				if(!this.isFriend(aUser) && !this.equals(aUser) && !suggestedFriends.contains(aUser)) {
					suggestedFriends.add(aUser);
				}
			}
		}
		return suggestedFriends;
	}
	
	
	/*
	 * Adds a group to the group list of the user
	 */
	public void enrollToGroup(Group group) {
		groups.add(group);
	}
	
	
	
	
	public String getName() {
		return name;
	}

	
	public String getEmail() {
		return email;
	}


	public ArrayList<User> getFriends() {
		return friends;
	}


	public ArrayList<Group> getGroups() {
		return groups;
	}
}
