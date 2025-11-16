package topic.graph;
///https://www.geeksforgeeks.org/problems/strongly-connected-component-tarjanss-algo-1587115621/1
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class TarjansSCC {
	
    // Function to return a list of lists of integers denoting the members
    // of strongly connected components in the given graph.
    int time ;
    int[] disc;
    int[] low;
    boolean[] stacked;
    
    public void dfs(ArrayList<ArrayList<Integer>> adj, int u,Stack<Integer> st ,  ArrayList<ArrayList<Integer>> res){
        stacked[u] = true;
        st.push(u);
        disc[u] = time;
        low[u] = time;
        time++;
        
        for(int v : adj.get(u)){
            if(disc[v] == 0){///not visited
                dfs(adj,v,st,res);
                low[u] = Math.min(low[u],low[v]);
            }
            else if(stacked[v]){
                low[u] = Math.min(low[u],disc[v]);
            }
        }
        
        int w = -1;
        if(low[u] == disc[u]){
            ArrayList<Integer> component = new ArrayList<>();
            while(w != u){
                w = st.pop();
                stacked[w] = false;
                component.add(w);
            }
            Collections.sort(component);
            res.add(component);
        }
        
    }
    public ArrayList<ArrayList<Integer>> tarjans(int V,
                                                 ArrayList<ArrayList<Integer>> adj) {
        time = 1;
        disc = new int[V];
        low = new int[V];
        stacked = new boolean[V];
        Stack <Integer> st=new Stack<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for(int u = 0 ;u < V ; u++){
            if(disc[u] == 0){
                dfs(adj,u,st,res);
            }
        }
        Collections.sort(res,(a,b)->a.get(0)-b.get(0));
        return res;
        // code here
        
    }

}
