package com.enset.clients;

import com.enset.stubs.Chat;
import com.enset.stubs.ChatGRPC;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Scanner;

public class ClientGRPC {
    public static void main(String[] args) throws IOException {
        ManagedChannel localhost = ManagedChannelBuilder.forAddress("localhost", 8090).usePlaintext().build();
        ChatGRPC.chatStub chatStub = ChatGRPC.newStub(localhost);

        String username;
        String message;
        Scanner scanner=new Scanner(System.in);

        System.out.print("Your Name : ");
        username=scanner.next();
        System.out.println(" connected Successfully");



       StreamObserver<Chat.request> send = chatStub.send(new StreamObserver<Chat.request>() {
            @Override
            public void onNext(Chat.request request) {
                String messageFrom = request.getFrom();
                String content = request.getMessage();
                System.out.println(messageFrom+" : "+content);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });

       while (true){
           System.out.print("Send To : ");
           String messageTo=scanner.next();
           scanner.nextLine();
           System.out.print("Message : ");
           message=scanner.nextLine();
           System.out.println(message);
           if(message.equals("q")) {
               Chat.connect disconnect = Chat.connect.newBuilder().setUsername(username).build();
               chatStub.disconnectReq(disconnect, new StreamObserver<Chat.request>() {
                   @Override
                   public void onNext(Chat.request request) {
                       System.out.println(request.getMessage());
                   }

                   @Override
                   public void onError(Throwable throwable) {

                   }

                   @Override
                   public void onCompleted() {

                   }
               });
               //System.in.read();
               break;
           }
           Chat.request request = Chat.request.newBuilder().setMessageFrom(username).setMessageTo(messageTo).setContent(message).build();
           send.onNext(request);
       }
    }
}
