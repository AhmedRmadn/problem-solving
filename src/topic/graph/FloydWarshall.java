package topic.graph;

public class FloydWarshall {
    public void floydWarshall(int[][] dist) {
        // Code here
        int n = dist.length;
        for(int k = 0 ;k < n ; k++){
            for(int u = 0 ; u < n ; u++){
                for(int v = 0; v < n; v++){
                    if(dist[u][k] != 1e8 && dist[k][v] != 1e8){
                        dist[u][v] = Math.min(dist[u][v], dist[u][k] + dist[k][v]);
                    }
                }
            }
        }
    }

}
