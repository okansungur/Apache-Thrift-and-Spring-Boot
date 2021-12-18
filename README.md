# Apache-Thrift-and-Spring-Boot

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

For our tutorial we will create two applications Client and Server. But before we start we have to download 
