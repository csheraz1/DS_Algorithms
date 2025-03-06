

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.SizeSequence;

public class MaxNewConnections {

            /*
             * Complete the 'maxNewConnections' function below.
             *
             * The function is expected to return a LONG_INTEGER.
             * The function accepts following parameters:
             *  1. UNWEIGHTED_INTEGER_GRAPH user
             *  2. INTEGER_ARRAY unverified
             */
        
            /*
             * For the unweighted graph, <name>:
             *
             * 1. The number of nodes is <name>Nodes.
             * 2. The number of edges is <name>Edges.
             * 3. An edge exists between <name>From[i] and <name>To[i].
             *
             */
        
            public static long maxNewConnections(int userNodes, List<Integer> userFrom, List<Integer> userTo, List<Integer> unverified) {
               long newConnections = 0;
               // Add all unverified users to a hashmap
               Map<Integer, Integer> unverifiedUsers = new HashMap<Integer, Integer>();
               for(int i = 0; i<unverified.size(); i++){
                    unverifiedUsers.put(unverified.get(i), i);
               }
               
                /** Create graph from provided input.
                   Each entry in List graph contains a subset of the graph that is
                   disconnected from the rest. **/
                List<List<edge>> graph = new ArrayList<List<edge>>();
                

System.out.println();
System.out.println();

                // Loop through each user edge between userFrom and userTo
                for(int i = 0; i<userFrom.size();i++){
                    // If empty add a list containing the first edge in userFrom and 
                    // userTo
                    if(graph.isEmpty()) {
                        List<edge> edgelist = new ArrayList<edge>();
                        edgelist.add(new edge(userFrom.get(i), userTo.get(i)));
                        graph.add(edgelist);
                        i++;
                    }
                    // Add edges to appropriate subset by edges in userfrom and userto
                    
                    for(int j = 0; j<graph.size(); j++){
                        boolean toAdd = true; 
                        boolean both = false; 
                        for(int k = 0; k<graph.get(j).size(); k++){
                            toAdd = true; 
                            //if both nodes are already present then dont add 
                            if((graph.get(j).get(k).node1 == userFrom.get(i) && 
                            graph.get(j).get(k).node2 == userTo.get(i)) ||
                            (graph.get(j).get(k).node2 == userFrom.get(i) &&
                            graph.get(j).get(k).node1 == userTo.get(i))){
                                both = true;
                                break;
                            }
                            // If none of the nodes are present in current graph 
                            // then do not add in current subgraph.
                            
                            if((graph.get(j).get(k).node1 != userFrom.get(i) && 
                            graph.get(j).get(k).node1 != userTo.get(i) &&
                            graph.get(j).get(k).node2 != userFrom.get(i) &&
                            graph.get(j).get(k).node2 != userTo.get(i))) {
                                toAdd = false;
                            }
                        }
                        if(both) 
                            continue;
                            
                        if(toAdd) {
                            graph.get(j).add(new edge(userFrom.get(i), userTo.get(i)));
                        } 
                        if(j == graph.size()-1 && !toAdd) {
                            List<edge> edgelist = new ArrayList<edge>();
                            edgelist.add(new edge(userFrom.get(i), userTo.get(i)));
                            graph.add(edgelist);
                            j++;
                            break;
                        }    
                    } 
                }
                System.out.println();

            for(int i =0; i<graph.size(); i++){
                for(int j=0; j<graph.get(i).size(); j++){
                    System.out.println(graph.get(i).get(j).node1);
                    System.out.println(graph.get(i).get(j).node2);

                }
            }
          
                // Next step is to loop through graph and check if connections can be added. 
                // increment newConnection each time an edge is added
                // go through each subset/subgraph. if connections can be added within then 
                // add them. 
                // Go through each subgraph and get number of nodes 
                // using helper function
                int totalCombinations = 0;
                for(int i =0; i<graph.size(); i++){
                    int nodes = getNumOfNodes(graph.get(i));
                    totalCombinations = 0;
                    for(int j=0; j<nodes; j++){
                        totalCombinations = totalCombinations+j;
                    }
                    newConnections = newConnections + totalCombinations - graph.get(i).size();
                    System.out.println("hello"+newConnections);
                }

              
                
                sort(graph, 0, graph.size() - 1);
                // Attempt merging between sub graphs if possible.
                
                for(int i = 0; i<graph.size(); i++){
                      for(int j = i+1; j<graph.size(); j++){ 
                        if(!(hasUnverified(graph.get(i), unverifiedUsers) && 
                            hasUnverified(graph.get(j), unverifiedUsers))){
                            graph.get(i).addAll(graph.get(j));
                            // Count number of connections
                            int nodes = getNumOfNodes(graph.get(i));
                            totalCombinations = 0;
                            for(int k=0; k<nodes; k++){
                                totalCombinations = totalCombinations+j;
                            }
                            newConnections = newConnections + totalCombinations - graph.get(i).size();
                            System.out.println(newConnections);

                        }
                      }  
                    }
                return newConnections; 
            }
            static boolean hasUnverified(List<edge> input, Map<Integer, Integer> map){
                for(int i = 0; i<input.size(); i++){
                    if(map.containsKey(input.get(i).node1) || map.containsKey(input.get(i).node2))
                    return true;
                }
                return false;
            }
            static int getNumOfNodes(List<edge> input){
                Map<Integer, Integer> nodes = new HashMap<Integer, Integer>();
                for(int i = 0; i<input.size(); i++){
                    if(!(nodes.containsKey(input.get(i).node1))) 
                        nodes.put(input.get(i).node1, i);
                    if(!(nodes.containsKey(input.get(i).node2))) 
                        nodes.put(input.get(i).node2, i);
                }
                return nodes.size();
            }
        static void merge(List<List<edge>> input, int l, int m, int r){
            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            // Create temp arrays
            List<List<edge>> left = new ArrayList<List<edge>>(n1);
            List<List<edge>> right = new ArrayList<List<edge>>(n2);

            // Copy data to temp arrays
            for (int i = 0; i < n1; ++i)
                left.add(input.get(l + i));
            for (int j = 0; j < n2; ++j)
                right.add(input.get(m + 1 + j));

            // Merge the temp arrays

            // Initial indices of first and second subarrays
            int i = 0, j = 0;

            // Initial index of merged subarray array
            int k = l;
            while (i < n1 && j < n2) {
                if (left.get(i).size() >= right.get(i).size()) {
                    input.set(k, left.get(i));
                    i++;
                }
                else {
                    input.set(k, right.get(j));
                    j++;
                }
                k++;
            }

            // Copy remaining elements of L[] if any
            while (i < n1) {
                input.set(k, left.get(i));
                i++;
                k++;
            }

            // Copy remaining elements of R[] if any
            while (j < n2) {
                input.set(k, right.get(j));
                j++;
                k++;
            }
        }

