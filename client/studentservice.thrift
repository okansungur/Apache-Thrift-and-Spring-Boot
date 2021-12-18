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