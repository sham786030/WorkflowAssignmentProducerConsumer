package WorkflowAssignmentProducerConsumer;

import java.util.concurrent.BlockingQueue;

public class Service implements Runnable{

    private BlockingQueue<AlertDetail> currentQueue;
    private BlockingQueue<AlertDetail> nextQueue;
    private String consumerName;

    Service(BlockingQueue<AlertDetail> currentQueue, BlockingQueue<AlertDetail> nextQueue, String consumerName){
        this.currentQueue=currentQueue;
        this.nextQueue=nextQueue;
        this.consumerName=consumerName;
    }

    @Override
    public void run() {

        try {
            while(true) {
                if (nextQueue != null) {
                    if(currentQueue.size() > 0) {
                        AlertDetail alertDetail = consumer(currentQueue);
                        System.out.println(consumerName + " " + alertDetail.getAlertNo());
                        producerForNextQueue(alertDetail);
                    }
                } else {
                    System.out.println(consumerName + " : Alert " + currentQueue.take().getAlertNo() + " has been confirmed");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private AlertDetail consumer(BlockingQueue<AlertDetail> currentQueue) throws InterruptedException {
        return currentQueue.take();
    }

    private void producerForNextQueue(AlertDetail alertDetail) throws InterruptedException {
        this.nextQueue.put(alertDetail);
    }
}
