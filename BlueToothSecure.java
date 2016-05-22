
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rohan
 */
class BlueToothSecure implements ActionListener {
    
    static JFrame frame;
    static JLabel l1,l2,l3,l11,l22,l33,status,l4,or,l5,stored;
    JTextArea a1;
    
    JTextField t1,t2;
    JButton scan,connect,unpair,start,stop,save,clear;
    JScrollPane sp;
    JComboBox jc;
    JList list;
    JProgressBar prog;
     String[] names;
     String[] n={"NO DEVICES"};
     String[] devices={""};
     static String deviceName="",deviceMac;
     static File file;
     static String storedname="  --  ";
     static String Filename = "C:\\BlueTooth Secure Files\\SavedDevices.txt";
     static String Pass_File = "C:\\BlueTooth Secure Files\\Password.txt";
     static int mode=1;
     


     
    void ui()
    {
        
        frame=new JFrame("BLUETOOTH SECURE");
        frame.setBounds(350 ,70,490,550);
       
      /*  a1=new JTextArea();
        sp=new JScrollPane(a1);
        sp.setBounds(300,100,200,300);
        frame.add(sp); */
        l1=new JLabel ("NAME:");
        l1.setBounds(70,320,150,100);
        l2=new JLabel ("MAC ID:");
        l2.setBounds(70,350,150,100);
        l3=new JLabel ("STATUS:");
        l3.setBounds(70,380,150,100);
        l11=new JLabel ("");
        l11.setBounds(130,320,150,100);
        l22=new JLabel ("");
        l22.setBounds(130,350,150,100);
        l33=new JLabel ("NO DEVICES TO PAIR");
        l33.setBounds(130,380,150,100);
        status=new JLabel ("Click SCAN to start");
        status.setBounds(300,450,150,100);
        frame.add(status);
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l11);
        frame.add(l22);
        frame.add(l33);
        scan=new JButton("SCAN");
        scan.setBounds(40, 60, 90 ,40);
        scan.addActionListener(this);
        connect=new JButton("CONNECT");
        connect.setBounds(40, 120, 90 ,40);
        connect.addActionListener(this);
        unpair=new JButton("UNPAIR");
        unpair.setBounds(40, 180, 90 ,40);
        unpair.addActionListener(this);
        start=new JButton("START");
        start.setBounds(40, 240, 90 ,40);
        start.addActionListener(this);
        stop=new JButton("RESET PASSWORD");
        stop.setBounds(40, 300, 160 ,40);
        stop.addActionListener(this);
        save=new JButton("SAVE");
        save.setBounds(250, 220, 90 ,40);
        save.addActionListener(this);
        clear=new JButton("CLEAR");
        clear.setBounds(340, 220, 90 ,40);
        clear.addActionListener(this);
        frame.add(clear);
        frame.add(scan);
        frame.add(connect);
        frame.add(unpair);
        frame.add(start);
        frame.add(stop);
        frame.add(save);
        
        or=new JLabel ("OR");
        or.setBounds(325,30,160,200);
        l4=new JLabel ("Enter device name to store:");
        l4.setBounds(260,60,170,200);
        t1=new JTextField();
        t1.setBounds(260, 170, 155, 25);
        l5=new JLabel ("Stored device: ");
        l5.setBounds(250,270,170,200);
        stored=new JLabel (storedname);
        stored.setBounds(350,270,170,200);       
        
        frame.add(t1);
        frame.add(l4);
        frame.add(or);
        frame.add(l5);
        frame.add(stored);

