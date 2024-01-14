package com.enset.server;

import com.enset.service.BankGrpcService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server= ServerBuilder.forPort(8090)
                .addService(new BankGrpcService())
                .build();
        server.start();
        server.awaitTermination();
    }
}
