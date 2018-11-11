import javafx.scene.input.KeyCode;
import sun.awt.SunHints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.security.Key;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 450;
    private static final int POS_X = 300;
    private static final int POS_Y = 200;
    private static final String logFileName = "logfile.txt";
    private static  boolean shownIoErrors = false;


    private  final JPanel panelTop = new JPanel(new GridLayout(2,3));
    private  final JPanel panelBottom = new JPanel(new BorderLayout());
    private  final JTextArea log = new JTextArea();

    private  final JTextField tfIPAddress = new JTextField("IP Address");
    private  final JTextField tfPort = new JTextField("Port");
    private  final JTextField tfLogin = new JTextField("Login");
    private  final JPasswordField tfPassword = new JPasswordField("pass");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JButton btnLogin = new JButton("Login");

    private final JButton btnDisconnect = new JButton("Disconnect");
    private final JButton btnSendMsg = new JButton("Send");
    private final JTextField tfMessage = new JTextField();

    private final JList userList = new JList<>();


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();

            }
        });
    }

    private ClientGUI(){

        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       // setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setTitle("Chat Client");
        setLocationRelativeTo(null);
        setSize(WIDTH,HEIGHT);

        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUsers = new JScrollPane(userList);

        String[] users = {"u1","u1","u1","u1","u1","u1","u1","u1","u1","u1","u1","u1","u1","u1","u1"};
        userList.setListData(users);
        scrollUsers.setPreferredSize(new Dimension(100,0));

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);

        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSendMsg, BorderLayout.EAST);

        add(panelTop, BorderLayout.NORTH);
        add(scrollLog, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(scrollUsers, BorderLayout.EAST);

        cbAlwaysOnTop.addActionListener(this);
        btnDisconnect.addActionListener(this);
        btnLogin.addActionListener(this);
        btnSendMsg.addActionListener(this);
        tfMessage.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop)
            setAlwaysOnTop(!isAlwaysOnTop());
        else
        if (src == btnSendMsg || src == tfMessage)
            sendMessage();
            else
                throw new RuntimeException("Unknown source" + src);
    }
//        switch (src){
//            case (cbAlwaysOnTop):
//                setAlwaysOnTop(!isAlwaysOnTop());
//                break;
//                default:
//                    throw new RuntimeException("Unknown source" + src);
//        }

     void sendMessage(){
        String msg = tfMessage.getText();
        String username = tfLogin.getText();
        if("".equals(msg)) return;
        tfMessage.setText(null);
        tfMessage.requestFocusInWindow();
        putLog(String.format("%s: %s", username, msg));
        wrtMsgToLogFile(msg, username);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg = e.getClass().getCanonicalName() + ": " + e.getMessage();
        JOptionPane.showMessageDialog(null, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    private void wrtMsgToLogFile(String msg, String username){
        try(FileWriter out = new FileWriter(logFileName, true)){
         out.write(username + ": " + msg + "\n");
         out.flush();
        }catch (IOException e){
            if (!shownIoErrors){
                shownIoErrors = true;
                JOptionPane.showMessageDialog(this,"File write error", "Exception", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void putLog(String msg){
        if ("".equals(msg)) return;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }
}
