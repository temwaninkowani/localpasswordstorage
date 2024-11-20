import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class launch extends JFrame implements ActionListener{

    JLabel label;
    JPanel panel;
    JButton generate,view;

    launch(){

     this.setSize(400,450);
     this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     this.setResizable(false);
     this.setTitle("launch page");
     

     panel = new JPanel();
     panel.setPreferredSize(new Dimension(400,450));
     panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
     panel.setBackground(Color.WHITE);
     

     label = new JLabel("choose a function");
     label.setFont(new Font("Arial",Font.BOLD,30));
     label.setForeground(Color.BLACK);
     label.setAlignmentX(CENTER_ALIGNMENT);
     
     view = new JButton("VIEW PASSWORDS");
     view.setAlignmentX(CENTER_ALIGNMENT);
     view.addActionListener(this);


     generate = new JButton("GENERATE");
     generate.setAlignmentX(CENTER_ALIGNMENT);
     generate.addActionListener(this);

     this.add(panel);
     panel.add(label);
     panel.add(Box.createVerticalStrut(20));
     panel.add(generate);
     panel.add(Box.createVerticalStrut(20));
     panel.add(view);
     
     
     this.pack();
     this.setVisible(true);







    }
    
    
    

     public static void main(String [] args){
         new launch();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view){
           try {
            new view();
            this.dispose();
           } catch (Exception exception) {
            //exception.printStackTrace();
           }

        }

        if(e.getSource() == generate){
            try {
             new generate();
             this.dispose();

            } catch (Exception exception) {
             //exception.printStackTrace();
            }
 
         }
    }
}
