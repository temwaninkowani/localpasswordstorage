import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class view extends JFrame implements ActionListener{

  JTable table;
  JPanel panel;
  JButton back;
  JScrollPane scrollPane;
  List<Object[]> passwords;
  String[] columnnames;
  DefaultTableModel defaultTableModel;
  DBHandler dbHandler;

    view(){

      this.setSize(400,450);  
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);

      dbHandler = new DBHandler();

      panel = new JPanel();
      panel.setPreferredSize(new Dimension(400,450));
      panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
      panel.setBackground(Color.WHITE);

      passwords = dbHandler.retrievepasswords();
      columnnames = new String[]{"USE","PASSWORDS"};
      defaultTableModel = new DefaultTableModel(columnnames,0);

      for(Object[] password : passwords){
        defaultTableModel.addRow(password);
      }

      table = new JTable(defaultTableModel);
      scrollPane = new JScrollPane(table);
      scrollPane.setPreferredSize(new Dimension(300,350));
      scrollPane.setAlignmentX(CENTER_ALIGNMENT);

      back = new JButton("BACK");
      back.setAlignmentX(CENTER_ALIGNMENT);
      back.addActionListener(this);


      panel.add(scrollPane);
      panel.add(Box.createVerticalStrut(30));
      panel.add(back);

      this.add(panel);
      this.pack();
      this.setVisible(true);
      
    }




    @Override
    public void actionPerformed(ActionEvent e) {
      
      if(e.getSource() == back){
        new launch();
        this.dispose();
      }
    }

}
