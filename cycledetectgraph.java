import java.util.ArrayList;
import java.util.Stack;

public class cycledetectgraph{
    static class edge{
        int src;
        int dest;
        int weight;
        edge(int src, int dest, int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
    public static void createDirecGraph(ArrayList<edge> graph[]){
        for(int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<edge>();
        }
        graph[0].add(new edge(0,1,2));
        graph[0].add(new edge(0,4,3));

        graph[1].add(new edge(1,0, 0));
        graph[1].add(new edge(1,2, 0));
        graph[1].add(new edge(1,4,2));

        graph[2].add(new edge(2, 1,10));
        graph[2].add(new edge(2, 3,10));

        graph[3].add(new edge(3,2,5));

        graph[4].add(new edge(4,0,10));
        graph[4].add(new edge(4,1,2));
        graph[4].add(new edge(4,5,2));

        graph[5].add(new edge(5,4,7));
    }
    public static boolean cycliUndirected(ArrayList<edge> graph[], int curr, boolean vis[], int parent){
        vis[curr]=true;
        for(int i=0; i<graph[curr].size(); i++){
            edge e = graph[curr].get(i);
            if(vis[e.dest] && e.dest != parent){
                return true;
            }
            else if(!vis[e.dest]){
                boolean iscycle = cycliUndirected(graph, e.dest, vis, curr);
                if(iscycle) return true;
            }
            else{
                continue;
            }
        }
        return false;
    }

    public static boolean cycledetect(ArrayList<edge> graph[], int curr, boolean rec[], boolean vis[]){
        vis[curr] = true;
        rec[curr] = true;
        for(int i=0; i<graph[curr].size(); i++){
            edge e = graph[curr].get(i);
            if(rec[e.dest]){
                return true;
            }
            else if(!rec[e.dest]){
                if(cycledetect(graph, e.dest, rec, vis)){
                    return true;
                }
            }
        }
        rec[curr]=false;
        return false;
    }
// Topological sorting can be done only for directed acyclic graphs(DAG).

    public static void toposorting(ArrayList<edge> graph[], int curr, Stack<Integer> s, boolean vis[]){
        vis[curr] = true;
        for(int i=0; i<graph[curr].size(); i++){
            edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                toposorting(graph, e.dest, s, vis);
            }
        }
        s.push(curr);
    }
    public static void main(String args[]){
        int V = 6;
        ArrayList<edge> graph[] = new ArrayList[V];
        createDirecGraph(graph);
        // boolean vis[] = new boolean[V];
        // boolean rec[] = new boolean[V];
        // for(int i=0; i<V; i++){
        //     if(!vis[i]){
        //         boolean iscycle = cycle(graph, i, rec, vis); 
        //         if(iscycle){
        //             System.out.println(iscycle);
        //             break;
        //         }
        //     }
        // }
        // boolean vis2[] = new boolean[V];
        // Stack<Integer> s = new Stack<>();

        // System.out.println(cycle(graph, 0, new boolean[V], new boolean[V]));
        // toposorting(graph, 0, s, vis2);
        // for(int i=0; i<V; i++){
        //     if(!vis2[i]){
        //         toposorting(graph, i, s, vis2);
        //     }
        // }
        // while(!s.isEmpty()){
        //     System.out.print(s.peek()+" ");
        //     s.pop();
        // }
        System.out.println(cycliUndirected(graph,0,new boolean[V],-1));
    }
}