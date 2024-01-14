package ma.enset.blocking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        Socket s =new Socket("localhost",8888);
        InputStream is =s.getInputStream();
        OutputStream os=s.getOutputStream();
        System.out.println("Im sending a Data ");
        Scanner scan=new Scanner(System.in);
        System.out.println("Number : ");
        int nb= scan.nextInt();
        os.write(nb);
        int res=is.read();
        System.out.println("response : "+res);
        s.close();
    }
}
