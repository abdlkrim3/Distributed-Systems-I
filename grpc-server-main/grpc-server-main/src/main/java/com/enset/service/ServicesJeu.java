package com.enset.service;

import com.enset.stubs.Jeu;
import com.enset.stubs.JeuGRPC;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Random;

public class ServicesJeu extends JeuGRPC.jeuImplBase {
    //Secret number...
    int nomberMagic =new Random().nextInt(1000);
    HashMap<String,StreamObserver<Jeu.repMsg>> joueurs =new HashMap<>();
    @Override
    public StreamObserver<Jeu.guessMsg> requestReplay(StreamObserver<Jeu.repMsg> responseObserver) {

        return new StreamObserver<Jeu.guessMsg>() {
            @Override
            public void onNext(Jeu.guessMsg guessMsg) {

                String joueur=guessMsg.getNom();
                if(!joueurs.containsKey(joueur)){
                    joueurs.put(joueur,responseObserver);
                }
                int nbr=(int)(guessMsg.getNomber());
                Jeu.repMsg repMsg;

                if(nbr== nomberMagic){

                    for(String nbJoueur: joueurs.keySet()){
                        if(nbJoueur.equals(joueur)){
                            repMsg= Jeu.repMsg.newBuilder().setContent("BRAVO vous avez gagneé").build();
                            joueurs.get(nbJoueur).onNext(repMsg);
                            joueurs.get(nbJoueur).onCompleted();
                        } else {
                            repMsg= Jeu.repMsg.newBuilder().setContent("Jeu terminé, le gagnant est : "+joueur).build();
                            joueurs.get(nbJoueur).onNext(repMsg);
                            joueurs.get(nbJoueur).onCompleted();
                        }
                    }
                }
                else if(nbr< nomberMagic){
                    repMsg= Jeu.repMsg.newBuilder().setContent("Votre nombre est plus grand "+nbr).build();
                    responseObserver.onNext(repMsg);
                }else{
                    repMsg= Jeu.repMsg.newBuilder().setContent("Votre nombre est plus petit "+nbr).build();
                    responseObserver.onNext(repMsg);
                }

            }
            @Override
            public void onError(Throwable throwable) {

            }
            @Override
            public void onCompleted() {

            }
        };
    }
}
