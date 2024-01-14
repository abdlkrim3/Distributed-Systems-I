package ma.enset.blocking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
            ServerSocket ss=new ServerSocket(1234);
            System.out.println("Im waiting a new connection");
            Socket socket=ss.accept();
            InputStream is =socket.getInputStream();
            OutputStream os=socket.getOutputStream();
            System.out.println("Im waiting a Data ");
            int nb =is.read();
            System.out.println("Im sending a response");
            int res =nb*23;
            os.write(res);
            socket.close();
    }
}
