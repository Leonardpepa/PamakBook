import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserPage extends JFrame implements ActionListener{
	private JPanel panel;
	private JTextField nameField;
	private JTextField emailField;
	private JButton backButton;
	private JButton postButton;
	private JTextArea postArea;
	private JTextArea displayPosts;
	private JLabel postsLabel;
	private JTextArea suggestedFriends;
	private JLabel suggestedLabel;
	
	private JTextField addFriendfield;
	private JLabel addFriendLabel;
	private JButton addFriendButton;
	
	private JLabel chooseGrouplabel;
	private JList<String> groupList;
	private DefaultListModel<String> model;
	private JScrollPane groupScroll;
	
	private JButton enrollButton;
	
	
	private User user;

	
	public UserPage(User user) {
		this.user = user;
		
		// set the layout manager of the panel to null so i can customize
		// the position and size of the components
		panel = new JPanel();
		panel.setLayout(null);
		
		//set the position and size of the field that contains the name of the user
		nameField = new JTextField(user.getName());
		nameField.setFocusable(false);
		nameField.setBounds(10,10,150,20);
		
		//set the position and size of the field that contains the email of the user
		emailField = new JTextField(user.getEmail());
		emailField.setFocusable(false);
		emailField.setBounds(170,10,150,20);
		
		//set the position and size of the back to login button
		backButton = new JButton("Back To Login Screen");
		backButton.setBounds(320, 5, 170, 30);
		
		addFriendLabel = new JLabel("Add a friend");
		addFriendLabel.setBounds(80, 20 ,80,100);
		
		addFriendfield = new JTextField("Search for a user with his name");
		addFriendfield.setBounds(80, 80, 180, 30);
		addFriendfield.addMouseListener(new myMouseListener());
		
		
		addFriendButton = new JButton("Add");
		addFriendButton.setBounds(280, 75, 60, 40);
		
		chooseGrouplabel = new JLabel("Enroll in a group");
		chooseGrouplabel.setBounds(450, 75, 180, 30 );
		
		groupList = new JList<String>();
		model = new DefaultListModel<String>();
		groupList.setModel(model);
		groupScroll = new JScrollPane(groupList);
		groupScroll.setBounds(420, 100, 150, 200);
		
		enrollButton = new JButton("Enroll");
		enrollButton.setBounds(380 + groupScroll.getWidth()/2, 300, 80, 40);
		
		
		
		//set the position and size of the post button
		postButton = new JButton("Post");
		postButton.setBounds(340,210,60,60);
		
		
		//set the position and size of the Recent Posts by Friends label
		postsLabel = new JLabel("Recent Posts by Friends");
		postsLabel.setBounds(80, 290, 150, 20);
		
		//set the position and size of Suggested Friends label
		suggestedLabel = new JLabel("Suggested Friends");
		suggestedLabel.setBounds(450, 330, 150, 100);
		
		//set the position, size and the format of the lines in the Suggested Friends text Area
		suggestedFriends = new JTextArea();
		suggestedFriends.setBounds(450, 400, 100, 140);
		suggestedFriends.setLineWrap(true);
		suggestedFriends.setWrapStyleWord(true);
		
		//set focusable to false so the user cant change anything
		suggestedFriends.setFocusable(false);
		suggestedFriends.setBackground(Color.lightGray);
		
		
		//set the position, size and the format of the lines in the Post text Area
		postArea = new JTextArea();
		postArea.setBounds(80, 160, 250, 120);
		postArea.setLineWrap(true);
		postArea.setWrapStyleWord(true);

		//set the position, size and the format of the lines in the  text Area that displays the posts
		displayPosts = new JTextArea();
		displayPosts.setBounds(80,310,250,200);
		displayPosts.setFocusable(false);
		displayPosts.setLineWrap(true);
		displayPosts.setWrapStyleWord(true);
		
		//makes a scroll pane object for the display posts text area
		//so when the content is full you scroll and see it all
		JScrollPane scroll = new JScrollPane(displayPosts);
		scroll.setBounds(80, 310, 250, 200);
		
		//add ActionListener to the buttons
		backButton.addActionListener(this);
		postButton.addActionListener(this);
		addFriendButton.addActionListener(this);
		enrollButton.addActionListener(this);
		
		
		//add all the components in the panel
		panel.add(nameField);
		panel.add(emailField);
		panel.add(backButton);
		panel.add(postArea);
		panel.add(postButton);
		panel.add(postsLabel);
		panel.add(scroll);
		panel.add(suggestedLabel);
		panel.add(suggestedFriends);
		
		panel.add(addFriendLabel);
		panel.add(addFriendfield);
		panel.add(addFriendButton);
		
		panel.add(chooseGrouplabel);
		panel.add(groupScroll);
		panel.add(enrollButton);
		
		showSuggestedFriend();
		showRecentPosts();
		fillGroupJlist();
		
		
		//Frame settings
		this.setContentPane(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("User Page");
		this.setSize(600, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	//show the suggested friends of the user in the window
	public void showSuggestedFriend() {
		suggestedFriends.setText("");
		for(User user: user.suggestedFriend()) {
			suggestedFriends.append(user.getName() + "\n");
		}
	}
	
	//show all the recent posts in the window 
	public void showRecentPosts() {
		displayPosts.setText("");
		for(Post post: user.getAllPosts()) {
				displayPosts.append(post.getUser().getName() + ", " + post.getTimestamp() + ", " + post.getPostText() + "\n");				
		}
		postArea.setText("");
	}
	
	
	
	public void fillGroupJlist() {
		for(Group g: PamakBook.allGroups) {
			model.addElement(g.getName());
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			this.dispose();
			new MainWindow();
		}
		if(e.getSource() == postButton) {
			
			//posts new post in the window 
			String text = postArea.getText();
			if(!text.equals("")) {
				Post post = new Post(user, text,new Date());
				user.addNewPost(post);
				showRecentPosts();
			}
			
		}
		
		// makes two users friends
		if(e.getSource() == addFriendButton) {
			String name = addFriendfield.getText();
			User aUser =  PamakBook.findUser(name);
			if(aUser != null) {
				user.addFriend(aUser);
				addFriendfield.setText("");
				showSuggestedFriend();
				showRecentPosts();
				graphDiameter();
			}
			else {
				JOptionPane.showMessageDialog(null,"User not found ");
			}
			
		}
		
		
		// adds a user to group
		if(e.getSource() == enrollButton) {
			String selectedGroup = groupList.getSelectedValue();
			for(Group g: PamakBook.allGroups) {
				if(g.getName().equals(selectedGroup) ) {
					g.addUser(user);
					break;
				}
			}
		}
	}
	
	
	public void graphDiameter() {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(PamakBook.users.size());
		for(int i=0; i<PamakBook.users.size(); i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		
		
		//creates the the nodes and the relationships for the graph 
		for(int i=0; i<PamakBook.users.size(); i++) {
			for(int j=0; j<PamakBook.users.size(); j++) {
				if(PamakBook.users.get(i).isFriend(PamakBook.users.get(j))) {
					Graph.addEgde(graph, i, j);
				}
			}
		}
		
		// finds the maximum shortest path
		int max = -2;
		for(int i=0;i<PamakBook.users.size(); i++) {
			for(int j=0;j<PamakBook.users.size(); j++) {
				if(Graph.calculateShortestDistance(graph, i, j, PamakBook.users.size()) > max) {
					max = Graph.calculateShortestDistance(graph, i, j, PamakBook.users.size()) ;
				}
			}
		}
		System.out.println("Diameter of Graph: " +  max);
	}
	
	
	
	class myMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(addFriendfield.getText().equals("Search for a user with his name")) {
				addFriendfield.setText("");
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
