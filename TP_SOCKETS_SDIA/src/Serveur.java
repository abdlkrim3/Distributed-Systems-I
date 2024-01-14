import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(83);
            //attendr une connexion client
            Socket s=ss.accept();
            //Création des objets InputStream et OutPutStream
            InputStream is=s.getInputStream();
            OutputStream os=s.getOutputStream();
            //lire une donnée
            int a= is.read();
            System.out.println(a);
            a+=5;
            //envoyer une donnée
            os.write(a);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
