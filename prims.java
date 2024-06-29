import java.util.*;

public class prims{
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
            graph[0].add(new edge(0,1,10));
            graph[0].add(new edge(0,2,15));
            graph[0].add(new edge(0,3,30));

            graph[1].add(new edge(1,0,10));
            graph[1].add(new edge(1,3,20));

            graph[2].add(new edge(2,0,15));
            graph[2].add(new edge(2,3,50));

            graph[3].add(new edge(3,0,30));
            graph[3].add(new edge(3,1,40));
            graph[3].add(new edge(3,2,50));
        }
    }
    public static ArrayList<edge>[] MST(ArrayList<edge> graph[]){
        ArrayList<edge> graph2[] = new ArrayList<edge>[graph.length];
        for(int i=0; i<graph2.length; i++){
            graph2[i] = new ArrayList<>();
        }
        ArrayList<Integer> nonmst = new ArrayList<>();
        for(int i=0; i<graph.length; i++){
            nonmst.add(i);
        }
        ArrayList<Integer> mst = new ArrayList<>();
        while(nonmst.size()!=0){
            int x = nonmst.remove(0);
            mst.add(x);
            edge e = new edge(0,1,Integer.MAX_VALUE);
            for(int i=0; i<mst.size(); i++){
                int curr = mst.get(i);
                for(int j=0; j<graph[curr].size(); j++){
                    edge th = graph[curr].get(j);
                    for(int k=0; k<nonmst.size(); k++){
                        if(th.dest==nonmst.get(k)){
                            if(th.wgh<=e.wgh) {
                                e = th;
                            }
                            break;
                        }
                    }
                }
            }
            if(e.wgh != Integer.MAX_VALUE){ 
                graph2[e.src].add(e);
            }
        }
        return graph2;
    }
    // using priority queue
    public static ArrayList<edge>[] Prims(ArrayList<edge> graph[]){
        ArrayList<edge> graph2[] = new ArrayList<edge>[graph.length];
        for(int i=0; i<graph2.length; i++){
            graph2[i] = new ArrayList<>();
        }
        boolean inMST[] = new boolean[graph.length];
        PriorityQueue<edge> pq = new PriorityQueue<>();
        pq.addAll(graph[0]);
        inMST[0] = true;
        while(!pq.isEmpty()){
            edge minEdge = pq.poll();
            if(inMST[minEdge.dest]){
                continue;
            }
            else{
                inMST[minEdge.dest] = true;
                graph2[minEdge.src].add(minEdge);
                for(edge e : graph[minEdge.dest]){
                    if(!inMST[e.dest]){
                        pq.add(e);
                    }
                }
            }
        }
        return graph2;
    }
    public static void main(String args[]){
        ArrayList<edge> graph[] = new ArrayList<edge>[4];
        createGraph(graph);
        ArrayList<edge> graph2[] = new ArrayList<edge>[4];
        graph2 = Prims(graph);
        for(int i=0; i<4; i++){
            if(graph2[i].size()>0){
                for(int j=0; j<graph2[i].size(); j++){
                    edge e = graph2[i].get(j);
                    System.out.println(e.src+" "+e.dest+" "+e.wgh);
                }
            }
        }
    }
}