        // Main function that sorts arr[l..r] using
        // merge()
        static void sort(List<List<edge>> input, int l, int r)
        {
            
            if (l < r) {

                // Find the middle point
                int m = l + (r - l) / 2;

                // Sort first and second halves
                sort(input, l, m);
                sort(input, m + 1, r);

                // Merge the sorted halves
                merge(input, l, m, r);
            }
        }

        public static void main(String[] args){
            List<Integer> userFrom = new ArrayList<>();
            userFrom.add(1);
            userFrom.add(2);
            userFrom.add(3);
            userFrom.add(1);
            userFrom.add(5);
            userFrom.add(4);
            List<Integer> userTo = new ArrayList<>();
            userTo.add(2);
            userTo.add(1);
            userTo.add(1);
            userTo.add(3);
            userTo.add(7);
            userTo.add(5);
            List<Integer> unverified = new ArrayList<>();
            unverified.add(2);
            unverified.add(7);
            
            long ans = maxNewConnections(5, userFrom, userTo, unverified);
            System.out.println("new connection " + ans);
        }
            
        
}
        
// Capital first letter is better
class edge{
    // Better to have private attributes
    // and use getter/setter methods
    public int node1;
    public int node2;
    edge(int node1, int node2){
        this.node1 = node1;
        this.node2 = node2;
    }
}
    