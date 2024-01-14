package multithreads;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try{
            Socket s=new Socket("localhost",8090);
            InputStream is=s.getInputStream();
            OutputStream os=s.getOutputStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(isr);
            PrintWriter pr=new PrintWriter(os,true);
            Scanner sc =new Scanner(System.in);
            String msg="";
            while(msg!=null&&!msg.equals("q")){
                System.out.println("Veulliez sisair un message (Entrer q pouquitterr) : ");
                msg=sc.nextLine();
                pr.println(msg);
                msg=br.readLine();
                System.out.println("Serveur : "+msg);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
