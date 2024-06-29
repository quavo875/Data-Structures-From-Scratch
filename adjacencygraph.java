import java.util.* ;

public class adjacencygraph{
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
    public static void createGraph(ArrayList<edge> graph[]){
        for(int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<edge>();
        }
        graph[0].add(new edge(0,1,2));
        graph[0].add(new edge(0,2,2));

        graph[1].add(new edge(1,3,10));
        graph[1].add(new edge(1, 0,0));

        graph[2].add(new edge(2, 0,2));
        graph[2].add(new edge(2, 4,10));

        graph[3].add(new edge(3,5,-1));
        graph[3].add(new edge(3, 1,0));
        graph[3].add(new edge(3, 4,-1));

        graph[4].add(new edge(4,2,-1));
        graph[4].add(new edge(4,3,0));
        graph[4].add(new edge(4,5,-1));

        graph[5].add(new edge(5,3,-1));
        graph[5].add(new edge(5,4,0));
        graph[5].add(new edge(5,6,-1));

        graph[6].add(new edge(6,5,8));
    }
    public static void bfs(ArrayList<edge> graph[], int v){
        Queue<Integer> q = new LinkedList<>();
        boolean vis[] = new boolean[v];
        q.add(0);
        while(!q.isEmpty()){
            int curr = q.remove();
            if(vis[curr]==false){
                System.out.println(curr+" ");
                vis[curr] = true;
                for(int i=0; i<graph[curr].size(); i++){
                    edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
    }
    public static void dfs(ArrayList<edge> graph[], int v){
        Stack<Integer> s = new Stack<>();
        boolean[] vis = new boolean[v];
        s.push(0);
        while(!s.isEmpty()){
            int curr = s.peek();
            if(vis[curr]==false){
                System.out.println(curr+" ");
                vis[curr] = true;
            }
            else{
                for(int i=0; i<graph[curr].size();i++){
                    edge e = graph[curr].get(i);
                    if(vis[e.dest]==false){
                        s.push(e.dest);
                        break;
                    }
                    else if(i==graph[curr].size()-1 && vis[e.dest]==true){
                        s.pop();
                    }
                }
            }
        } 
    }
    public static void recursivedfs(ArrayList<edge> graph[], int curr, boolean[] vis){
        System.out.println(curr + " ");
        vis[curr]=true;
        for(int i=0; i<graph[curr].size(); i++){
            edge e = graph[curr].get(i);
            if(vis[e.dest]==false){
                recursivedfs(graph, e.dest, vis);
            }
        }
    }
    public static void srctotar(ArrayList<edge> graph[], int curr, int tar, String path, boolean[] vis){
        if(curr == tar){
            System.out.println(path);
        }
        else{
            for(int i=0; i<graph[curr].size(); i++){
                edge e = graph[curr].get(i);
                if(!vis[e.dest]){
                    vis[curr]=true;
                    srctotar(graph, e.dest, tar, path+e.dest, vis);
                    vis[curr] = false;
                }
            }
        }
    }
    public static void main(String args[]){
        int vertex = 7;
        ArrayList<edge> graph[] = new ArrayList[vertex];
        createGraph(graph);
        // print 2's neighbours
        for(int i=0; i<graph[2].size(); i++){
            System.out.println(graph[2].get(i).dest+","+graph[2].get(i).weight);
        }
        boolean[] vis = new boolean[vertex];
        bfs(graph,vertex);
        dfs(graph,vertex);
        recursivedfs(graph,0,vis);
        srctotar(graph, 0, 5, "0", new boolean[vertex]);
    }
}
