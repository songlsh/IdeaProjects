import com.sls.App;
import com.sls.applicationEvent.BlackListService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class Test {

//    public static void main(String[] args) {
//        AtomicInteger nextHashCode =
//                new AtomicInteger();
//        System.out.println(nextHashCode);
//    }
    @Autowired
    private BlackListService service;

    @org.junit.Test
    public void test(){
      service.sendEmail("blackAddredd@123.com", "content");
//      service.sendEmail("blackAddredd@1231.com", "content");

    }
}
