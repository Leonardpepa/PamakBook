import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame implements ActionListener{
	private JButton newUserButton;
	private JButton savePamakBookButton;
	private JTextField nameField;
	private JTextField emailField;
	private JButton loginButton;
	private JButton infectionButton;
	private JPanel panel;
	
	
	public MainWindow(){
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		newUserButton = new JButton("New User");
		savePamakBookButton = new JButton("Save PamakBook");
		
		loginButton = new JButton("Enter User Page");
		infectionButton = new JButton("Show Potencial Inffections");
		
		nameField = new JTextField(10);
		nameField.setText("User Name");
		
		emailField = new JTextField(11);
		emailField.setText("User Email");
		
		newUserButton.addActionListener(this);
		savePamakBookButton.addActionListener(this);
		loginButton.addActionListener(this);
		infectionButton.addActionListener(this);
		
		
		//add the MouseListener to the field that contains the name
		//so when you click the first time the text Please enter your name disappear and you can type a name 
		nameField.addMouseListener(new myMouseListener());
		emailField.addMouseListener(new myMouseListener());
		
		//add the components in the panel
		panel.add(newUserButton);
		panel.add(nameField);
		panel.add(emailField);
		panel.add(loginButton);
		panel.add(infectionButton);
		panel.add(savePamakBookButton);
		
		
		
		//Frame settings
		this.setContentPane(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("User Entry");
		this.setSize(350, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String name = nameField.getText();
		String email = emailField.getText();
		User userFound = PamakBook.findUser(name);
		
		
		if(userFound != null) {
			
			if(e.getSource() == loginButton) {
				//close this window and open the user page window
				this.dispose();
				new UserPage(userFound);
			}
			
			if(e.getSource() == infectionButton) {
				//close this window and open the possible Infection window 
				this.dispose();
				new PossibleInfectionWindow(userFound);
			}
			if(e.getSource() == newUserButton) {
				JOptionPane.showMessageDialog(null,"User already exists");
			}
			
		}
		
		else {

			if(e.getSource() == newUserButton) {
					if(!name.equals("") && !email.equals("")) {
						User newUser = new User(name, email);					
						if(newUser.getName() != null) {
							PamakBook.users.add(newUser);
							JOptionPane.showMessageDialog(null, "User " + newUser.getName() + " has successfully been created");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Please fill the forms");
					}
				
			}
			
			if(e.getSource() == loginButton || e.getSource() == infectionButton) {
				JOptionPane.showMessageDialog(null, "User " + name + " Not Found");
				nameField.setText("");
			}
			
			
		}
		// saves the state of the PamakBook
		if(e.getSource() == savePamakBookButton) {
			
			try {
				FileOutputStream stream = new FileOutputStream("PamakBook-State.ser");
				ObjectOutputStream out = new ObjectOutputStream(stream);
				out.writeObject(PamakBook.users);
				out.writeObject(PamakBook.allGroups);
				JOptionPane.showMessageDialog(null, "PamakBook Saved");
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		
		
	}
	
	
	class myMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == nameField) {
				if(nameField.getText().equals("User Name")) {
					nameField.setText("");
				}
			}
			else {
				if(emailField.getText().equals("User Email")) {
					emailField.setText("");
				}
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

}
