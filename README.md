# PamakBook

PamakBook is a small social media platform developed for the course of Object oriented programming in 3rd semester (september-january 2020) for the University Of Macedonia

# The social media contains

* User
* groups (open or closed)
* post from user

# User
* private String name;
* private String email (email needs to be from the University Of Macedonia pattern (ics or iis or dai && (3-5) numbers) && @ && uom.edu.gr)
* private ArrayList<User> friends;
* private ArrayList<Group> groups;
* private ArrayList<Post> posts;

  the user can add another user as friend and can enroll to an open group or closed group (if he has a friend already enrolled in the closed group)
  also the user can write a post and share it with his friends
  
  # Group
  * protected String name;
  * protected String description;
  * protected ArrayList<User> enrolledUsers;
  a user can be enrolled in this group
  
  # ClosedGroup extends Group
  a user can only be enrolled in this group if he has a friend already enrolled in
  (the first user can be enrolled without this requirement)
  
  # Post 
  * private Date timestamp;
  * private String postText;
  * private User user;
  
  # DB
  the app doesnt user a database. for the simplicity of things the state is stored in a binary file 
  
  # GUI
  the gui is as simple as it gets made with java swing
  * Main window for the user to log in or register with name and email (no password)
  * User page it has the users info his chat with his friends also it shows some suggested friends
  * Possible infected friends window it shows the users of the social media that are more possible to get infected if a user is diagnosed with a virus
  
  
