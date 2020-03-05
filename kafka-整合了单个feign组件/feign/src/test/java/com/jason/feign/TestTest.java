package com.jason.feign;

        import com.jason.kafka.entity.Student;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;

        import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/3/3  15:55
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTest {

    @Autowired
    private FeignTest feignTest;

    @Test
    public void test(){
        List<Student> students = feignTest.send2();
        for(Student stu: students){
            System.out.println(stu);
        }
    }
}