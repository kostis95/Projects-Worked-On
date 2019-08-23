import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by konin on 22-6-2016.
 */


class Producer implements Runnable {


    protected BlockingQueue<DijkstraSingleThread.Graph> queue;
    protected int counter;
    protected int setRange;
    protected String inputSet;
    protected String inputSetsLocation;

    public Producer(String inputSet, String inputSetsLocation, Integer setRange, BlockingQueue<DijkstraSingleThread.Graph> queue) {
        this.queue = queue;
        this.counter = 0;
        this.inputSet = inputSet;
        this.setRange = setRange;
        this.inputSetsLocation = inputSetsLocation;
        DijkstraSingleThread.Graph graph;

    }

    @Override
    public void run() {

        // keep running
//        counter = 0;
//        while (counter != 10) {

            try {
                //1. max value +1 is het aantal vertices, aangezien 0 ook een edge is
                int vertices = setRange + 1;
                // run ParallelDijkstra (met deze waardes)

                //creating the Graph
                DijkstraSingleThread.Graph graph = new DijkstraSingleThread.Graph(inputSet,0, vertices);
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(inputSetsLocation + inputSet));
                    String line = reader.readLine();
                    while (line != null) {
                        String[] test = line.split(" ");
                        graph.addEdge(Integer.parseInt(test[0]), Integer.parseInt(test[1]), Integer.parseInt(test[2]));
                        line = reader.readLine();
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Het meten en uitvoeren van het Dijkstra Algoritme
                long beginNanoTime = System.nanoTime();
                graph.run();
                long endNanoTime = System.nanoTime();
                long totalDuration = endNanoTime - beginNanoTime;
                System.out.println("The inputset "+ inputSet + " was created in " + totalDuration + " ns.");

                //System.out.println("added Graph");


                queue.put(graph);
                //System.out.println("Produced "+ counter);
                counter++;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


    }
}

class SmartProducer extends Producer {
    private static int ID_NOT_USED = -99;
    private int id; // to identify a consumer
    private int totalProduction;

    public SmartProducer(int id, String inputSet, String inputSetsLocation, Integer setRange, BlockingQueue<DijkstraSingleThread.Graph> queue) {
        super(inputSet, inputSetsLocation, setRange, queue);
        this.id = id;
    }

//
//    public SmartProducer(int id, BlockingQueue<DijkstraSingleThread.Graph> queue) {
////        super(queue);
//        this.id = id;
//        this.totalProduction = 0;
//    }


