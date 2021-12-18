# Apache-Thrift & Spring-Boot

- Thrift is now  an open-source tool from the Apache Software Foundation.
- Data types and service interfaces for the complex services  are defined in a simple definition file. Thrift compiler uses this file to generate RPC servers and clients
- Thrift supports a wide range of language support.
- Thrift is lighweight and language-independent .
- Thrift was created at Facebook and according to the technical requirements. 

Thrift is simple and as a developer we dont need to write any code for serialization or data transport. To keep things simple thrift data types don't introduce any special dynamic types or wrapper objects.
Base types for Thrift are :
- bool value boolean value, true or false
- byte value signed byte
- i16 value 16-bit signed integer
- i32 value 32-bit signed integer
- i64 value 64-bit signed integer
- double value 64-bit floating point number
- string text/binary string

The other types are Structs, Containers , Exceptions, Services

For our tutorial we will create two applications Client and Server. But before we start we have to download Apache thrift compiler.If you are working on windows please download the link for windows.
<p align="center">
  <img  src="https://github.com/okansungur/Apache-Thrift-and-Spring-Boot/blob/main/download.png"><br/>
  Apache Thrift download link and Maven dependency 
</p>

After the download we are ready to create our studentservice.thrift file. The namespace here is important here as we will use the generated classes within our applications.

```

namespace java com.example.thrift.mythrift

exception InvalidOperationException {
    1: i32 number, 
    2: string  error
}

struct StudentResource {
    1: i32 id,
    2: string studentName, 
    3: optional string  studentid 
}

service StudentService {

    StudentResource get(1:i32 id) throws (1:InvalidOperationException e),

    void save(1:StudentResource resource) throws (1:InvalidOperationException e),

    list <StudentResource> getList() throws (1:InvalidOperationException e),

    bool ping() throws (1:InvalidOperationException e)
}

```
We put some basic data types and our student data consists id, studentName and  studentid fields . Our service has 4 methods that save and get our student data and also the ping method.


We will also place thrift compiler inside our project with   studentservice.thrift file. 
We will open the command prompt and run the code to generate the classes  **thrift-0.15.0.exe -r --gen java studentservice.thrift**
By this way we will generate the classes needed for our server application. 


<p align="center">
  <img  src="https://github.com/okansungur/Apache-Thrift-and-Spring-Boot/blob/main/thrift_compiler.png"><br/>
  Thrift compiler and our thrift file
</p>

After adding the needed  dependencies to our pom.xml file we will run maven-clean  and maven compile commands, we will create a StudentServiceImpl class that implements the StudentService.Iface. This interface is autogenerated by Thrift Compiler. You can go and check for it, from thmythrift package.

```

public class StudentServiceImpl implements StudentService.Iface {

    @Override
    public StudentResource get(int id) throws InvalidOperationException, TException {
        //..find student by id
        return new StudentResource();
    }

    @Override
    public void save(StudentResource resource) throws InvalidOperationException, TException {
        //..saving
        System.out.println("Student saved!!");
    }

    @Override
    public List<StudentResource> getList() throws InvalidOperationException, TException {
        List<StudentResource> studentResourceList = new ArrayList<>();
        StudentResource studentResource = new StudentResource();
        studentResource.setId(112);
        studentResource.setStudentName("Lucy");
        studentResource.setStudentid("157112");
        studentResourceList.add(studentResource);

        return studentResourceList;
    }

    @Override
    public boolean ping() throws InvalidOperationException, TException {
        return true;
    }
}

```
We implement the methods according to our needs. To demonstrate the data we have only defined getList() method. We defined a student with name Lucy.
Now we are ready to code our ServerApplication. We will be using port 9479 for incoming data.

```

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


```

We have completed the  server application. and now we are ready to go with the client application. We will again use our thrift compiler to generate our resource codes. Just repeat the same steps like we did in our server application.


<p align="center">
  <img  src="https://github.com/okansungur/Apache-Thrift-and-Spring-Boot/blob/main/thrift_client.png"><br/>
  Thrift compiler client side
</p>



Now we are ready to create the ClientApplication.
```
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

```


The TTransport provides a simple abstraction for reading and writing  operations. If you are familiar with java networking the methods are similar.
- open
- close
- read
- write
- flush




