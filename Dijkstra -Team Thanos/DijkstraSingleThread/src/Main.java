/**
 *
 * @author Kostis
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) throws IOException {

        String inputSetsLocation = "/Users/antoniosthanos/Desktop/Blok 2/6. Parallel Computing/Inputsets/";
        String[] inputsets = new String[]{"G1.txt", "G23.txt", "G25.txt", "G36.txt", "G45.txt", "G54.txt", "G58.txt",
                "G60.txt", "G50.txt", "G70.txt"};
        Integer[] setRanges = findSetRanges(inputSetsLocation, inputsets);


        //Selecting amount of processors
        int AvailableProcessors = Runtime.getRuntime().availableProcessors();
        int usedAmountOfProcessors;

        int desiredAmountOfProcessors = 1;
        if(desiredAmountOfProcessors < AvailableProcessors){
            usedAmountOfProcessors = desiredAmountOfProcessors;
        } else {
            usedAmountOfProcessors = AvailableProcessors;
        }

        LinkedList<DijkstraSingleThread.Graph> graphs = createGraphs(inputsets, inputSetsLocation, setRanges);

        //Creating an appropriate Graphlist for each thread
        LinkedList<LinkedList<DijkstraSingleThread.Graph>> graphListForEachThread = prepareGraphListsForEachThread(graphs, inputsets, usedAmountOfProcessors);

        //Running all the threads and measuring the time
        long beginNanoTime = System.nanoTime();
        int counter = 0;

        int countThreadFinished = 0;



        for(int i=0; i < usedAmountOfProcessors; i++) {
            //DijkstraSingleThread dijkstraSingleThread = new DijkstraSingleThread(inputsets, graphListForEachThread.get(i), usedAmountOfProcessors);
            //dijkstraSingleThread.start();
        }

        if( countThreadFinished == inputsets.length){
            long endNanoTime = System.nanoTime();
        }

        long endNanoTime = System.nanoTime();
        long totalDuration = endNanoTime - beginNanoTime;
        System.out.println("Everything " + " ran in " + totalDuration + " ns.");
    }


    public static LinkedList<DijkstraSingleThread.Graph> createGraphs(String[] inputsets, String inputSetsLocation, Integer[] setRanges) {
        //Create A dijkstra graph for each inputset, solve it, and test its speed
        LinkedList<DijkstraSingleThread.Graph> graphs = new LinkedList<DijkstraSingleThread.Graph>();
        for (int i = 0; i < inputsets.length; i++) {
            //1. max value +1 is het aantal vertices, aangezien 0 ook een edge is
            int vertices = setRanges[i] + 1;
            // run ParallelDijkstra (met deze waardes)

            //creating the Graph
            DijkstraSingleThread.Graph graph = new DijkstraSingleThread.Graph("FOKOF", 0, vertices);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(inputSetsLocation + inputsets[i]));
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

            graphs.add(graph);
        }
        return graphs;
    }

    public int addFinishedThread(int countThreadFinished){
        countThreadFinished++;
        return countThreadFinished;
    }

    public static LinkedList<LinkedList<DijkstraSingleThread.Graph>>

    prepareGraphListsForEachThread(LinkedList<DijkstraSingleThread.Graph> graphs, String[] inputsets, int usedAmountOfProcessors) throws IOException {

        //decide distribution
        int[] amountOfGraphsPerThread = new int[usedAmountOfProcessors];
        for(int i = 0; i < usedAmountOfProcessors; i++){
            amountOfGraphsPerThread[i] = graphs.size() / usedAmountOfProcessors;
        }

        //divide rest over the lists
        int rest = graphs.size() % usedAmountOfProcessors;
        for(int i=0; i < rest; i++){
            amountOfGraphsPerThread[i] += 1;
        }

        // A list for each Graph containing a list of Graphs to calculate
        LinkedList<LinkedList<DijkstraSingleThread.Graph>> graphsForThreads = new LinkedList<LinkedList<DijkstraSingleThread.Graph>>();

        int counter = 0;
        for(int i =0; i < usedAmountOfProcessors; i++) {
            LinkedList<DijkstraSingleThread.Graph> graphsForCurrentThread = new LinkedList<DijkstraSingleThread.Graph>();
            for (int j = 0; j < amountOfGraphsPerThread[i]; j++) {
                graphsForCurrentThread.add(graphs.get(counter));
                counter ++;
            }
            graphsForThreads.add(graphsForCurrentThread);
        }
        return graphsForThreads;
    }








    public static Integer[] findSetRanges(String inputSetsLocation, String[] inputsets) {
        // For loop om voor elke set zijn range te vinden en in te vullen in array.
        Integer[] setRanges = new Integer[inputsets.length];
        for (int i = 0; i < inputsets.length; i++) {
            setRanges[i] = 0;

            try {
                BufferedReader reader = new BufferedReader(new FileReader(inputSetsLocation + inputsets[i]));
                String line = reader.readLine();

                while (line != null) {

                    String[] test = line.split(" ");

                    //Misschien toch een dubbele array, [0] voor nr. inputset, [1] voor source/destination waarde.
                    Integer sourceValue = Integer.parseInt(test[0]);
                    Integer destinationValue = Integer.parseInt(test[1]);
                    if (setRanges[i] < sourceValue) {
                        setRanges[i] = sourceValue;
                    }
                    if (setRanges[i] < destinationValue) {
                        setRanges[i] = destinationValue;
                    }
                    line = reader.readLine();
                }
                System.out.println(setRanges[i]);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  setRanges;
    }




    public int[] threadAmounts(Integer[] setRanges, int amountOfThreads) throws IOException {

        int processors = Runtime.getRuntime().availableProcessors();
        int desiredAmountOfProcessors = 4;
        if (desiredAmountOfProcessors > processors){
            throw new IOException("Possible amount of available processors has been exceeded");
        }

        int amountOfTimeUnits = setRanges[0] / 4;
        //create int[] threadUnits
        int[] arrayOfTimeUnits = new int[amountOfThreads];
        for(int i=0; i < amountOfThreads; i++){
            arrayOfTimeUnits[i] = amountOfThreads;
        }
        //verdeel de rest over de cores.
        int rest = setRanges[0] % 4;
        for(int i=0; i < amountOfThreads; i++){
            arrayOfTimeUnits[i] += 1;
        }

        return arrayOfTimeUnits;
    }
}


