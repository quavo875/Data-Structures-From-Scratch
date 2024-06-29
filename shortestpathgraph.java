import java.util.ArrayList;
import java.util.PriorityQueue;

public class shortestpathgraph{
    static class edge{
        int src;
        int dest;
        int weight;
        edge(int s, int d, int w){
            this.src = s;
            this.dest = d;
            this.weight = w;
        }
    }
    public static void createGraph(ArrayList<edge> graph[]){
        for(int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<edge>();
        }
        graph[0].add(new edge(0,1,2));
        graph[0].add(new edge(0,2,4));

        graph[1].add(new edge(1,2,1));
        graph[1].add(new edge(1,3,7));

        graph[2].add(new edge(2,4,3));

        graph[3].add(new edge(3,5,1));

        graph[4].add(new edge(4,3,3));
        graph[4].add(new edge(4,5,5));
    }

    public static class Pair implements Comparable<Pair>{
        int node;
        int dist;

        public Pair(int n, int d){
            this.node = n;
            this.dist = d;
        }

        @Override
        public int compareTo(Pair p2){
            return this.dist - p2.dist; // ascending
            // return p2.dist - this.dist (descending)
        }
    }
    public static void dijkstra(ArrayList<edge> graph[], int src, int V){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int dist[] = new int[V];
        for(int i=0; i<V; i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }
        boolean vis[] = new boolean[V];
        pq.add(new Pair(0, 0));
        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(!vis[curr.node]){
                vis[curr.node] = true;
                for(int i=0; i<graph[curr.node].size(); i++){
                    edge e = graph[curr.node].get(i);
                    int u = e.src;
                    int v = e.dest;
                    if(!vis[v] && dist[u] + e.weight < dist[v]){
                        dist[v] = dist[u] + e.weight;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }
        for(int i=0; i<V; i++){
            System.out.print(dist[i] + " ");
        }
    }
    public static void main(String args[]){
        int V = 6;
        ArrayList<edge> graph[] = new ArrayList[V];
        createGraph(graph);
        dijkstra(graph, 0, V);
    }
}