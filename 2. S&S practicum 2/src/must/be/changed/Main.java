package must.be.changed;
import java.awt.Adjustable;
import java.util.Iterator;

import nl.hva.dmci.ict.sortingsearching.tileworld.*;
import nl.hva.dmci.ict.sortingsearching.weigthedgraph.AdjMatrixEdgeWeightedDigraph;
import nl.hva.dmci.ict.sortingsearching.weigthedgraph.DirectedEdge;
import nl.hva.dmci.ict.sortingsearching.weigthedgraph.EdgeWeightedDigraph;
/**
 * TODO make sure your code is compliant with the HBO-ICT coding conventions!
 * @author ???
 */
public class Main{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    	for (int i = 1; i < 22; i++){
	    	EdgeWeightedDigraph eD = new EdgeWeightedDigraph("i" + i);
	    	Dijkstra dijkstra = new Dijkstra(eD, eD.getStart());
	    	
	    	if (dijkstra.hasPathTo(eD.getEnd())){
	    		Iterable<DirectedEdge> antwoord = dijkstra.pathTo(eD.getEnd());
	        	System.out.println("Aantal onderzochte knopen bij Dijkstra: " + dijkstra.getAantalOnderzochteKnopen());
	        	Iterator it = antwoord.iterator();
	        	int totalWeightDijkstra = 0;
	        	int totalLengthDijkstra = 1;
	        	while(it.hasNext()){
	        		DirectedEdge dE = (DirectedEdge)it.next();
	        		totalWeightDijkstra += dE.getWeight();
	        		totalLengthDijkstra ++;
	        	}
	        	System.out.println("Lengte van het kortste pad" + i + "(met Dijkstra) is: " + totalLengthDijkstra);
	        	System.out.println("Kosten van het kortste pad" + i + "(met Dijkstra) zijn: " + totalWeightDijkstra);
	    		eD.tekenPad(antwoord);
	    		eD.save("i" + i + "-Dijkstra");
	    	}

	    	
	    	EdgeWeightedDigraph eDFloyd = new EdgeWeightedDigraph("i" + i);
	    	AdjMatrixEdgeWeightedDigraph adjMEWD = eDFloyd.createAdjMatrixEdgeWeightedDigraph();
	    	
	    	FloydWarshall fW = new FloydWarshall(adjMEWD);
	    	if(fW.hasPath(eDFloyd.getStart(), eDFloyd.getEnd())){
	    		int lengtePadFW = 1;
	    		Iterable<DirectedEdge> antwoord = fW.path(eDFloyd.getStart(), eDFloyd.getEnd());
	    		Iterator it = antwoord.iterator();
	    		while(it.hasNext()){
	        		DirectedEdge dE = (DirectedEdge)it.next();
	        		lengtePadFW++;
	        	}
	    		System.out.println("Aantal knopen onderzocht bij FlodWarshall: " + fW.getAantalKnopenOnderzocht());
	    		System.out.println("Lengte van het kortste pad" + i + "(met FloydWarshall) is: " + lengtePadFW);
	    		System.out.println("Kosten van het kortste pad" + i + " (met FloydWarshall) zijn: " + fW.dist(eDFloyd.getStart(), eDFloyd.getEnd()));
	    		eDFloyd.tekenPad(fW.path(eDFloyd.getStart(), eDFloyd.getEnd()));
	    		eDFloyd.save("i" + i + "-Floyd");
	    	}
    	}

    }
}
