package topic.graph;
/////https://leetcode.com/problems/critical-connections-in-a-network/description/
import java.util.ArrayList;
import java.util.List;

public class CriticalConnections {
    int[] discover;
    int[] lowest;
    boolean[] visited;
    int time = 1;

    public void dfs(List<Integer>[] adj, List<List<Integer>> res, int u, int par) {
        visited[u] = true;
        discover[u] = time;
        lowest[u] = time;
        time++;
        for (int v : adj[u]) {
            if (!visited[v]) {
                dfs(adj, res, v, u);
                lowest[u] = Math.min(lowest[u], lowest[v]);

                if (lowest[v] > discover[u]) {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(u);
                    temp.add(v);
                    res.add(temp);
                }
            } else if (v != par) {
                lowest[u] = Math.min(lowest[u], discover[v]);
            }

        }

    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<Integer>();
        for (List<Integer> e : connections) {
            adj[e.get(0)].add(e.get(1));
            adj[e.get(1)].add(e.get(0));
        }
        discover = new int[n];
        lowest = new int[n];
        visited = new boolean[n];
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(adj, res, i, -1);
            }
        }
        return res;
    }

}
