package topic.graph;
///////https://www.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * 

import java.util.ArrayList;

import java.util.List;

​

class GFG {

​

    // dfs Function to reach destination

    boolean dfs(int curr, int des, List<List<Integer>> adj,

                List<Integer> vis) {

​

        // If curr node is destination return true

        if (curr == des) {

            return true;

        }

        vis.set(curr, 1);

        for (int x : adj.get(curr)) {

            if (vis.get(x) == 0) {

                if (dfs(x, des, adj, vis)) {

                    return true;

                }

            }

        }

        return false;

    }

​

    // To tell whether there is path from source to

    // destination

    boolean isPath(int src, int des, List<List<Integer>> adj) {

        List<Integer> vis = new ArrayList<>(adj.size() + 1);

        for (int i = 0; i <= adj.size(); i++) {

            vis.add(0);

        }

        return dfs(src, des, adj, vis);

    }

​

    // Function to return all the strongly connected

    // component of a graph.

    List<List<Integer>> findSCC(int n, List<List<Integer>> a) {

        // Stores all the strongly connected components.

        List<List<Integer>> ans = new ArrayList<>();

​

        // Stores whether a vertex is a part of any Strongly

        // Connected Component

        List<Integer> is_scc = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {

            is_scc.add(0);

        }

​

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {

            adj.add(new ArrayList<>());

        }

​

        for (List<Integer> edge : a) {

            adj.get(edge.get(0)).add(edge.get(1));

        }

​

        // Traversing all the vertices

        for (int i = 1; i <= n; i++) {

​

            if (is_scc.get(i) == 0) {

​

                // If a vertex i is not a part of any SCC

                // insert it into a new SCC list and check

                // for other vertices whether they can be

                // the part of this list.

                List<Integer> scc = new ArrayList<>();

                scc.add(i);

​

                for (int j = i + 1; j <= n; j++) {

​

                    // If there is a path from vertex i to

                    // vertex j and vice versa, put vertex j

                    // into the current SCC list.

                    if (is_scc.get(j) == 0 && isPath(i, j, adj)

                            && isPath(j, i, adj)) {

                        is_scc.set(j, 1);

                        scc.add(j);

                    }

                }

​

                // Insert the SCC containing vertex i into

                // the final list.

                ans.add(scc);

            }

        }

        return ans;

    }

}

​

public class Main {

​

    public static void main(String[] args) {

​

        GFG obj = new GFG();

        int V = 5;

        List<List<Integer>> edges = new ArrayList<>();

        edges.add(new ArrayList<>(List.of(1, 3)));

        edges.add(new ArrayList<>(List.of(1, 4)));

        edges.add(new ArrayList<>(List.of(2, 1)));

        edges.add(new ArrayList<>(List.of(3, 2)));

        edges.add(new ArrayList<>(List.of(4, 5)));

        List<List<Integer>> ans = obj.findSCC(V, edges);

        System.out.println("Strongly Connected Components are:");

        for (List<Integer> x : ans) {

            for (int y : x) {

                System.out.print(y + " ");

            }

            System.out.println();

        }

    }

}

​

// This code is contributed by shivamgupta310570

import java.util.ArrayList;

import java.util.List;

​

class GFG {

​

    // dfs Function to reach destination

    boolean dfs(int curr, int des, List<List<Integer>> adj,

                List<Integer> vis) {

​

        // If curr node is destination return true

        if (curr == des) {

            return true;

        }

        vis.set(curr, 1);

        for (int x : adj.get(curr)) {

            if (vis.get(x) == 0) {

                if (dfs(x, des, adj, vis)) {

                    return true;

                }

            }

        }

        return false;

    }

​

    // To tell whether there is path from source to

    // destination

    boolean isPath(int src, int des, List<List<Integer>> adj) {

        List<Integer> vis = new ArrayList<>(adj.size() + 1);

        for (int i = 0; i <= adj.size(); i++) {

            vis.add(0);

        }

        return dfs(src, des, adj, vis);

    }

​

    // Function to return all the strongly connected

    // component of a graph.

    List<List<Integer>> findSCC(int n, List<List<Integer>> a) {

        // Stores all the strongly connected components.

        List<List<Integer>> ans = new ArrayList<>();

​

        // Stores whether a vertex is a part of any Strongly

        // Connected Component

        List<Integer> is_scc = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {

            is_scc.add(0);

        }

​

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {

            adj.add(new ArrayList<>());

        }

​

        for (List<Integer> edge : a) {

            adj.get(edge.get(0)).add(edge.get(1));

        }

​

        // Traversing all the vertices

        for (int i = 1; i <= n; i++) {

​

            if (is_scc.get(i) == 0) {

​

                // If a vertex i is not a part of any SCC

                // insert it into a new SCC list and check

                // for other vertices whether they can be

                // the part of this list.

                List<Integer> scc = new ArrayList<>();

                scc.add(i);

​

                for (int j = i + 1; j <= n; j++) {

​

                    // If there is a path from vertex i to

                    // vertex j and vice versa, put vertex j

                    // into the current SCC list.

                    if (is_scc.get(j) == 0 && isPath(i, j, adj)

                            && isPath(j, i, adj)) {

                        is_scc.set(j, 1);

                        scc.add(j);

                    }

                }

​

                // Insert the SCC containing vertex i into

                // the final list.

                ans.add(scc);

            }

        }

        return ans;

    }

}

