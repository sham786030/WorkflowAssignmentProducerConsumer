package WorkflowAssignmentProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WorkflowAssignment {

    public static void main(String[] args) {
        //Creating BlockingQueue of size 10
        BlockingQueue<AlertDetail> supervisorQueue = new ArrayBlockingQueue<>(10);
        BlockingQueue<AlertDetail> reviewerQueue = new ArrayBlockingQueue<>(10);
        BlockingQueue<AlertDetail> confirmerQueue = new ArrayBlockingQueue<>(10);

        //Generating the Alert and pushing to queue of supervisor
        GenerateAlert generateAlert = new GenerateAlert(supervisorQueue);
        generateAlert.generate();

        //Running the service for supervisor to consume the business logic & to produce for the next Queue
        Service supervisorService = new Service(supervisorQueue, reviewerQueue, "Supervisor");

        //Running the service for reviewer to consume the business logic & to produce for the next Queue
        Service reviewerService = new Service(reviewerQueue, confirmerQueue, "Reviewer");

        //Running the service for confirmer to consume the business logic & to produce for the next Queue
        Service confirmerService = new Service(confirmerQueue, null, "Confirmer");

        new Thread(supervisorService).start();
        new Thread(reviewerService).start();
        new Thread(confirmerService).start();

    }
}
