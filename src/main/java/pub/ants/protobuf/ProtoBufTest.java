package pub.ants.protobuf;

import pub.ants.protobuf.DataInfo.Student;

/**
 * @author magw
 * @version 1.0
 * @date 2020/11/21 上午9:39
 * @description: No Description
 * // protoc --java_out=src/main/java src/protobuf/Student.proto
 */
public class ProtoBufTest {

    public static void main(String[] args) throws Exception {
        Student student = Student.newBuilder().setAge(28).setAddress("上海").setName("zhangsan")
            .build();
        System.out.println(student);
        byte[] studentArr = student.toByteArray();

        DataInfo.Student student2 = DataInfo.Student.parseFrom(studentArr);
        System.out.println(student2);
    }

}
