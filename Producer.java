package WorkflowAssignmentProducerConsumer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue<AlertDetail> currentQueue;
    private BlockingQueue<AlertDetail> nextQueue;
    private String producerName;

    public Producer(BlockingQueue<AlertDetail> currentQueue, BlockingQueue<AlertDetail> nextQueue, String producerName){
        this.currentQueue =currentQueue;
        this.producerName=producerName;
        this.nextQueue = nextQueue;
    }
    @Override
    public void run() {

        try {

            while(true) {
                if (currentQueue != null) {
                    nextQueue.put(currentQueue.take());
                    System.out.println(producerName + " " + currentQueue.take().getAlertNo());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        }
}
