package com.enset.service;

import com.enset.stubs.Chat;
import com.enset.stubs.ChatGRPC;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;

public class ServicesChat extends ChatGRPC.chatImplBase {
    HashMap<String,StreamObserver<Chat.request>> listeClient =new HashMap<>();

    @Override
    public void disconnect(Chat.connect request, StreamObserver<Chat.request> responseObserver) {
        String username = request.getNom();
        listeClient.get(username).onCompleted();
        listeClient.remove(username);
        Chat.request serverRep = Chat.request.newBuilder().setMessageFrom("ServerJeu").setContent("Disconnected....").build();
        responseObserver.onNext(serverRep);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Chat.request> send(StreamObserver<Chat.request> responseObserver) {
        return new StreamObserver<Chat.request>() {
            @Override
            public void onNext(Chat.request request) {
                String messageFrom = request.getFrom();
                String messageTo = request.getTo();
                if(!listeClient.containsKey(messageFrom)){
                    listeClient.put(messageFrom,responseObserver);
                }
                if(messageTo.isEmpty()){
                    for(String s : listeClient.keySet()){
                        if(!s.equals(messageFrom))
                            listeClient.get(s).onNext(request);
                    }
                }
                else if(listeClient.containsKey(messageTo)){
                    StreamObserver<Chat.request> requestStreamObserver = listeClient.get(messageTo);
                    requestStreamObserver.onNext(request);
                }
            }
            @Override
            public void onError(Throwable throwable) {}
            @Override
            public void onCompleted() {}
        };
    }
}
