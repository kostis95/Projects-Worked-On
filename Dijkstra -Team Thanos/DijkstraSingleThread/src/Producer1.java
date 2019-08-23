import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Producer1 extends Thread {


    public Producer1(String inputSet, Integer setRange, String inputSetsLocation){
        this.inputSet = inputSet;
        this.setRange = setRange;
        this.inputSetsLocation = inputSetsLocation;
    }

    private String inputSet;
    private Integer setRange;
    private String inputSetsLocation;
   DijkstraSingleThread.Graph graph;

    @Override
    public void run(){
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
            //System.out.println("The inputset "+ inputsets[i] + " ran in " + totalDuration + " ns.");

            this.graph = graph;

        System.out.println("added Graph in run class");
        }


    public DijkstraSingleThread.Graph getGraph() {
        return graph;
    }
}

