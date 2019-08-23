import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    String sendMessage(String message) throws RemoteException;
     void getShortestDistances(String[] inputsets, DijkstraSingleThread.Graph graph, Integer processors) throws RemoteException;
    <T> T executeTask(Task<T> t) throws RemoteException;
}