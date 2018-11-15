package gb.j2.chat.client;

import gb.j2.chat.library.Messages;
import gb.j2.network.SocketThread;
import gb.j2.network.SocketThreadListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler, SocketThreadListener {
    private static final int WIDTH = 500;              // ширина окна
    private static final int HEIGHT = 300;             //  высота окна
    private static final String TITLE = "Chat Client"; // заголовок окна

    private final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss: "); //  объект, который предоставляет время в заданном формате

    private final JTextArea log = new JTextArea();  // то, где отображаются сообщения
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));  // панелька с авторизацией
    //private final JTextField tfIPAddress = new JTextField("95.84.209.91");
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");  // адрес
    private final JTextField tfPort = new JTextField("8189");        // порт
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Alwayson top");  // галочка, которая делает окно поверх всех остальных
    private final JTextField tfLogin = new JTextField("ivan");  //  логин
    private final JPasswordField tfPassword = new JPasswordField("123");  // пасс
    private final JButton btnLogin = new JButton("Login");  // кнопка для авторизации

    private final JPanel panelBottom = new JPanel(new BorderLayout());  //  нижняя панелька с отправкой сообщений
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");  // отключение от сервера
    private final JTextField tfMessage = new JTextField();  //  поле для ввода сообщений
    private final JButton btnSend = new JButton("Send"); //  кнопка для отправки сообщения

    private final JList<String> userList = new JList<>();  //  список пользователей
    private boolean shownIoErrors = false; //  ??? по-моему это нужно для уведомления гуи, что вылетело исключение
    private SocketThread socketThread; //  поток для сокета ??
    private final String[] EMPTY = new String[0]; // ???


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            } // запуск нового потока, в котором создается новый объект, то бишь окно
        });
    }

    private ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);// где то говорилось, но уже забыл
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // при нажатии на крестик окна, стопается приложение
        setLocationRelativeTo(null);  // ???
        setSize(WIDTH, HEIGHT); //  настройка размеров
        setTitle("Chat Client"); // установка заголовка

        log.setEditable(false); // нельзя поставить курсор
        log.setLineWrap(true); // по-моему это окоемочка
        JScrollPane scrollLog = new JScrollPane(log);// добавляем скролл в окно с сообщеньками
        JScrollPane scrollUsers = new JScrollPane(userList); //  добавляем скролл в список пользователя
        String[] users = {"user1_with_an_exceptionally_long_nickname", "user2", "user3", "user4", "user5", "user6", "user7", "user8", "user9", "user10"}; //  создаем список пользователей
        userList.setListData(users); //  добавляем список в окно из массива
        scrollUsers.setPreferredSize(new Dimension(100, 0)); // ставим фиксированные размеры списка
        cbAlwaysOnTop.addActionListener(this);
        btnLogin.addActionListener(this);
        btnSend.addActionListener(this);
        btnDisconnect.addActionListener(this);
        tfMessage.addActionListener(this);  //  все выше перечисленное добавляем метод, которые слушает событие по данным компонентам, например нажатие кнопки
        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);  //  все выше добавление компанентов на верхнюю панель
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);  //  все выше добавление компанентов на нижнюю панель
        panelBottom.setVisible(false);
        add(panelTop, BorderLayout.NORTH);
        add(scrollLog, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(scrollUsers, BorderLayout.EAST); // добавление панельей
        setVisible(true); // сделать окно видимым
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) { //т. к. в гуи мы не видим ексепшенов, то мы создаем
        e.printStackTrace();                               //специальный метод, который будет нам их показывать
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        if (ste.length == 0)
            msg = "Empty Stacktrace";
        else {
            msg = e.getClass().getCanonicalName() + ": " + e.getMessage() +
                    "\n\t at " + ste[0];
        }
        JOptionPane.showMessageDialog(null, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) { // обработчик событий
        Object src = e.getSource(); // узнаем что за компонент бросил событие
        if (src == cbAlwaysOnTop) { // если это чекбокс
            setAlwaysOnTop(cbAlwaysOnTop.isSelected()); // то ставим окно поверх всех остальных
        } else if (src == btnLogin || src == tfIPAddress || src == tfLogin || src == tfPassword || src == tfPort) { // кнопка логин или одно из полей
            connect(); // приконнектиться к серверу
        } else if (src == btnSend || src == tfMessage) { // если кнопка отправки сообщения
            sendMessage();  // отправлять сообщения
        } else if (src == btnDisconnect) { // если кнопка дисконнект
            socketThread.close(); // то закрыть сокет
        } else {
            throw new RuntimeException("Unknown source: " + src); // любое другое действие расценивается как исключеие
        }
    }

    private void connect() {
        Socket socket = null;
        try {
            socket = new Socket(tfIPAddress.getText(), Integer.parseInt(tfPort.getText())); // создаем килентский сокет
        } catch (IOException e) {
            log.append("Exception: " + e.getMessage());  // кидаем в лог исключение
        }
        socketThread = new SocketThread(this, "Client thread", socket);
    }

    void sendMessage() {
        String msg = tfMessage.getText();  // отправка сообщения из поля
        if ("".equals(msg)) return;
        tfMessage.setText(null);
        tfMessage.requestFocusInWindow();
        socketThread.sendMessage(Messages.getClientBcast(msg));
    }

    private void wrtMsgToLogFile(String msg, String username) { // по идее система логгирования
        try (FileWriter out = new FileWriter("log.txt", true)) {
            out.write(username + ": " + msg + "\n");
            out.flush();
        } catch (IOException e) {
            if (!shownIoErrors) {
                shownIoErrors = true;
                JOptionPane.showMessageDialog(this, "File write error", "Exception", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void putLog(String msg) {
        if ("".equals(msg)) return;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }

    /**
     * Socket Thread Events
     * */

    @Override
    public void onStartSocketThread(SocketThread thread, Socket socket) {
        putLog("Connection Established");
    }

    @Override
    public void onStopSocketThread(SocketThread thread) {
        putLog("Connection lost");
        panelBottom.setVisible(false);
        panelTop.setVisible(true);
        setTitle(TITLE);
        userList.setListData(EMPTY);
    }

    @Override
    public void onReceiveString(SocketThread thread, Socket socket, String msg) {
        handleMessage(msg);
    }

    @Override
    public void onSocketThreadIsReady(SocketThread thread, Socket socket) {
        panelBottom.setVisible(true);
        panelTop.setVisible(false);
        String login = tfLogin.getText();
        String password = new String(tfPassword.getPassword());
        thread.sendMessage(Messages.getAuthRequest(login, password));
    }

    @Override
    public void onSocketThreadException(SocketThread thread, Exception e) {
        putLog("socket thread exception");
    }

    private void handleMessage(String value) {
        String[] arr = value.split(Messages.DELIMITER);
        String msgType = arr[0];
        switch (msgType) {
            case Messages.AUTH_ACCEPT:
                setTitle(TITLE + " logged in as: " + arr[1]);
                break;
            case Messages.AUTH_ERROR:
                putLog(value);
                break;
            case Messages.MESSAGE_FORMAT_ERROR:
                putLog(value);
                socketThread.close();
                break;
            case Messages.BROADCAST:
                putLog(dateFormat.format(Long.parseLong(arr[1])) +
                        arr[2] + ": " + arr[3]);
                break;
            case Messages.USER_LIST:
                String users = value.substring(Messages.USER_LIST.length() + Messages.DELIMITER.length());
                String[] userArray = users.split(Messages.DELIMITER);
                Arrays.sort(userArray);
                userList.setListData(userArray);
                break;
            default:
                throw new RuntimeException("Unknown message type: " + value);
        }
        //        wrtMsgToLogFile(msg, username);

    }
}
