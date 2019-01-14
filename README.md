# WorkflowAssignmentProducerConsumer

 Service.java:
 
 On sercice class i have made two methods which will serve the functionalites of producer and consumer problem
 
 1. 
    private AlertDetail consumer(BlockingQueue<AlertDetail> currentQueue) throws InterruptedException {
        return currentQueue.take();
    }
2. 
    private void producerForNextQueue(AlertDetail alertDetail) throws InterruptedException {
        this.nextQueue.put(alertDetail);
    }
