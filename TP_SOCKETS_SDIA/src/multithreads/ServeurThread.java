package multithreads;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServeurThread extends Thread{
    private Socket s;
    private List<Socket> joeurs;
    private int nbMagique;
    public ServeurThread(Socket s,List<Socket> joeurs,int nbMagique) {

        this.s = s;
        this.joeurs=joeurs;
        this.nbMagique=nbMagique;
    }

    @Override
    public void run() {
        try {
            InputStream is=s.getInputStream();
            OutputStream os=s.getOutputStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(isr);
            PrintWriter pr=new PrintWriter(os,true);
            String msg="";
            Scanner scan=new Scanner(System.in);
            int nbJoeur=0;
            pr.println("Veulliez sisir votre nom : ");
            String jName=br.readLine();
            String msgJoeur=null;
            do{
                try{
                    pr.println("Veulliez tenter un nombre : ");
                    if((msgJoeur= br.readLine())!=null){
                        nbJoeur=Integer.parseInt(msgJoeur);
                        if(nbJoeur==nbMagique){
                            pr.println("Bravo vous avez trouve le nombre magique");
                            for(Socket sjoeur:joeurs){
                                if(s!=sjoeur){
                                    OutputStream osj=sjoeur.getOutputStream();
                                    PrintWriter prj=new PrintWriter(osj,true);
                                    prj.println("Le joeur "+jName +" a trouve le nombre magique : "+nbMagique);
                                }
                            }
                        } else if (nbJoeur>nbMagique) {
                            pr.println("Le nombre "+nbJoeur+" est superieur au nombre magique!!");

                        }else {
                            pr.println("Le nombre "+nbJoeur+" est inferieur au nombre magique!!");
                        }
                    }
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                }
            }while (nbJoeur!=nbMagique);
            for(Socket sjoeur:joeurs){
                sjoeur.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
