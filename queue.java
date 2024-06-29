import java.util.*;

public class queue{
    static class Queuestack{
        static Stack<Integer> s1 = new Stack<>();
        static Stack<Integer> s2 = new Stack<>();

        public static boolean isEmpty(){
            return s1 == s1.isEmpty() && s2 == s2.isEmpty();
        }
        public static void add(int data){
            if(s1.isEmpty()){
                s1.push(data);
            }
            while(!s1.isEmpty()){
                int top = s1.peek();
                s1.pop(top);
                s2.push(top);
            }
            while(!s2.isEmpty()){
                int top2 = s2.peek();
                s2.pop(top2);
                s1.push(top2);
            }    
        }
        public static int remove(){
            if(isEmpty()){
                System.out.println("empty queue");
                return -1;
            }
            return s1.pop();
        }
        public static int peek(){
            if(isEmpty()){
                System.out.println("empty queue");
                return -1;
            }
            return s1.peek();
        }    
    }
    public static void main(String args[]){
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        
        while(!q.isEmpty()){
            System.out.println(q.peek());
            q.remove();
        }
    }
}