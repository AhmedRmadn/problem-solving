package topic.graph;
////https://www.geeksforgeeks.org/problems/articulation-point-1/1
import java.util.ArrayList;

public class ArticulationPoint {
	
    // Function to return Breadth First Traversal of given graph.
    int time;
    int[] disc;
    int[] low;
    boolean[] visited;
    
    public void dfs(ArrayList<ArrayList<Integer>> adj, int u , boolean[] isAP, int par){
        disc[u] = time;
        low[u] = time;
        time++;
        visited[u] = true;
        int countChild = 0;
        for(int v : adj.get(u)){
            if(!visited[v]){
                countChild++;
                dfs(adj,v,isAP,u);
                low[u] = Math.min(low[u], low[v]);
                if(par != -1 && low[v] >= disc[u]){
                    isAP[u] = true;
                }
            }
            else if(v != par){
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        
        if(par == -1 && countChild>1){
            isAP[u] = true;
        }
    }
    public ArrayList<Integer> articulationPoints(int V,
                                                 ArrayList<ArrayList<Integer>> adj) {
        time = 1;
        disc = new int[V];
        low = new int[V];
        visited = new boolean[V];
        boolean[] isAP = new boolean[V];
        ArrayList<Integer> res = new ArrayList<>();
        dfs(adj,0,isAP,-1);
        for(int i = 0 ; i < V ; i++)
            if(isAP[i])
                res.add(i);
        if(res.isEmpty())
            res.add(-1);
        return res;
        
    }
    

}
