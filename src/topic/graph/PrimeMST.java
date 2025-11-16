package topic.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimeMST {
	
    public int spanningTree(int V, int[][] edges) {
        @SuppressWarnings("unchecked")
        List<int[]>[] graph = new ArrayList[V];
        for(int i = 0 ; i < V ; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] e: edges){
            graph[e[0]].add(new int[]{e[1],e[2]});
            graph[e[1]].add(new int[]{e[0],e[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        boolean[] visited = new boolean[V];
        pq.offer(new int[]{0,0});
        int sum = 0;
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int u = curr[0];
            if(visited[u])
                continue;
            visited[u] = true;
            sum += curr[1];
            for(int[]e : graph[u]){
                int v = e[0];
                if(!visited[v]){
                    pq.offer(e);
                }
            }
            
            
        }
        return sum;
    }

}
