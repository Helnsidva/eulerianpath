import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;
import static java.lang.Math.pow;

public class FindEulerianPath {

    final static int INFINITY = 1000000;

    static List<Integer> getIncomingVertices(int mask) {

        List<Integer> output = new ArrayList<>();
        int buffer;
        for (int i = 0; mask != 0; i++) {
            buffer = mask % 2;
            mask /= 2;
            if (buffer != 0) {
                output.add(i);
            }
        }
        return output;

    }

    static List<Integer> findCycle(Graph graph) {

        int graphSize = graph.getGraphSize();
        int[][] viewedWays = new int[graphSize][(int)pow(2, graphSize)];
        for(int i = 0; i < graphSize; i++) {
            for(int j = 0; j < (int)pow(2, graphSize); j++) {
                viewedWays[i][j] = INFINITY;
            }
        }

        viewedWays[0][0] = 0;

        int wayLength = findCheapest(0, (int)pow(2, graphSize) - 1, viewedWays, graph);

        List<Integer> output = findWay(viewedWays, graph);
        output.add(wayLength);

        return output;

    }

    static int findCheapest(int i, int mask, int[][] viewedWays, Graph graph) {

        if (viewedWays[i][mask] != INFINITY)
            return viewedWays[i][mask];

        List<Integer> maskContaining = getIncomingVertices(mask);
        for (int j: maskContaining) {

            if (j == i)
                continue;

            if (graph.getDistance(i, j) != null) {

                viewedWays[i][mask] = min(viewedWays[i][mask],
                        findCheapest(j, mask ^ (int)pow(2, j), viewedWays, graph) + graph.getDistance(i, j));

            }

        }
        return viewedWays[i][mask];

    }

    static List<Integer> findWay(int[][] viewedWays, Graph graph) {

        int graphSize = graph.getGraphSize();
        int i = 0;
        int mask = (int)pow(2, graphSize) - 1;

        List<Integer> theWay = new ArrayList<>();

        theWay.add(0);

        while (mask != 0) {
            List<Integer> maskWays = getIncomingVertices(mask);
            for (int j: maskWays) {

                if (graph.getDistance(i, j) != null &&
                        viewedWays[i][mask] == viewedWays[j][mask ^ (int)pow(2, j)] + graph.getDistance(i, j)) {

                    theWay.add(j);
                    i = j;
                    mask = mask ^ (int)pow(2, j);
                    continue;


                }
            }
        }
        return theWay;

    }

}
