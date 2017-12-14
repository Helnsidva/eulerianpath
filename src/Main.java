import java.util.*;

import static java.lang.Math.min;
import static java.lang.StrictMath.pow;

public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addNeighbor(0, 6, 10);
        graph.addNeighbor(0, 4, 7);
        graph.addNeighbor(0, 7, 5);
        graph.addNeighbor(0, 1, 1);
        graph.addNeighbor(1, 6, 5);
        graph.addNeighbor(1, 7, 3);
        graph.addNeighbor(1, 2, 0);
        graph.addNeighbor(2, 6, 50);
        graph.addNeighbor(2, 7, 3);
        graph.addNeighbor(2, 3, 4);
        graph.addNeighbor(3, 7, 7);
        graph.addNeighbor(3, 5, 2);
        graph.addNeighbor(3, 4, 10);
        graph.addNeighbor(4, 7, 13);
        graph.addNeighbor(4, 5, 9);
        graph.addNeighbor(5, 7, 7);
        graph.addNeighbor(5, 6, 6);
        graph.addNeighbor(6, 7, 31);

        graph.printGraphData();

        List<Integer> theWay = FindEulerianPath.findCycle(graph);
        System.out.println("length = " + theWay.get(theWay.size() - 1));
        System.out.println("the way: " + theWay.subList(0, theWay.size() - 1));

        int[] checkWay = AnnealingAlgorithm.findPath(graph);
        System.out.println(Arrays.toString(checkWay));

    }

}
