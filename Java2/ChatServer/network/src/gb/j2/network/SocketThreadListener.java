package gb.j2.network;

import java.net.Socket;

public interface SocketThreadListener {
    void onStartSocketThread(SocketThread thread, Socket socket);

    void onStopSocketThread(SocketThread thread);

    void onSocketThreadException(SocketThread thread, Exception e);

    void onSocketThreadIsReady(SocketThread thread, Socket socket);

    void onReceiveString(SocketThread thread, Socket socket, String msg);
}
