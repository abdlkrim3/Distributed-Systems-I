public class Talkative implements Runnable {
    private int nbr;
    private String nom;

    public Talkative(int nbr, String nom) {
        this.nbr = nbr;
        this.nom = nom;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            nbr++;
            System.out.println(nom+":"+nbr);
        }
    }

}
