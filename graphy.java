import java.util.*;

class GraphNode {
    public String name;
    public int index;
    public boolean isVisited = false;

    ArrayList<GraphNode> neighbours = new ArrayList<GraphNode>();

    public GraphNode(String name, int index){
        this.index = index;
        this.name = name;
    }   
}

class MatrixGraph {
    ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();
    int [][]adjanceyMatrix;
    
    public MatrixGraph(ArrayList <GraphNode> nodeList){
        this.nodeList = nodeList;
        adjanceyMatrix = new int[nodeList.size()][nodeList.size()];
    }
    
    public void addUndirectedEdge(int i, int j){
        adjanceyMatrix[i][j] = 1;
        adjanceyMatrix[j][i] = 1;
    }
    
    //method to return neighbours
    public ArrayList<GraphNode> getNieghbours(GraphNode node){
        ArrayList<GraphNode> neighbours = new ArrayList<GraphNode>();
        int nodeIndex = node.index;

        for (int i = 0; i < adjanceyMatrix.length; i++) {
            if (adjanceyMatrix[nodeIndex][i] == 1) {
                neighbours.add(nodeList.get(i));
            }
        }
        
        return neighbours;
    }

    //internal fincton
    void bfsVisit(GraphNode node){
        LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
        queue.add(node);
        while (!queue.isEmpty()) {
            GraphNode current = queue.remove(0);
            current.isVisited = true;
            System.out.print(current.name + " ");
            ArrayList<GraphNode> neighbours = getNieghbours(current);

            for (GraphNode neighbour : neighbours) {
                if (neighbour.isVisited == false) {
                    queue.add(neighbour);
                    neighbour.isVisited = true;                    
                }
            }
        }
        System.out.println();
    }

    public void bfs(){
        for (GraphNode node : nodeList) {
            if (node.isVisited == false) {
                bfsVisit(node);
            }
        }
    }

    void dfsVisit(GraphNode node){
        Stack<GraphNode> stack = new Stack<GraphNode>();
        stack.push(node);
        while (!stack.isEmpty()) {
            GraphNode current = stack.pop();
            System.out.print(current.name + " ");
            current.isVisited = true;
            ArrayList<GraphNode> neighbours = getNieghbours(current);

            for (GraphNode neighbour : neighbours) {
                if (!neighbour.isVisited) {
                    stack.push(neighbour);
                    neighbour.isVisited = true;
                }
            }
        }
    }

    public void dfs(){
        for (GraphNode node : nodeList) {
            if (!node.isVisited) {
                dfsVisit(node);
            }
        }
    }

    public void addDirectedEdge(int i, int j){
        adjanceyMatrix[i][j] = 1;
    }

    void topoVisit(GraphNode node, Stack<GraphNode> stack){
        ArrayList<GraphNode> neighbours = getNieghbours(node);
        for (GraphNode neighbour : neighbours) {
            if (!neighbour.isVisited) {
                topoVisit(neighbour, stack);
            }
        }
        node.isVisited = true;
        stack.push(node);
    }

    public void topo(){
        Stack<GraphNode> stack = new Stack<GraphNode>();
        for (GraphNode node : nodeList) {
            if (!node.isVisited) {
                topoVisit(node, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop().name + " ");
        }
    }
}

class ListGraph{
    ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();

    public ListGraph(ArrayList<GraphNode> nodeList){
        this.nodeList = nodeList;
    }

    public void addUndirectedEdge(int i, int j){
        GraphNode one = nodeList.get(i);
        GraphNode two = nodeList.get(j);

        one.neighbours.add(two);
        one.neighbours.add(one);
    }

    void bfsVisit(GraphNode node){
        LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
        queue.add(node);
        
        while (!queue.isEmpty()) {
            GraphNode currentNode = queue.remove(0);
            System.out.println(currentNode.name + " ");
            currentNode.isVisited = true;

            for (GraphNode neighbour : currentNode.neighbours) {
                if (!neighbour.isVisited) {
                    queue.add(neighbour);
                    neighbour.isVisited = true;
                }
            }
        }
    }

