package topic.graph;

import java.util.Arrays;

public class kruskalMST {
    class DSU{
        int[] parent;
        int n;
        public DSU(int n){
            this.n = n;
            parent = new int[n];
            this.n = n;
            for(int i = 0 ; i < n ; i++)
                parent[i] = i;
        }
        
        public void union(int a , int b){
            int p_a = find(a);
            int p_b = find(b);
            if(p_a != p_b)
                parent[p_b] = p_a;
            
        }
        public int find(int a){
            if(a != parent[a]){
                parent[a] = find(parent[a]);
            }
            return parent[a];
        }
    }
    public int spanningTree(int V, int[][] edges) {
        DSU dsu = new DSU(V);
        Arrays.sort(edges,(a,b)->a[2] - b[2]);
        int sum = 0;
        for(int [] e : edges){
            if(dsu.find(e[0]) != dsu.find(e[1])){
                sum += e[2];
                dsu.union(e[0],e[1]);
                
            }
        }
        return sum;
        
    }

}