​

public class Main {

​

    public static void main(String[] args) {

​

        GFG obj = new GFG();

        int V = 5;

        List<List<Integer>> edges = new ArrayList<>();

        edges.add(new ArrayList<>(List.of(1, 3)));

        edges.add(new ArrayList<>(List.of(1, 4)));

        edges.add(new ArrayList<>(List.of(2, 1)));

        edges.add(new ArrayList<>(List.of(3, 2)));

        edges.add(new ArrayList<>(List.of(4, 5)));

        List<List<Integer>> ans = obj.findSCC(V, edges);

        System.out.println("Strongly Connected Components are:");

        for (List<Integer> x : ans) {

            for (int y : x) {

                System.out.print(y + " ");

            }

            System.out.println();

        }

    }

}

​

// This code is contributed by shivamgupta310570

import java.util.ArrayList;

import java.util.List;

​

class GFG {

​

    // dfs Function to reach destination

    boolean dfs(int curr, int des, List<List<Integer>> adj,

                List<Integer> vis) {

​

        // If curr node is destination return true

        if (curr == des) {

            return true;

        }

        vis.set(curr, 1);

        for (int x : adj.get(curr)) {

            if (vis.get(x) == 0) {

                if (dfs(x, des, adj, vis)) {

                    return true;

                }

            }

        }

        return false;

    }

​

    // To tell whether there is path from source to

    // destination

    boolean isPath(int src, int des, List<List<Integer>> adj) {

        List<Integer> vis = new ArrayList<>(adj.size() + 1);

        for (int i = 0; i <= adj.size(); i++) {

            vis.add(0);

        }

        return dfs(src, des, adj, vis);

    }

​

    // Function to return all the strongly connected

    // component of a graph.

    List<List<Integer>> findSCC(int n, List<List<Integer>> a) {

        // Stores all the strongly connected components.

        List<List<Integer>> ans = new ArrayList<>();

​

        // Stores whether a vertex is a part of any Strongly

        // Connected Component

        List<Integer> is_scc = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {

            is_scc.add(0);

        }

​

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {

            adj.add(new ArrayList<>());

        }

​

        for (List<Integer> edge : a) {

            adj.get(edge.get(0)).add(edge.get(1));

        }

​

        // Traversing all the vertices

        for (int i = 1; i <= n; i++) {

​

            if (is_scc.get(i) == 0) {

​

                // If a vertex i is not a part of any SCC

                // insert it into a new SCC list and check

                // for other vertices whether they can be

                // the part of this list.

                List<Integer> scc = new ArrayList<>();

                scc.add(i);

​

                for (int j = i + 1; j <= n; j++) {

​

                    // If there is a path from vertex i to

                    // vertex j and vice versa, put vertex j

                    // into the current SCC list.

                    if (is_scc.get(j) == 0 && isPath(i, j, adj)

                            && isPath(j, i, adj)) {

                        is_scc.set(j, 1);

                        scc.add(j);

                    }

                }

​

                // Insert the SCC containing vertex i into

                // the final list.

                ans.add(scc);

            }

        }

        return ans;

    }

}

​

public class Main {

​

    public static void main(String[] args) {

​

        GFG obj = new GFG();

        int V = 5;

        List<List<Integer>> edges = new ArrayList<>();

        edges.add(new ArrayList<>(List.of(1, 3)));

        edges.add(new ArrayList<>(List.of(1, 4)));

        edges.add(new ArrayList<>(List.of(2, 1)));

        edges.add(new ArrayList<>(List.of(3, 2)));

        edges.add(new ArrayList<>(List.of(4, 5)));

        List<List<Integer>> ans = obj.findSCC(V, edges);

        System.out.println("Strongly Connected Components are:");

        for (List<Integer> x : ans) {

            for (int y : x) {

                System.out.print(y + " ");

            }

            System.out.println();

        }

    }

}

​

// This code is contributed by shivamgupta310570

import java.util.ArrayList;

import java.util.List;

​

class GFG {

​

    // dfs Function to reach destination

    boolean dfs(int curr, int des, List<List<Integer>> adj,

                List<Integer> vis) {

​

        // If curr node is destination return true

        if (curr == des) {

            return true;

        }

        vis.set(curr, 1);

        for (int x : adj.get(curr)) {

            if (vis.get(x) == 0) {

                if (dfs(x, des, adj, vis)) {

                    return true;

                }

            }

        }

        return false;

    }

​

    // To tell whether there is path from source to

    // destination

    boolean isPath(int src, int des, List<List<Integer>> adj) {

        List<Integer> vis = new ArrayList<>(adj.size() + 1);

        for (int i = 0; i <= adj.size(); i++) {

            vis.add(0);

        }

        return dfs(src, des, adj, vis);

    }

​

