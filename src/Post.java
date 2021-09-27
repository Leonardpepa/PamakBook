import java.io.Serializable;
import java.util.*;

public class Post implements Comparable<Post>, Serializable{
	private Date timestamp;
	private String postText;
	private User user;
	
	Post(User aUser, String text, Date time){
		this.user = aUser;
		this.postText = text;
		this.timestamp = time;
	}
	
	
	/*
	 * Makes the Post class comparable by its time stamp
	 */
	@Override
	public int compareTo(Post o) {
		return o.timestamp.compareTo(this.getTimestamp());
	}
	
	

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
