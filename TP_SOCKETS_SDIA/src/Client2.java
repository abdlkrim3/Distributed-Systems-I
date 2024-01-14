import java.io.*;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        try {
            //etablire la connexion avec le serveur
            Socket s=new Socket("localhost",83);
            //création des objet InputStream et OutPutStream
            OutputStream os=s.getOutputStream();
            InputStream is=s.getInputStream();
            ObjectOutputStream oos=new ObjectOutputStream(os);
            Etudiant e=new Etudiant("nom1","prenom1","SDIA");
            oos.writeObject(e);
            oos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
