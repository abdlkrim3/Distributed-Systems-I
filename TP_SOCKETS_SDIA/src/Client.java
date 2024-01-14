import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            //etablire la connexion avec le serveur
            Socket s=new Socket("localhost",83);
            //création des objet InputStream et OutPutStream
            OutputStream os=s.getOutputStream();
            InputStream is=s.getInputStream();
            //envoie une donnée
            os.write(7);
            //lire une donnée
            int a=is.read();
            System.out.println(a);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
