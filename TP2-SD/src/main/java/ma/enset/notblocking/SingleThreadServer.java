package ma.enset.notblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SingleThreadServer {

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("0.0.0.0", 4444));
        //int validops=serverSocketChannel.validOps();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true){
            int channelCount = selector.select();
            if(channelCount==0) continue;
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    handelAccept(selectionKey,selector);
                }else if(selectionKey.isReadable()){
                    handleReadWrite(selectionKey,selector);
                }
                iterator.remove();
            }
        }
    }

    private static void handleReadWrite(SelectionKey selectionKey,Selector selector) throws IOException {
        SocketChannel socketChannel=(SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        int dataSize=socketChannel.read(byteBuffer);
        if(dataSize==-1){
            System.out.println(String.format(" The client %s has been disconnected",socketChannel.getRemoteAddress().toString()));
        }
        String request=new String(byteBuffer.array()).trim();
        System.out.println(String.format("New request %s from %s",request,socketChannel.getRemoteAddress().toString()));
        String response =new StringBuffer(request).reverse().toString().toUpperCase()+"\n";
        ByteBuffer byteBufferResponse=ByteBuffer.allocate(1024);
        byteBufferResponse.put(response.getBytes());
        byteBufferResponse.flip();
        socketChannel.write(byteBufferResponse);


    }

    private static void handelAccept(SelectionKey selectionKey, Selector selector) throws IOException {
        ServerSocketChannel serverSocketChannel=(ServerSocketChannel) selectionKey.channel();
        SocketChannel socketChannel=serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector,SelectionKey.OP_READ);
        System.out.println(String.format("New connection from %s ",socketChannel.getRemoteAddress().toString()));
    }
}
