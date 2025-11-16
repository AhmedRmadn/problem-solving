package topic.graph;

import java.util.Arrays;

/////https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1
public class BllmanFord {
	
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist,(int)1e8);
        dist[src] = 0;
        for(int i = 0 ; i < V - 1 ; i++){
            for(int[] edge : edges){
                relax(edge,dist);
            }
            
        }
        for(int[] edge : edges){/// the Vth iter to detect negative cycle
            if(relax(edge,dist))
                return new int[]{-1};
        }
        return dist;
        
    }
    public boolean relax(int[] edge , int[]dist){
        int u = edge[0];
        int v = edge[1];
        int w = edge[2];
        if(dist[u] != 1e8 && dist[u] + w < dist[v]){
            dist[v] = dist[u] + w;
            return true;
        }
        return false;
    }

}
