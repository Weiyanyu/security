package top.yeonon.async;

import lombok.extern.java.Log;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@Log
@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("/order")
    public  DeferredResult<String> order() throws Exception {
        log.info("主线程开始");

        String orderNo = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNo);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNo, result);

//        Callable<String> result = () -> {
//            log.info("子线程开始");
//            Thread.sleep(1000);
//            log.info("子线程结束");
//            return "Success";
//        };

        log.info("主线程结束");
        return result;
    }

}
