import java.util.*;

public class BreadthFirstSearchAlgorithm {

    public static void main(String[] args) {
		Tree<Integer> root = Tree.of(1);
        Tree<Integer> rootFirstChild = root.addChild(2);
        Tree<Integer> depthMostChild = rootFirstChild.addChild(3);
        Tree<Integer> rootSecondChild = root.addChild(4);

        System.out.println("For binary tree:");
        BreadthFirstSearchAlgorithm.search(3, root);

        Node<Integer> start = new Node<>(1);
        Node<Integer> firstNeighbor = new Node<>(2);
        start.connect(firstNeighbor);

        Node<Integer> firstNeighborNeighbor = new Node<>(3);
        firstNeighbor.connect(firstNeighborNeighbor);
        firstNeighborNeighbor.connect(start);

        Node<Integer> secondNeighbor = new Node<>(4);
        start.connect(secondNeighbor);

        System.out.println("For graph:");
        BreadthFirstSearchAlgorithm.search(4, firstNeighborNeighbor);
    }

    public static <T> Optional<Tree<T>> search(T value, Tree<T> root) {
        Queue<Tree<T>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Tree<T> currentNode = queue.remove();
            System.out.printf("Visited node with value: %d%n", currentNode.getValue());
            if (currentNode.getValue().equals(value)) {
                return Optional.of(currentNode);
            } else {
                queue.addAll(currentNode.getChildren());
            }
        }
        return Optional.empty();
    }

    public static <T> Optional<Node<T>> search(T value, Node<T> start) {
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(start);
        Node<T> currentNode;
        Set<Node<T>> alreadyVisited = new HashSet<>();
        while (!queue.isEmpty()) {
            currentNode = queue.remove();
            System.out.printf("Visited node with value: %d%n", currentNode.getValue());
            if (currentNode.getValue().equals(value)) {
                return Optional.of(currentNode);
            } else {
                alreadyVisited.add(currentNode);
                queue.addAll(currentNode.getNeighbors());
                queue.removeAll(alreadyVisited);
            }
        }
        return Optional.empty();
    }
}

class Tree<T> {

    private T value;
    private List<Tree<T>> children;

    public Tree(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public static <T> Tree<T> of(T value) {
        return new Tree<>(value);
    }

    public Tree<T> addChild(T value) {
        Tree<T> newChild = new Tree<>(value);
        children.add(newChild);
        return newChild;
    }

    public T getValue() {
        return value;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }
}

class Node<T> {

    private T value;
    private Set<Node<T>> neighbors;

    public Node(T value) {
        this.value = value;
        this.neighbors = new HashSet<>();
    }

    public void connect(Node<T> node) {
        if (this == node) {
            throw new IllegalArgumentException("Can't connect node to itself");
        }
        this.neighbors.add(node);
        node.neighbors.add(this);
    }

    public T getValue() {
        return value;
    }

    public Set<Node<T>> getNeighbors() {
        return neighbors;
    }
}