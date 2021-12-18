package com.example.thrift;

import com.example.thrift.mythrift.StudentService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        try {
            TProcessor tprocessor = new StudentService.Processor<StudentService.Iface>(new StudentServiceImpl());
            TServerSocket serverTransport = new TServerSocket(9479);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();
            System.out.println("Server has started at port 9479");
        } catch (TTransportException e) {
            e.printStackTrace();
        }


    }


}
