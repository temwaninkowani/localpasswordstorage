import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class generate extends JFrame implements ActionListener{

    JPanel panel;
    JTextField textfield;
    JLabel pass,use;
    JButton add,generatebtn,back;
    public static final String DB_URL = "jdbc:sqlite:userdata.db";
    public static String newpass;
    public static String passwordUse;
    public static String finalpassword;

    

    generate(){

       this.setSize(400,450);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       this.setResizable(false);
       this.setTitle("GENERATE PAGE");

       panel = new JPanel();
       panel.setPreferredSize(new Dimension(400,450));
       panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
       panel.setBackground(Color.WHITE);
       
    

       pass = new JLabel();
       pass.setFont(new Font("ARIEL",Font.ITALIC,30));
       pass.setAlignmentX(CENTER_ALIGNMENT);


       use = new JLabel("for");
       use.setAlignmentX(CENTER_ALIGNMENT);

       textfield = new JTextField();
       textfield.setMaximumSize(new Dimension(200,30));
       textfield.setAlignmentX(CENTER_ALIGNMENT);


       add = new JButton("ADD");
       add.setAlignmentX(CENTER_ALIGNMENT);
       add.addActionListener(this);


       generatebtn = new JButton("GENERATE PASSWORD");
       generatebtn.setAlignmentX(CENTER_ALIGNMENT);
       generatebtn.addActionListener(this);
       

       back = new JButton("back");
       back.addActionListener(this);
       back.setAlignmentX(CENTER_ALIGNMENT);

       

       this.add(panel);
       panel.add(pass);
       panel.add(Box.createVerticalStrut(20));
       panel.add(use);
       panel.add(Box.createVerticalStrut(20));
       panel.add(textfield);
       panel.add(Box.createVerticalStrut(20));
       panel.add(add);
       panel.add(Box.createVerticalStrut(20));
       panel.add(generatebtn);
       panel.add(Box.createVerticalStrut(20));
       panel.add(back);
       this.pack();
       this.setVisible(true);




    }


    public static String generatePassword(int passwordLength){

        String alphanumericstring = "abcdefghijklmnopqrstuvwxyz"
        + "0123456789" + "ABCDEFGHIJKLMNOPQRSTUVWXYS";

        StringBuilder stringBuilder = new StringBuilder(passwordLength);
        
        for(int i=0;i<passwordLength;i++){
            int index = (int) (alphanumericstring.length() * Math.random());
            stringBuilder.append(alphanumericstring.charAt(index));
        }

       newpass = stringBuilder.toString();
       return newpass;
    }

    public void addPassword() throws SQLException{

       connection();
       try(Connection connection = DriverManager.getConnection(DB_URL)) {
        passwordUse = textfield.getText().toString();
        String pw = finalpassword.toString();
        String insertSQL = "INSERT INTO userdata (use , password) VALUES (?,?)";

        try(PreparedStatement pStatement = connection.prepareStatement(insertSQL)){
        pStatement.setString(1,passwordUse);
        pStatement.setString(2,pw);
        pStatement.executeUpdate();
        System.out.println("password added to database");
        }
        
       } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Failed to add to database");
       }
        
       

    }

      public void connection () throws SQLException{

      try{
        Class.forName("org.sqlite.JDBC");
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            if(connection != null){
                String createTable = "CREATE TABLE IF NOT EXISTS userdata ("
               + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
               + "use TEXT NOT NULL,"
               + "password TEXT NOT NULL)";

               
               Statement statement = connection.createStatement();
               statement.execute(createTable);
               System.out.println("table created");

            }

      }

         }catch(SQLException | ClassNotFoundException e){
           System.out.println("failed to connect");

        }

    }



    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getSource() == back){
          new launch();
          this.dispose();
        }

        if(e.getSource() == generatebtn){
           generate.generatePassword(8);
           finalpassword = generate.generatePassword(8);
           System.out.print(finalpassword);
           pass.setText(finalpassword);
    

        }

        if(e.getSource() == add){
             try {
                addPassword();
                System.out.println("password added");
            } catch (SQLException e1) {
                System.out.println("failed to add to database");
            }
        }
       
    }


}
