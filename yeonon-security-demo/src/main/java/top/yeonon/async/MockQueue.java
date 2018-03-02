package top.yeonon.async;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Log
@Component
public class MockQueue {

    private String placeOrder;
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {

        new Thread(() -> {
            this.placeOrder = placeOrder;
            log.info("下订单" + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("订单处理完毕" + completeOrder);
        }).start();


    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
