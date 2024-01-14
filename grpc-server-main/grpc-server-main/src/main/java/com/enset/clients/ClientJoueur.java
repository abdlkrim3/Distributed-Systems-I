package com.enset.clients;

import com.enset.stubs.Jeu;
import com.enset.stubs.JeuGRPC;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;

import static java.lang.System.exit;

public class ClientJoueur {
    public static void main(String[] args) {
        ManagedChannel localhost = ManagedChannelBuilder.forAddress("localhost", 8090).usePlaintext().build();
        JeuGRPC.jeuStub jeuStub = JeuGRPC.newStub(localhost);
        StreamObserver<Jeu.guessMsg> guessMsgStreamObserver = jeuStub.requestReplay(new StreamObserver<Jeu.repMsg>() {
            @Override
            public void onNext(Jeu.repMsg repMsg) {
                String content = repMsg.getContent();
                System.out.println(content);
            }
            @Override
            public void onError(Throwable throwable) {}

            @Override
            public void onCompleted() {
                exit(0);
            }
        });

        String Nom;
        System.out.print("Nom:");
        Scanner scanner = new Scanner(System.in);
        Nom=scanner.next();
        System.out.println("Tenter un nombre: ");

        while(true) {
            int number=new Scanner(System.in).nextInt();
            Jeu.guessMsg build = Jeu.guessMsg.newBuilder().setNom(Nom).setNumber((double) number).build();
            guessMsgStreamObserver.onNext(build);
        }
    }
}
