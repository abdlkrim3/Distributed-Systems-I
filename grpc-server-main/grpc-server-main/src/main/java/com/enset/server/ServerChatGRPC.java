package com.enset.server;

import com.enset.service.ServicesChat;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ServerChatGRPC {
    public static void main(String[] args) throws InterruptedException, IOException {
        ServerBuilder.forPort(8090).addService(new ServicesChat()).build().start().awaitTermination();
    }
}
