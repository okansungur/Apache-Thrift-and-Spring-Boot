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

We will also place thrift compiler inside our project with   studentservice.thrift file. After adding the needed  dependencies to our pom.xml file we will run maven-clean  and maven compile commands.

<p align="center">
  <img  src="https://github.com/okansungur/Apache-Thrift-and-Spring-Boot/blob/main/thrift_compiler.png"><br/>
  Thrift compiler and our thrift file
</p>

By this way we will generate the classes 






