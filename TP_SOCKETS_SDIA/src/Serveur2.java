import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur2 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(83);
            //attendr une connexion client
            System.out.println("Attendre une connexion");
            Socket s=ss.accept();
            //Création des objets InputStream et OutPutStream
            InputStream is=s.getInputStream();
            OutputStream os=s.getOutputStream();
            ObjectInputStream ois=new ObjectInputStream(is);
            Etudiant e =(Etudiant) ois.readObject();
            System.out.println(e.getNom()+" "+e.getPrenom()+" "+e.getGroupe());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
