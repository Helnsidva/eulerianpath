import java.util.Random;

public class AnnealingAlgorithm {

    static int[] findPath(Graph graph) {

        int graphSize = graph.getGraphSize();

        int[] path = new int[graphSize + 1];

        for(int i = 0; i < graphSize; i++) {
            path[i] = i;
        }
        path[graphSize] = 0;

        Random random = new Random();

        for(int fails = 0; fails < graphSize * graphSize; ) {

            int p1;
            int p2;
            do {

                p1 = random.nextInt(graphSize - 1);
                p2 = random.nextInt(graphSize - 1);

            } while (p1 == p2 || p1 == 0 || p2 == 0);

            int sum1 = graph.getNotNullDistance(path[p1 - 1], path[p1]) + graph.getNotNullDistance(path[p1], path[p1 + 1]) +
                    graph.getNotNullDistance(path[p2 - 1], path[p2]) + graph.getNotNullDistance(path[p2], path[p2 + 1]);

            int sum2 = graph.getNotNullDistance(path[p1 - 1], path[p2]) + graph.getNotNullDistance(path[p2], path[p1 + 1]) +
                    graph.getNotNullDistance(path[p2 - 1], path[p1]) + graph.getNotNullDistance(path[p1], path[p2 + 1]);

            if (sum2 < sum1) {
                int temp = path[p1];
                path[p1] = path[p2];
                path[p2] = temp;
            }

            else
                fails++;

        }
        return path;

    }

}