    public void bfs(){
        for (GraphNode node : nodeList) {
            if (!node.isVisited) {
                bfsVisit(node);
            }
        }
    }

    void dfsVisit(GraphNode node){
        Stack<GraphNode> stack = new Stack<GraphNode>();
        stack.push(node);
        while (!stack.isEmpty()) {
            GraphNode current = stack.pop();
            System.out.print(current.name + " ");
            current.isVisited = true;

            for (GraphNode neighbour : current.neighbours) {
                if (!neighbour.isVisited) {
                    stack.push(neighbour);
                    neighbour.isVisited = true;
                }
            }
        }
    }

    public void dfs(){
        for (GraphNode node : nodeList) {
            if (!node.isVisited) {
                dfsVisit(node);
            }
        }
    }

    public void addDirectedEdge(int i, int j){
        GraphNode one = nodeList.get(i); 
        GraphNode two = nodeList.get(j);
        one.neighbours.add(two);
    }

    void topoVisit(GraphNode node, Stack<GraphNode> stack){
        for (GraphNode  neighbour : node.neighbours) {
            if (!neighbour.isVisited) {
                topoVisit(neighbour, stack);
            }
        }
        node.isVisited = true;
        stack.push(node);
    }

    public void topo(){
        Stack<GraphNode> stack = new Stack<GraphNode>();
        for (GraphNode node : nodeList) {
            if (!node.isVisited) {
                topoVisit(node, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop().name + " ");
        }
    }
}

 

public class graphy {
    public static void main(String[] args) {
        ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();
        nodeList.add(new GraphNode("A", 0));       
        nodeList.add(new GraphNode("B", 1));
        nodeList.add(new GraphNode("C", 2));
        nodeList.add(new GraphNode("D", 3));
        nodeList.add(new GraphNode("E", 4));     
        nodeList.add(new GraphNode("F", 5));
        nodeList.add(new GraphNode("G", 6));
        nodeList.add(new GraphNode("H", 7));

        MatrixGraph g = new MatrixGraph(nodeList);
        // g.addUndirectedEdge(0, 1);
        // g.addUndirectedEdge(0, 2);
        // g.addUndirectedEdge(0, 3);
        // g.addUndirectedEdge(1, 4);
        // g.addUndirectedEdge(2, 3);
        // g.addUndirectedEdge(3, 4);

        g.addDirectedEdge(0, 2);
        g.addDirectedEdge(2, 4);
        g.addDirectedEdge(4, 7);
        g.addDirectedEdge(4, 5);
        g.addDirectedEdge(5, 6);
        g.addDirectedEdge(1, 2);
        g.addDirectedEdge(1, 3);
        g.addDirectedEdge(3, 5);

        // g.bfs();
        // g.dfs();
        g.topo();

        ArrayList<GraphNode> nodeList1 = new ArrayList<GraphNode>();
        nodeList1.add(new GraphNode("A", 0));
        nodeList1.add(new GraphNode("B", 1));
        nodeList1.add(new GraphNode("C", 2));
        nodeList1.add(new GraphNode("D", 3));
        nodeList1.add(new GraphNode("E", 4));
        nodeList1.add(new GraphNode("F", 5));
        nodeList1.add(new GraphNode("G", 6));
        nodeList1.add(new GraphNode("H", 7));
        ListGraph g1 = new ListGraph(nodeList1);
        // g1.addUndirectedEdge(0, 1);
        // g1.addUndirectedEdge(0, 2);
        // g1.addUndirectedEdge(0, 3);
        // g1.addUndirectedEdge(1, 4);
        // g1.addUndirectedEdge(2, 3);
        // g1.addUndirectedEdge(3, 4);

        g1.addDirectedEdge(0, 2);
        g1.addDirectedEdge(2, 4);
        g1.addDirectedEdge(4, 7);
        g1.addDirectedEdge(4, 5);
        g1.addDirectedEdge(5, 6);
        g1.addDirectedEdge(1, 2);
        g1.addDirectedEdge(1, 3);
        g1.addDirectedEdge(3, 5);

        // g1.bfs();
        // g1.dfs();
        g1.topo();

    }
}
