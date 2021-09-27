import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PossibleInfectionWindow extends JFrame {
	private JPanel panel;
	private JTextArea textArea;
	private JButton backButton;
	private User user;
	
	public PossibleInfectionWindow(User user) {
		
		this.user = user;
		
		// set the layout manager of the panel to null so i can customize
		// the position and size of the components
		panel = new JPanel();
		panel.setLayout(null);
		
		backButton = new JButton("Back To login Scren");
		
		//set the position and the size of the button
		backButton.setBounds(110, 310, 160, 30);
		
		//adds ActionListener to the the button
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new MainWindow();
				
			}
		});
		
		
		
		// set the position and the size of the texetArea		 
		textArea = new JTextArea();
		textArea.setBounds(10,10,400,300);
		// is corrects the format of the lines in the text area so the words doesnt cut of
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		// it sets the focusable to false so the user cant change enything
		textArea.setFocusable(false);
		// adds the components in the panel
		panel.add(backButton);
		panel.add(textArea);
		
		displayPossibleInfectedFirends();
		
		//Frame settings
		this.setContentPane(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Possible Infection");
		this.setSize(400, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	//displays in the text area the potential infected friends
	public void displayPossibleInfectedFirends() {
		textArea.setText("**********************************************************************"+ "\n" +
				user.getName() +" has been infected. The following users have to be tested\n" +
								 "**********************************************************************\n");
				for(User aUser: user.possibleInfectedUsers()) {
					textArea.append(aUser.getName() + "\n");
				}
	}
	
}
