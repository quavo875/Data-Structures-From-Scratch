import java.util.ArrayList;

public class bellmanford{
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

        graph[1].add(new edge(1,2,-4));

        graph[2].add(new edge(2,3,2));

        graph[3].add(new edge(3,4,4));

        graph[4].add(new edge(4,1,-1));
    }
    public static void bellman(ArrayList<edge> graph[], int src, int V){
        int dist[] = new int[V];
        for(int i=0; i<V; i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }    
        }
        for(int k=0; k<V-1; k++){
            for(int i=0; i<V; i++){
                for(int j=0; j<graph[i].size(); j++){
                    edge e = graph[i].get(j);
                    int u = e.src;
                    int v = e.dest;
                    if(dist[u] != Integer.MAX_VALUE && dist[u]+e.weight<dist[v]){
                        dist[v] = dist[u]+e.weight;
                    }
                }
            }
        }
        for(int i=0; i<V; i++){
            System.out.print(dist[i]+" ");
        }
    }
    public static void main(String args[]){
        int V = 5;
        ArrayList<edge> graph[] = new ArrayList[V];
        createGraph(graph);
        bellman(graph, 0, V);
    }
}