    // Function to return all the strongly connected

    // component of a graph.

    List<List<Integer>> findSCC(int n, List<List<Integer>> a) {

        // Stores all the strongly connected components.

        List<List<Integer>> ans = new ArrayList<>();

​

        // Stores whether a vertex is a part of any Strongly

        // Connected Component

        List<Integer> is_scc = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {

            is_scc.add(0);

        }

​

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {

            adj.add(new ArrayList<>());

        }

​

        for (List<Integer> edge : a) {

            adj.get(edge.get(0)).add(edge.get(1));

        }

​

        // Traversing all the vertices

        for (int i = 1; i <= n; i++) {

​

            if (is_scc.get(i) == 0) {

​

                // If a vertex i is not a part of any SCC

                // insert it into a new SCC list and check

                // for other vertices whether they can be

                // the part of this list.

                List<Integer> scc = new ArrayList<>();

                scc.add(i);

​

                for (int j = i + 1; j <= n; j++) {

​

                    // If there is a path from vertex i to

                    // vertex j and vice versa, put vertex j

                    // into the current SCC list.

                    if (is_scc.get(j) == 0 && isPath(i, j, adj)

                            && isPath(j, i, adj)) {

                        is_scc.set(j, 1);

                        scc.add(j);

                    }

                }

​

                // Insert the SCC containing vertex i into

                // the final list.

                ans.add(scc);

            }

        }

        return ans;

    }

}

​

public class Main {

​

    public static void main(String[] args) {

​

        GFG obj = new GFG();

        int V = 5;

        List<List<Integer>> edges = new ArrayList<>();

        edges.add(new ArrayList<>(List.of(1, 3)));

        edges.add(new ArrayList<>(List.of(1, 4)));

        edges.add(new ArrayList<>(List.of(2, 1)));

        edges.add(new ArrayList<>(List.of(3, 2)));

        edges.add(new ArrayList<>(List.of(4, 5)));

        List<List<Integer>> ans = obj.findSCC(V, edges);

        System.out.println("Strongly Connected Components are:");

        for (List<Integer> x : ans) {

            for (int y : x) {

                System.out.print(y + " ");

            }

            System.out.println();

        }

    }

}

​

// This code is contributed by shivamgupta310570

1. Kosaraju's Algorithm:

Kosaraju's Algorithm involves two main phases:

    Performing Depth-First Search (DFS) on the Original Graph:
        We first do a DFS on the original graph and record the finish times of nodes (i.e., the time at which the DFS finishes exploring a node completely).
    Performing DFS on the Transposed Graph:
        We then reverse the direction of all edges in the graph to create the transposed graph.
        Next, we perform a DFS on the transposed graph, considering nodes in decreasing order of their finish times recorded in the first phase.
        Each DFS traversal in this phase will give us one SCC.

Here’s a simplified version of Kosaraju’s Algorithm:

    DFS on Original Graph: Record finish times.
    Transpose the Graph: Reverse all edges.
    DFS on Transposed Graph: Process nodes in order of decreasing finish times to find SCCs.
 */
public class KosarajuSCC {
	
    // Function to find number of strongly connected components in the graph.
    ///// to get source in stack
    public void dfs(ArrayList<ArrayList<Integer>> adj, int u, boolean[] visited, Stack<Integer>st){
        visited[u] = true;
        for(int v : adj.get(u)){
            if(!visited[v]){
                dfs(adj,v,visited,st);
            }
        }
        st.push(u);
        
    }
    public ArrayList<ArrayList<Integer>> revGraph(int n, ArrayList<ArrayList<Integer>> adj){
        ArrayList<ArrayList<Integer>> rev = new ArrayList<>();
        for(int i = 0 ; i < n ; i++)
            rev.add(new ArrayList<>());
        for(int u = 0 ; u < n ;u++){
            for(int v : adj.get(u)){
                rev.get(v).add(u);
            }
        }
        return rev;
    }
    public ArrayList<Integer> exploreComponents(ArrayList<ArrayList<Integer>> rev,int src, boolean[] explored){
        ArrayList<Integer> component = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        explored[src]= true;
        while(!q.isEmpty()){
            int u = q.poll();
            component.add(u);
            for(int v : rev.get(u)){
                if(!explored[v]){
                    q.offer(v);
                    explored[v] =true;
                }
            }
        }
        return component;
    }
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        boolean[] visited = new boolean[n];
        Stack<Integer>st = new Stack<>();
        for(int u = 0; u < n ;u++){
            if(!visited[u]){
                dfs(adj,u,visited,st);
            }
        }
        ArrayList<ArrayList<Integer>> rev = revGraph(n, adj);
        boolean[] explored = new boolean[n];
        int count = 0;
        while(!st.isEmpty()){
            int u = st.pop();
            if(!explored[u]){
                count++;
                exploreComponents(rev,u,explored);
            }
        }
        return count;
        
    }
    

}
