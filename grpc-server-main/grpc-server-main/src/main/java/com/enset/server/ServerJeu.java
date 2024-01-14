package com.enset.server;

import com.enset.service.ServicesJeu;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ServerJeu {
    public static void main(String[] args) throws InterruptedException, IOException {
        ServerBuilder.forPort(8090).addService(new ServicesJeu()).build().start().awaitTermination();
    }
}
