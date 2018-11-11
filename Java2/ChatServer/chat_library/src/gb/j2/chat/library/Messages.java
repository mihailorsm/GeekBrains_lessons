package gb.j2.chat.library;

public class Messages {
    /*
        /bcast§time§src§<message>
        /auth_request§login§password
        /auth_accept§nickname                     Общий вид сообщения для клиента и сервера (протокол)
        /auth_error                               Нужно, чтобы клиент и сервер понимали друг друга
        /msg_format_error§<message>
        /user_list§user1§user2§user3
     */
    public static final String DELIMITER = "§";  // разделитель
    public static final String AUTH_REQUEST = "/auth_request";  // запрос авторизации
    public static final String AUTH_ACCEPT = "/auth_accept";  // подтверждение авторизации
    public static final String AUTH_ERROR = "/auth_error";  // ошибка авторизации
    public static final String BROADCAST = "/bcast"; // ???
    public static final String MESSAGE_FORMAT_ERROR = "/msg_fmt_err";  // ошибка формата сообщения, клиент, либо сервер не понимает сообщения
    public static final String USER_LIST = "/user_list";  // Список клиентов
    public static final String CLIENT_BCAST = "/cl_bcast";  // ???

    public static String getAuthRequest(String login, String password) { // формирует запрос на авторизацию из логина и пароля
        return AUTH_REQUEST + DELIMITER + login + DELIMITER + password;
    }

    public static String getAuthAccept(String nickname) {
        return AUTH_ACCEPT + DELIMITER + nickname;
    } // при успешной авторизации возвращает ник

    public static String getAuthError() {
        return AUTH_ERROR;
    } // ошибка авторизации

    public static String getBroadcast(String src, String msg) {  // ??
        return BROADCAST + DELIMITER + System.currentTimeMillis() +
                DELIMITER + src + DELIMITER + msg;
    }

    public static String getMessageFormatError(String msg) {
        return MESSAGE_FORMAT_ERROR + DELIMITER + msg;
    }  //

    public static String getUserList(String list) {
        return USER_LIST + DELIMITER + list;
    } // получение списка пользователей

    public static String getClientBcast(String msg) {
        return CLIENT_BCAST + DELIMITER + msg;
    }  // ???


}
