package com.example.thrift;

import com.example.thrift.mythrift.StudentResource;
import com.example.thrift.mythrift.StudentService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);

        TTransport transport = null;
        try {
            transport = new TSocket("localhost", 9479);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            StudentService.Client client = new StudentService.Client(protocol);
            boolean result = client.ping();
            System.out.println("ping result is " + result);
            List<StudentResource> resources = client.getList();
            System.out.println("Student name is " + resources.get(0).studentName);
            System.out.println("Student id is " + resources.get(0).studentid);
            transport.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                transport.close();
            }
        }

    }

}
