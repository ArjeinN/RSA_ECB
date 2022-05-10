/**
* The RSAwGUI program implements an application that
* simply runs RSA on Electronic Code Book (ECB) mode. It is supported with GUI, 
* used to encrypt/decrypt plaintext, ciphertext.
*
* @author  Mert Arcan
* @version 1.0
* @since   2022-05-09
*/
import javax.swing.*;
import java.awt.event.*;  
import java.awt.*; 
import java.util.Vector;
public class RSAwGUI extends JFrame {


    public static void main(String[] args) {
     

      KeyGenerator gen = new KeyGenerator();
      RSA rsa = new RSA();
      JFrame f=new JFrame("RSA");  
      
      f.setSize(800,500);  
    
      JPanel p = new JPanel();
      p.setBounds(50,100,250,300);   
      p.setBackground(Color.blue);
        JButton encrypt = new JButton("Encrypt");
      p.add(encrypt);
        JTextArea Earea = new JTextArea(14,20);
        Earea.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(Earea);
      p.add(scroll);

      JPanel p2 = new JPanel();
      p2.setBounds(630,5,150,380);
        Vector<KeyPair> keyarr = new Vector<KeyPair>();
        JList<KeyPair> keyList = new JList<KeyPair>(keyarr);
        keyList.setFont(new Font("Arial",Font.BOLD,14));
        JScrollPane scrollList = new JScrollPane(keyList);
        JButton generateKeys=new JButton("Generate Key Pairs");  
        //generateKeys.setBounds(0,0,200,30); 
      p2.add(generateKeys);
      p2.add(scrollList);
      
      JPanel p3 = new JPanel();
      p3.setBounds(320,100,250,300);   
      p3.setBackground(Color.red);
        JButton decrypt = new JButton("Decrypt");
      p3.add(decrypt);
        JTextArea Darea = new JTextArea(14,20);
        Darea.setLineWrap(true);
        JScrollPane Dscroll = new JScrollPane(Darea);
      p3.add(Dscroll);

      
      // ADD to Main Frame
      f.add(p2);
      f.add(p);
      f.add(p3);
      
      // Main Frame Last Settings
      f.setLayout(null);  
      f.setVisible(true);  
      f.setResizable(false);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    

      // ActionListeners
      generateKeys.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
        KeyPair kp = gen.GeneratePair();
        keyarr.add(kp);
        keyList.updateUI();
        scrollList.updateUI();
        p2.updateUI();
        }  
            });
      encrypt.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){  
          Darea.setText(null);
          String plaintext = Earea.getText();
          KeyPair keys = keyList.getSelectedValue();
          String CipherText= rsa.Encrypt(plaintext,keys);
          Darea.setText(CipherText);
          Darea.updateUI();
          //Earea.setText(null);
          
      }  
      
    });  
    decrypt.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){  
        Earea.setText(null);
        String ciphertext = Darea.getText();
        KeyPair keys = keyList.getSelectedValue();
        String PlainText = rsa.Decrypt(ciphertext,keys);
        Earea.setText(PlainText);
        Earea.updateUI();
        //Darea.setText(null);
        
        
    }  
    
  });  

    
 
    }

    
 
    
}