    @Override
    public void run() {

         totalProduction ++;
         System.out.println("Producer "+ id + "("+ totalProduction + ") creates " + inputSet);
         try {
            //1. max value +1 is het aantal vertices, aangezien 0 ook een edge is
            int vertices = setRange + 1;
            // run ParallelDijkstra (met deze waardes)

            //creating the Graph
            DijkstraSingleThread.Graph graph = new DijkstraSingleThread.Graph(inputSet,0, vertices);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(inputSetsLocation + inputSet));
                String line = reader.readLine();
                while (line != null) {
                    String[] test = line.split(" ");
                    graph.addEdge(Integer.parseInt(test[0]), Integer.parseInt(test[1]), Integer.parseInt(test[2]));
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Het meten en uitvoeren van het Dijkstra Algoritme
            long beginNanoTime = System.nanoTime();
            graph.run();
            long endNanoTime = System.nanoTime();
            long totalDuration = endNanoTime - beginNanoTime;
            System.out.println("The inputset "+ inputSet + " was created in " + totalDuration + " ns.");

            //System.out.println("added Graph");


            queue.put(graph);
            //System.out.println("Produced "+ counter);
            counter++;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {

    String[] inputsets;
    LinkedList<DijkstraSingleThread.Graph> graphs;
    private int usedAmountOfProcessors;
    BlockingQueue<DijkstraSingleThread.Graph> queue;

    public Consumer(int usedAmountOfProcessors, String[] inputsets, BlockingQueue<DijkstraSingleThread.Graph> queue)  {
        this.usedAmountOfProcessors = usedAmountOfProcessors;
        this.inputsets = inputsets;
        this.queue = queue;

    }


        public Consumer(BlockingQueue<DijkstraSingleThread.Graph> queue) {
            this.queue = queue;
        }

        public Consumer(int id, BlockingQueue<DijkstraSingleThread.Graph> queue) {
            this.queue = queue;
            System.out.println("hi");
        }

        @Override
        public void run() {
            DijkstraSingleThread.Graph inputsetRunning;
            Random rnd = new Random();
            int counter = 1;
            // keep running
            while(counter != inputsets.length) {
                try {
                    inputsetRunning = queue.take();
                    long beginNanoTime = System.nanoTime();
                    //hier geef gewoon 1,2  -- 3,4 -- 5,6
                    DijkstraSingleThread dijkstraSingleThread = new DijkstraSingleThread(inputsets, inputsetRunning, usedAmountOfProcessors);
                    dijkstraSingleThread.start();
                    System.out.println("Consumer takes inputset " + inputsetRunning.getInputsetName());

                    // slow down the consumer
                     //Thread.sleep(rnd.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
            }
        }

    public void setGraphs(LinkedList<DijkstraSingleThread.Graph> graphs) {
        this.graphs = graphs;
    }
}

class SmartConsumer extends Consumer {

    private static int ID_NOT_USED = -99;
    private int id; // to identify a consumer
    private int totalConsumption;

    public SmartConsumer(int id, String inputSet, String inputSetsLocation, Integer setRange, BlockingQueue<DijkstraSingleThread.Graph> queue) {
        super(queue);
        this.id = id;
        this.totalConsumption = 0;
    }

    @Override
    public void run() {

        DijkstraSingleThread.Graph inputsetRunning;

            try {
                inputsetRunning = queue.take();
                long beginNanoTime = System.nanoTime();
                //hier geef gewoon 1,2  -- 3,4 -- 5,6
                DijkstraSingleThread dijkstraSingleThread = new DijkstraSingleThread(inputsets, inputsetRunning, 1);
                dijkstraSingleThread.start();

                totalConsumption ++;
                System.out.println("Consumer "+ id + "("+ totalConsumption + ") takes " + inputsetRunning.getInputsetName());
                // slow down the consumer
                //Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


// can be a filter
class Broker implements Runnable {
    //protected BlockingQueue<Integer> input;
    //protected BlockingQueue<Integer> output;

    public Broker(BlockingQueue<DijkstraSingleThread.Graph> input, BlockingQueue<DijkstraSingleThread.Graph> output) {
        //this.input = input;
        //this.output = output;
    }

    @Override
    public void run() {

        while (true) {
//            try {
//                int value = input.take();
//                int newValue = value-50; // take out commission
//                output.put(newValue);
//
//                System.out.println(" Broker takes "+value + " and puts "+ newValue);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

public class ProducerConsumer {

    protected BlockingQueue<DijkstraSingleThread.Graph> queue;
    protected int counter;
    protected Integer[] setRanges;
    protected String[] inputSets;
    protected String inputSetsLocation;

    public ProducerConsumer(String[] inputSets, String inputSetsLocation, Integer[] setRanges) {
        this.counter = 0;

        this.inputSets = inputSets;
        this.setRanges = setRanges;
        this.inputSetsLocation = inputSetsLocation;
        //DijkstraSingleThread.Graph graph;
    }

    public void multipleSlowSmartProducersMultipleSlowSmartConsumers(int queueCapacity, int producerCount,
                                                                     int consumerCount) throws InterruptedException {

        int AvailableProcessors = Runtime.getRuntime().availableProcessors();
        int usedAmountOfProcessors;

        int desiredAmountOfProcessors = 1;
        if(desiredAmountOfProcessors < AvailableProcessors){
            usedAmountOfProcessors = desiredAmountOfProcessors;
        } else {
            usedAmountOfProcessors = AvailableProcessors;
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        // the producer and consumer share a blocking queue
        BlockingQueue<DijkstraSingleThread.Graph> queue = new ArrayBlockingQueue<DijkstraSingleThread.Graph>(queueCapacity);

        //Hier miskien RMI?
        for (int i = 0; i< inputSets.length ; i++) {

            SmartProducer producer = new SmartProducer(i, inputSets[i], inputSetsLocation, setRanges[i], queue);
            executorService.submit(producer);
            SmartConsumer consumer = new SmartConsumer(i, inputSets[i], inputSetsLocation, setRanges[i], queue);
            executorService.submit(consumer);
            //producer.run();
            //SmartProducer smartProducer = new SmartProducer(i, queue);
            //executorService.submit(smartProducer);
        }
//
//        for (int i = 0; i< inputSets.length; i++) { //10 keer runnen
//
//
//            //consumer.run();
//        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        if (executorService.isTerminated())
            System.out.println(queue.size());
    }


    public void singleProducerSingleSlowConsumer(String inputset, String inputSetLocation, Integer setRange, int queueCapacity) throws InterruptedException {

        BlockingQueue<DijkstraSingleThread.Graph> queue = new ArrayBlockingQueue<DijkstraSingleThread.Graph>(queueCapacity);
        ExecutorService executorService = Executors.newWorkStealingPool();

        // the producer and consumer share a blocking queue
            Producer producer = new Producer(inputset, inputSetLocation, setRange, queue);
            Consumer consumer = new Consumer(queue);
        // fire up producer and consumer
        executorService.submit(producer);
        executorService.submit(consumer);

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        if (executorService.isTerminated())
            System.out.println(queue.size());
    }


    public void singleProducerMultipleSlowSmartConsumers(int queueCapacity, int consumerCount) throws InterruptedException {
        // a producer/consumer solution with a BlockingQueue
        // solution: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html

        ExecutorService executorService = Executors.newCachedThreadPool();

        // the producer and consumer share a blocking queue
        BlockingQueue<DijkstraSingleThread.Graph> queue = new ArrayBlockingQueue<DijkstraSingleThread.Graph>(queueCapacity);

//        Producer producer = new Producer(queue);
//        executorService.submit(producer);

        for (int i = 1; i<= consumerCount; i++) {

            //SmartConsumer smartConsumer = new SmartConsumer(i, queue);
           // executorService.submit(smartConsumer);
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        if (executorService.isTerminated())
            System.out.println(queue.size());

    }







    public void SingleProducerBrokerConsumerPipe(int producerMarketSize, int consumerMarketSize) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        BlockingQueue<DijkstraSingleThread.Graph> producerMarket = new ArrayBlockingQueue<DijkstraSingleThread.Graph>(producerMarketSize);
        BlockingQueue<DijkstraSingleThread.Graph> consumerMarket = new ArrayBlockingQueue<DijkstraSingleThread.Graph>(consumerMarketSize);

        // a producer produces on the producermarket
        //Producer producer = new Producer(producerMarket);
        // a consumer consumes on the consumermarket
        Consumer consumer = new Consumer(consumerMarket);
        // a broker buys on the producermarket and sells on the consumermarket
        Broker broker = new Broker(producerMarket, consumerMarket);

        //executorService.submit(producer);
        executorService.submit(consumer);
        executorService.submit(broker);

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }


    // use Xint VM setting to prevent JIT optimizations (?)
    public static void main(String[] args) throws InterruptedException {

        String inputSetsLocation = "/Users/antoniosthanos/Desktop/Blok 2/6. Parallel Computing/Inputsets/";
        String[] inputsets = new String[]{"G1.txt", "G23.txt", "G25.txt", "G36.txt", "G45.txt", "G54.txt", "G58.txt",
                "G60.txt", "G50.txt", "G70.txt"};
        Integer[] setRanges = Main.findSetRanges(inputSetsLocation, inputsets);

        //LinkedList<DijkstraSingleThread.Graph> graphs = callCreateGraphs(inputsets, inputSetsLocation, setRanges);
        ProducerConsumer pc = new ProducerConsumer(inputsets, inputSetsLocation, setRanges);
        pc.multipleSlowSmartProducersMultipleSlowSmartConsumers(inputsets.length, 4, 4);
        //pc.singleProducerSingleSlowConsumer(inputsets[0], inputSetsLocation, setRanges[0], 20);
//        pc.singleProducerMultipleSlowSmartConsumers(20, 10);
//        pc.multipleSlowSmartProducersMultipleSlowSmartConsumers(20, 2, 5);
//        pc.SingleProducerBrokerConsumerPipe(5, 10);
    }

    public static LinkedList<DijkstraSingleThread.Graph> callCreateGraphs(String[] inputsets, String inputSetsLocation, Integer[] setRanges) {
        LinkedList<DijkstraSingleThread.Graph> graphs = new LinkedList<DijkstraSingleThread.Graph>();
        for (int i = 0; i < inputsets.length; i++) {
            Producer1 producer1 = new Producer1(inputsets[i], setRanges[i], inputSetsLocation);
            producer1.start();
            producer1.getGraph();
        }
        return graphs;
    }
}
