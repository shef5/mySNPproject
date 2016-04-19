package snp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rohan
 */
import javax.swing.*;
import java.awt.event.*;
import java.io.InputStream;

public class Blue implements ActionListener {
    
    JFrame frame;
    JLabel l1,l2;
    JTextArea a1;
    JTextField t1,t2;
    JButton b1,b2;
    JScrollPane sp;
    JComboBox jc;
    JList list;
    void ui()
    {
        frame=new JFrame("BLUETOOTH SECURE");
        frame.setBounds(100 ,20,800,700);
       
        a1=new JTextArea();
        sp=new JScrollPane(a1);
        sp.setBounds(300,100,200,300);
        frame.add(sp);
        l1=new JLabel ("name:");
        l1.setBounds(100,400,100,100);
        l2=new JLabel ("mac id:");
        l2.setBounds(100,410,100,100);
        frame.add(l1);
        frame.add(l2);
        b1=new JButton("SCAN");
        b1.setBounds(50, 100, 90 ,50);
        b1.addActionListener(this);
        b2=new JButton("CONNECT");
        b2.setBounds(50, 360, 100 ,50);
        b2.addActionListener(this);
        frame.add(b1);
        frame.add(b2);
      
       
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame.setLayout(null);
 frame.setVisible(true);     


    }
    
    
    
    
    public static void main(String args[])
    {
        Blue classobj=new Blue();
        classobj.ui();
    }
    
    
    @SuppressWarnings("CallToPrintStackTrace")
    public void actionPerformed(ActionEvent e)
    {
        try{
        if(e.getSource()==b1)
        {
            System.out.println("scanning...");
            String btscan="btdiscovery -s";
            Process p1=Runtime.getRuntime().exec("cmd /c"+btscan);
        //    p1.waitFor(); 
          

            InputStream i1=p1.getInputStream();
            InputStream i2=p1.getErrorStream();
            String s = "";
            while(true)
            {
                int var=i1.read();
                if(var==-1)break;
                s=s+(char)var;
            }
      
   

              a1.append(s+"\n");
            
             String s1 = "errrrr";
            while(true)
            {
                int var=i1.read();
                if(var==-1)break;
                s1=s1+(char)var;
            }
               System.out.println(s1);
                             System.out.println("done");

        }

    }
        catch(Exception ee){
                  ee.printStackTrace();
}
    }
    
}
