package topic.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	////V E
	/// 2*V + E
	////// V*E*LOG(V)
    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here
        @SuppressWarnings("unchecked")
        List<int[]>[] graph = new List[V];
        for(int i = 0 ; i < V ;i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] e : edges){
            graph[e[0]].add(new int[]{e[1],e[2]});
            graph[e[1]].add(new int[]{e[0],e[2]});
        }
        int[] dist = new int[V];
        Arrays.fill(dist,-1);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1] - b[1]);
        pq.offer(new int[]{src,0});
        boolean[] visited = new boolean[V];
        while(!pq.isEmpty()){
            int[] curr =pq.poll();
            int u = curr[0];
            if(dist[u] != -1)
                continue;
            dist[u] = curr[1];
            for(int[] e : graph[u]){
                int v = e[0];
                if(dist[v] == -1){
                    pq.offer(new int[]{v,dist[u] + e[1]});
                }
            }
        }
        return dist;
        
    }

}
