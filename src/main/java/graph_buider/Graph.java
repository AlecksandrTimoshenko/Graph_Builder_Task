package graph_buider;

import com.google.common.collect.Lists;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class Graph {

  private static final Logger logger = Logger.getLogger(Graph.class);

  /**
   * List of all {@link Vertex} in this graph.
   */
  private final List<Vertex> vertexes;
  /**
   * List of all {@link Vertex} in this graph.
   */
  private final List<Edge> edges;

  /**
   * @return List of all {@link Edge} this graph.
   */
  public List<Edge> getEdges() {
    return edges;
  }

  /**
   * @return List of all {@link Vertex} in this graph.
   */
  public List<Vertex> getVertexes() {
    return vertexes;
  }

  /**
   * A builder for constructing instances of {@link Graph}, initialized by the {@link Vertex} and {@link Edge}.
   */
  public static class Builder {

    private List<Vertex> vertexes = Lists.newArrayList();
    private List<Edge> edges = Lists.newArrayList();

    /**
     * @param vertex it's new {@link Vertex}
     */
    public Builder addVertex(Vertex vertex) {
      this.vertexes.add(vertex);
      return this;
    }

    /**
     * @param vertexes list inserts to graph.
     */
    public Builder addVertexes(List<Vertex> vertexes) {
      this.vertexes.addAll(vertexes);
      return this;
    }

    /**
     * Connects two {@link Vertex} and adds beam weight {@link Edge}
     *
     * @param sourceLocNo set params {@link Edge} {@code Vertex start},
     * destLocNo set params {@link Edge} {@code Vertex finish},
     * duration set params {@link Edge} {@code int waight}.
     * @throws IllegalArgumentException, if you try set negative value in {@code int weight} fild.
     */
    public Builder addEdge(int sourceLocNo, int destLocNo, int duration) {
      vertexCheckAvailability(sourceLocNo);
      vertexCheckAvailability(destLocNo);
      if (duration < 0) {
        logger.error("Negative value duration " + duration);
        throw new IllegalArgumentException("Duration cannot be negative");
      }
      Edge lane = new Edge(vertexes.get(sourceLocNo), vertexes.get(destLocNo), duration);
      edges.add(lane);
      logger.info(
          "Added lane between " + vertexes.get(sourceLocNo) + " and " + vertexes.get(destLocNo));
      return this;
    }

    /**
     * Checks - it's possible to add a {link Vertex} to List and add it
     */
    private void vertexCheckAvailability(int id) {
      while (vertexes.size() < id + 1) {
        vertexes.add(new Vertex(vertexes.size(), "Node_" + vertexes.size()));
        logger.info("Node added with name: Node_" + vertexes.size());
      }
    }

    /**
     * Creates a new undirected graph.
     *
     * @return the {@link Graph} connected graph.
     * @throws IllegalArgumentException when graph is not connected.
     */
    public Graph build() {
      if (!isConnectedGraph()) {
        logger.error("Graph is not connected");
        throw new IllegalArgumentException();
      }
      return new Graph(vertexes, edges);
    }

    /**
     * @return true, when all {@link Vertex} are connected by the {@link Edge}, or false if not.
     */
    public boolean isConnectedGraph() {
      Map<Vertex, Boolean> visited = new HashMap<>();
      ArrayDeque<Vertex> queue = new ArrayDeque<>();
      queue.add(vertexes.get(0));
      while (!queue.isEmpty()) {
        Vertex vertex = queue.pop();
        visited.put(vertex, true);
        for (Edge edge : edges) {
          if (edge.getStart().equals(vertex) && !visited.containsKey(edge.getFinish())) {
            queue.add(edge.getFinish());
          } else if (edge.getFinish().equals(vertex) && !visited.containsKey(edge.getStart())) {
            queue.add(edge.getStart());
          }
        }
      }
      if (visited.size() == vertexes.size()) {
        return true;
      }
      return false;
    }
  }

  private Graph(List<Vertex> vertexes, List<Edge> edges) {
    this.vertexes = Collections.unmodifiableList(vertexes);
    this.edges = Collections.unmodifiableList(edges);
  }
}