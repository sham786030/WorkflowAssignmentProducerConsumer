package WorkflowAssignmentProducerConsumer;

import java.util.concurrent.BlockingQueue;

public class GenerateAlert {

    private BlockingQueue<AlertDetail> queue;

    GenerateAlert(BlockingQueue<AlertDetail> queue) {
        this.queue = queue;
    }

    public void generate(){

        for(int i=1; i<6; i++){
            AlertDetail msg = new AlertDetail(""+i);
            try {
                Thread.sleep(i);
                queue.put(msg);
                System.out.println("Generating alert : "+ " "+msg.getAlertNo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
