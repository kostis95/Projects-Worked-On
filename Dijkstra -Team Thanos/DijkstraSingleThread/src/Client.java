import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Client {


    public static void main(String[] args) throws RemoteException, UnknownHostException, InterruptedException {
        String[] inputsets = new String[]{"G1.txt", "G23.txt", "G25.txt", "G36.txt", "G45.txt", "G54.txt", "G58.txt",
                "G60.txt", "G50.txt", "G70.txt"};
        String inputSetsLocation = "/Users/antoniosthanos/Desktop/Blok 2/6. Parallel Computing/Inputsets/";


        // Who am I?
        String localHostname = InetAddress.getLocalHost().getHostName();
        System.out.println("This is host:" + localHostname );

        // What is the (default) host?
        String serviceHost = Server.MasterNodeName;

        // Use command line parameter to override the default host
        if (args.length > 0)
            serviceHost = args[0];

        // connect to the host and request the service
        Service service = Server.connect(serviceHost);

        // execute a remote method
        long t1, t2;

        t1 = System.currentTimeMillis();
        Integer[] setRanges = findSetRanges(inputSetsLocation, inputsets);

        LinkedList<DijkstraSingleThread.Graph> graphs = createGraphs(inputsets, inputSetsLocation, setRanges);
        DijkstraSingleThread.Graph graphTest= graphs.get(0);
        Integer processors = 8;
        service.getShortestDistances(inputsets, graphTest, processors);

        t2 = System.currentTimeMillis();

        //System.out.println("Client side:" + greeting);
        System.out.println("SendMessage took " + (t2-t1) + " ms.");

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
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  setRanges;
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

}





