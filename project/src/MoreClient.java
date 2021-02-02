import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class MoreClient extends JFrame implements ActionListener,Runnable{
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;

    BufferedWriter writer;
    BufferedReader reader;

    MoreClient(){
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 400, 60);
        add(p1);



        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/arrow.png"));
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5, 17, 30, 30);
        p1.add(l1);

        l1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/yiyi.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(40, 5, 50, 50);
        p1.add(l2);

        JLabel l3 = new JLabel("Friends");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.BLUE);
        l3.setBounds(100, 15, 100, 18);
        p1.add(l3);

        JLabel l6 = new JLabel("YiYi, TaTa, HaHa");
        l6.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l6.setForeground(Color.BLUE);
        l6.setBounds(110, 35, 160, 20);
        p1.add(l6);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/call.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l4 = new JLabel(i9);
        l4.setBounds(350, 20, 30, 30);
        p1.add(l4);

        l4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/image.png"));
        Image i11 = i10.getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel l5 = new JLabel(i12);
        l5.setBounds(350, 550, 40, 30);
        add(l5);

        l5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        a1 = new JTextArea();
        a1.setBounds(10, 75, 390, 450);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);


        t1 = new JTextField();
        t1.setBounds(5, 550, 250, 30);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 10));
        add(t1);

        b1 = new JButton("send");
        b1.setBounds(265, 550, 70, 30);
        b1.setBackground(new Color(7, 94, 84));
        b1.setForeground(Color.BLACK);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        b1.addActionListener(this);
        add(b1);

        getContentPane(). setBackground(Color. BLACK);
        setLayout(null);
        setSize(400, 600);
        setLocation(90 , 100);
        setUndecorated(true);
        setVisible(true);

        try{

            Socket socketClient = new Socket("localhost", 2003);
            writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        }catch(Exception e){

        }
    }
    public void actionPerformed(ActionEvent ae){
        String str = "YiYi\n"+t1.getText();
        try{
            writer.write(str);
            writer.write("\r\n");
            writer.flush();
        }catch(Exception e){

        }
        t1.setText("");
    }

    public void run(){
        try{
            String msg = "";
            while((msg = reader.readLine()) != null){
                a1.append(msg + "\n");
            }
        }catch(Exception e){}
    }
    public static void main(String[] args){
        MoreClient three = new MoreClient();
        Thread t1 = new Thread(three);
        t1.start();

    }

}