        prog=new JProgressBar(0, 100);
        prog.setBounds(50, 450, 370, 30);
    //    frame.add(prog);
        
        
        jc=new JComboBox(n);
        jc.setBounds(260,64,150,40);
        frame.add(jc); 
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);     


    }
    
    
    
    public static void main(String args[]) throws IOException, InterruptedException
    {
        try {
                       File theDir = new File("C:\\BlueTooth Secure Files");
            if (!theDir.exists()) {
                System.out.println("creating directory... " );
                boolean directryCreated = false;
                
                try{
                    theDir.mkdir();
                    directryCreated = true;
                }
                catch(SecurityException se){
                    se.printStackTrace();
                }
                if(directryCreated) {
                    System.out.println("DIR created");
                }
            }
            File f = new File(Filename);
            if(!f.exists() && !f.isDirectory()) {
                Writefile("",Filename);
                System.out.println("new file created");
                
            }   
            String s=args[0];
             if(s.equals("2"))
            {
     
            
            if(Readfile(Filename).length()==0)
                {
                String d1 = JOptionPane.showInputDialog(frame, "Enter a device name or MAC to connect:");
                    Writefile(d1, Filename);
                }
                                mode=0;
                                LOCKPC();

            }
             
             else if(s.equals("1")){
            BlueToothSecure classobj=new BlueToothSecure();
            Process p=Runtime.getRuntime().exec("cmd /c echo %username%");
                  InputStream i1=p.getInputStream();
                  String name = "";
                while(true)
                { 
                    int var=i1.read();
                    if(var==-1)break;
                    name=name+(char)var;
                }
                
           
                File f1 = new File(Pass_File);
                   
            if(!f1.exists() && !f1.isDirectory()) {
                    JPasswordField pwd = new JPasswordField(10);
                    String pass="";
                int action = JOptionPane.showConfirmDialog(null, pwd,"Welcome "+name+"!! Choose a password: ",JOptionPane.OK_CANCEL_OPTION);
                if(action==0)
                {  for(char x: pwd.getPassword())
                    pass+=x;
                    }                   
                    encrypt(pass);
                    System.out.println("new pass file created");
            }    
                
            
            if(classobj.Readfile(Filename)!=null)
            {
                classobj.storedname=classobj.Readfile(Filename);
            }
                                    classobj.ui();

            }
        } catch (Exception ex) {
    
        

    }
    }          
    
    static  void encrypt(String text) 
         {
        try {
            char c;
            char[] cc=text.toCharArray();
            for(int i=0;i<text.length();i++)
            {
                cc[i]=(char) (text.charAt(i)-16);
            }
            System.out.println(cc);
            String s="";
             for(int i=0;i<cc.length;i++)
            {
             s+=cc[i];   
            }   
                  System.out.println(s);

             Writefile(s, Pass_File);
        } catch (Exception ex) {
        }
         }
            
 static String decrypt()
  {
 String s="";
        try {
            String text=Readfile(Pass_File);
            char c;
            char[] cc=text.toCharArray();
            for(int i=0;i<text.length();i++)
            {
                cc[i]=(char) (text.charAt(i)+16);
                
            }
            System.out.println(cc);

             for(int i=0;i<cc.length;i++)
            {
             s+=cc[i];   
            }   
                  System.out.println(s);
        } catch (IOException ex) {
        }
     return s;
  }
    
    public static void LOCKPC() throws InterruptedException, IOException
    {
         
         
            String devicestored="";
            String d=Readfile(Filename);
            //status.setText("Starting LOCK MODE...");
            System.out.print("lockpc "+d);
            if(d.length()!=0 && !d.equalsIgnoreCase("NO DEVICES"))
            {
                devicestored=d;
            }
            else
            {
                devicestored=deviceName;
            }
            
       if(devicestored.length()==0&&mode==1)
       {
           status.setText("No device selected");
       }
       else{
           
            if(isRemembered(devicestored))
                {
                   Process p=Runtime.getRuntime().exec("cmd /c btpair -u");

                }
         //   boolean logoff=false;
            int i=1;
            while(true)
            {
                
                
                 Process p=Runtime.getRuntime().exec("cmd /c btdiscovery -d%n%");
                 p.waitFor();
                  InputStream i1=p.getInputStream();
                  String result = "";
                while(true)
                { 
                    int var=i1.read();
                    if(var==-1)break;
                    result=result+(char)var;
                }
                System.out.println(i+result);
                if(!result.contains(devicestored))
                {
                //  status.setText("Device is out of range");
                  sleep(500);
                  if(mode==1){
                  status.setText("Locking your PC...");
                  }
                    System.out.println("Locking your PC...");
                  ProcessBuilder builder = new ProcessBuilder(
                     "cmd.exe", "/c", "cd \"C:\\Windows\\system32\" && rundll32.exe user32.dll, LockWorkStation");
                        builder.redirectErrorStream(true);
                        Process p2 = builder.start();
                        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                            String line;
                            while (true) {
                                line = r.readLine();
                                if (line == null) { break; }
                                System.out.println(line);
                                       }
                    break;

                }
            //   status.setText("Device found::"+i+" sec");
                sleep(500);
                 i++;
            }
            
            }
        
    }
    
    static Boolean isRemembered(String name) throws IOException, InterruptedException
    {   
                sleep(6000);
        String remember="btdiscovery  -d%r% -n\""+name+"\"";
        Process p=Runtime.getRuntime().exec("cmd /c"+remember);
            p.waitFor(); 
        System.out.println(remember);

            InputStream i1=p.getInputStream();
            String result = "";
            while(true)
            {
                int var=i1.read();
                if(var==-1)break;
                result=result+(char)var;
            }
                    System.out.println("1: "+result);

            if (result.contains("Yes"))
            {
                return true;
            }
            else
            {
             return false;
            }     
              
    }
    
    
    static String  Readfile(String des) throws FileNotFoundException, IOException
    {
        String result="";
        
      
               FileInputStream fis= new FileInputStream(des);
               while(true)
               {
                   int x=fis.read();
                   if(x==-1)
                       break;
                   else
                       result+=(char)x;
                       System.out.print((char)x);
                             
               }
               return result;
                       
    }
    
    static void Writefile(String s,String des) throws IOException
    {
            file = new File(des);
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s);
            bw.flush();
            bw.close();
            System.out.println("data writen"+s);
            
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public void actionPerformed(ActionEvent e)
    {
        try{
        if(e.getSource()==scan)
        {
            status.setText("Scanning nearby devices...");
                prog.setValue(1);

          //  System.out.println("scanning...");
            String btscan="btdiscovery -d%a%%n%";
            Process p1=Runtime.getRuntime().exec("cmd /c"+btscan);
            //p1.waitFor(); 
                          prog.setValue(10);


            InputStream i1=p1.getInputStream();
            InputStream i2=p1.getErrorStream();
            String result = "";
            while(true)
            {
                int var=i1.read();
                if(var==-1)break;
                result=result+(char)var;
            }
                                prog.setValue(20);

            result=result.replaceAll("\\(","");
   
            devices=result.split("\n");
             System.out.println("2: "+devices[0]);
            for(String x : devices )
                System.out.println("3 "+x);
           
                            prog.setValue(50);

              System.out.println(result.length());
              System.out.println(devices.length);

             if(devices.length>0 && devices[0]!="")
            {
               names=new String[devices.length];
               
               for(int i=0;i<devices.length;i++)
               { 
               names[i]=devices[i].substring(18);
             //   a1.append(devices[i].substring(18));
               }
                       prog.setValue(70);

                jc.removeAllItems();
                for(String x : names )
                  jc.addItem(x);
                
               
                 status.setText(result.length()+"devices found");
                
            }
                             prog.setValue(100);

            status.setText("Scanning completed");


             String s1 = "";
             while(true)
             {
                int var=i1.read();
                if(var==-1)break;
                s1=s1+(char)var;
             }
              System.out.println(s1);

        }
        
        if(e.getSource()==connect)
        {
            int b=0;
            String a=Readfile(Filename);
             deviceName=jc.getSelectedItem().toString();
             
               if(deviceName.equals("NO DEVICES") && a.length()==0)
                            {
                                JOptionPane.showMessageDialog(frame, "NO device to connect.");
                            }
               else if(deviceName.equals("NO DEVICES") && a.length()!=0)
            {
                deviceName=a;
                b=1;
            }
               else
               {  
                   deviceName=deviceName.substring(0, deviceName.length()-1);
                     b=1;   
               }
        
             deviceMac="";
             prog.setValue(1);

            for(String x : devices )
            {
                if(x.contains(deviceName))
                {
                    deviceMac=x.substring(0, 17);
                }
            }

            System.out.println(deviceName+"4"+deviceMac);
        
               if(b==1){
            String pair="btpair -p -n\""+ deviceName+"\"";
            System.out.println(pair);

            Process p2=Runtime.getRuntime().exec("cmd /c"+pair);
         // sleep(12000);
             l11.setText("loading..");
             l22.setText("loading..");
            p2.waitFor();
            InputStream i1=p2.getInputStream();
            String result = "";
            while(true)
            {
                int var=i1.read();
                if(var==-1)break;
                result=result+(char)var;
            }
          
             System.out.println(""+result);
             
               sleep(5000);
             if(isRemembered(deviceName))
             {
                  l11.setText(deviceName);
                  l22.setText(deviceMac);
                  l33.setText("paired");
                  status.setText("Connected to "+ deviceName);

             }
             else
             {
                  status.setText("Unable to connect");
             }
                                         prog.setValue(100);

               }
        }
        if(e.getSource()==unpair)
        {
                               
            
          Process p=Runtime.getRuntime().exec("cmd /c btpair -u");
            l11.setText("");
            l22.setText("");
            l33.setText("unpaired");
            status.setText("Unpaired all devices");
                                         prog.setValue(100);

              
        } 
        if(e.getSource()==start)
        {

            if(jc.getSelectedItem().toString().equals("NO DEVICES")&&Readfile(Filename).equals(""))
            {
                
                status.setText("NO DEVICE SELECTED");
            }
            else
            {
                LOCKPC();

            }
                                                        prog.setValue(100);

        }
         if(e.getSource()==save)
        {
                                                        prog.setValue(1);

            String pass_enterd="";
                JPasswordField pwd = new JPasswordField(10);
                int action = JOptionPane.showConfirmDialog(null, pwd,"Enter password to proceed futher:",JOptionPane.OK_CANCEL_OPTION);
                if(action==0)
                {  for(char x: pwd.getPassword())
                    pass_enterd+=x;
                    } 
                if(!pass_enterd.equals(decrypt()))
                    {
                        JOptionPane.showMessageDialog(frame, "Wrong Password!!");
                        status.setText("Wrong Password!!");
                    }   
            else{      
            String data="";
            data=jc.getSelectedItem().toString();
            
            if(data.equals("NO DEVICES") && t1.getText().toString().length()!=0)
            {
                data=t1.getText().toString();
            }
            else
            {
                data=data.substring(0,data.length()-1);
            }
            
            Writefile(data,Filename);
            status.setText(data+" stored");
            }
                                                                   prog.setValue(100);

        }
         if(e.getSource()==clear)
         {
             Writefile("",Filename);
             storedname="  --  ";
             stored.setText(storedname);
             status.setText("Cleared saved devices");
         }
         if(e.getSource()==stop)
        {
                JPasswordField pwd = new JPasswordField(10);
                String oldpass="";
                int action = JOptionPane.showConfirmDialog(null, pwd,"Enter old password :",JOptionPane.OK_CANCEL_OPTION);
                if(action<0)
                {  
                    }  
                if(action==0)
                {  for(char x: pwd.getPassword())
                    oldpass+=x;
                 }   
                if(oldpass.equals(decrypt()))
           {
                 JPasswordField pwd2 = new JPasswordField(10);
                String newpass="";
                int action2 = JOptionPane.showConfirmDialog(null, pwd2,"Enter new password :",JOptionPane.OK_CANCEL_OPTION);
                if(action2==0)
                {  for(char x: pwd2.getPassword())
                    newpass+=x;
                      
                System.out.println(newpass);
                encrypt(newpass);
                 JOptionPane.showMessageDialog(frame, "Your password has been changed successfully");
                 status.setText("Password Changed");
                }
                else if(action2<0)
                {
                    JOptionPane.showMessageDialog(frame, "Password has not been changed");

                }

           }
           else{
                                     JOptionPane.showMessageDialog(frame, "Incorrect Password");
}
        } 

    }
        catch(Exception ee){
                  System.err.println("error...something went wrong");
                  ee.printStackTrace();
}
    }
    
}


