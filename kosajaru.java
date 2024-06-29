import java.util.*;

public class kosajaru{
    static class edge implements Comparable<edge>{
        int src;
        int dest;
        int wgh;
        public edge(int src,int dest,int wgh){
            this.src = src;
            this.dest = dest;
            this.wgh = wgh;
        }
        @Override
        public int compareTo(edge e2){
            return this.wgh - e2.wgh;
        }
    }
    public static void createGraph(ArrayList<edge> graph[]){
        for(int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<graph.length; i++){
            graph[0].add(new edge(0,2,15));
            graph[0].add(new edge(0,3,15));

            graph[1].add(new edge(1,0,10));

            graph[2].add(new edge(2,1,15));

            graph[3].add(new edge(3,4,30));
        }
    }
    public static void topsort(ArrayList<edge> graph[], int curr, boolean vis[], Stack<Integer> s){
        vis[curr] = true;
        for(int i=0; i<graph[curr].size(); i++){
            edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                topsort(graph, e.dest, vis, s);
            }
        }
        s.push(curr);
    }
    public static void dfs(ArrayList<edge> graph[], int curr, boolean vis[]){
        vis[curr] = true;
        System.out.print(curr + " ");
        for(int i=0; i<graph[curr].size(); i++){
            edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                dfs(graph, e.dest, vis);
            }
        }

    }
    public static void kosaraju(ArrayList<edge> graph[], int V){
        Stack<Integer> s = new Stack<>();
        boolean vis[] = new boolean[V];
        for(int i=0; i<V; i++){
            if(!vis[i]){
                topsort(graph, i, vis, s);
            }
        }
        ArrayList<edge> transpose[] = new ArrayList[V];
        for(int i=0; i<graph.length; i++){
            vis[i] = false;
            transpose[i] = new ArrayList<>();
        }
        for(int i=0; i<V; i++){
            for(int j=0; j<graph[i].size(); j++){
                edge e = graph[i].get(j);
                transpose[e.dest].add(new edge(e.dest, e.src, e.wgh));
            }
        }
        while(!s.isEmpty()){
            int curr = s.pop();
            if(!vis[curr]){
                dfs(transpose, curr, vis);
                System.out.println();
            }
        }
    }
    public static void main(String args[]){
        int V = 5;
        ArrayList<edge> graph[] = new ArrayList[V];
        createGraph(graph);
        kosaraju(graph, V);
    }
}