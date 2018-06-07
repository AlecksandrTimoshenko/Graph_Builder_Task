package graph_buider;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 * Looks for the shortest path between two vertices using the Dextra algorithm
 */

public class Dejkstra {

  private static final Logger logger = Logger.getLogger(Dejkstra.class);

  private final List<Vertex> nodes;
  private final List<Edge> edges;
  private Set<Vertex> settledNodes;
  private Set<Vertex> unSettledNodes;
  private Map<Vertex, Vertex> predecessors;
  private Map<Vertex, Integer> distance;

  public Dejkstra(Graph graph) {
    this.nodes = Lists.newArrayList(graph.getVertexes());
    this.edges = Lists.newArrayList(graph.getEdges());
  }

  /**
   * Starts execution of algorithm Dextra and set start vertex.
   */
  public void execute(Vertex source) {
    settledNodes = new HashSet<>();
    unSettledNodes = new HashSet<>();
    distance = new HashMap<>();
    predecessors = new HashMap<>();
    distance.put(source, 0);
    unSettledNodes.add(source);
    while (unSettledNodes.size() > 0) {
      Vertex node = getMinimum(unSettledNodes);
      settledNodes.add(node);
      unSettledNodes.remove(node);
      findMinimalDistances(node);
    }
  }

  /**
   * Looking for the Minimal Distances and put in too distance map.
   */
  private void findMinimalDistances(Vertex node) {
    List<Vertex> adjacentNodes = getNeighbors(node);
    for (Vertex target : adjacentNodes) {
      if (getShortestDistance(target) > getShortestDistance(node)
          + getDistance(node, target)) {
        distance.put(target, getShortestDistance(node)
            + getDistance(node, target));
        predecessors.put(target, node);
        unSettledNodes.add(target);
      }
    }
  }

  /**
   * @param {@link Edge} Vertex start, Vertex finish.
   * @return {@link Edge} weight.
   */
  private int getDistance(Vertex node, Vertex target) {
    for (Edge edge : edges) {
      if (edge.getStart().equals(node)
          && edge.getFinish().equals(target)) {
        return edge.getWeight();
      }
    }
    throw new RuntimeException("Should not happen");
  }

  /**
   * @param {@link Vertex}
   * @return a neighbors List of {@link Vertex} from graph.
   */
  private List<Vertex> getNeighbors(Vertex node) {
    List<Vertex> neighbors = Lists.newArrayList();
    for (Edge edge : edges) {
      if (edge.getStart().equals(node)
          && !isSettled(edge.getFinish())) {
        neighbors.add(edge.getFinish());
      }
    }
    return neighbors;
  }

  /**
   * @return {@link Vertex} minimum distance.
   */
  private Vertex getMinimum(Set<Vertex> vertexes) {
    Vertex minimum = null;
    for (Vertex vertex : vertexes) {
      if (minimum == null) {
        minimum = vertex;
      } else {
        if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
          minimum = vertex;
        }
      }
    }
    return minimum;
  }

  private boolean isSettled(Vertex vertex) {
    return settledNodes.contains(vertex);
  }

  /**
   * @param {@link Vertex} destination.
   * @return distance.
   */
  private int getShortestDistance(Vertex destination) {
    Integer d = distance.get(destination);
    if (d == null) {
      return Integer.MAX_VALUE;
    } else {
      return d;
    }
  }

  /**
   * @param target vertex.
   * @return shortest path.
   */
  public LinkedList<Vertex> getPath(Vertex target) {
    LinkedList<Vertex> path = new LinkedList<>();
    Vertex step = target;
    if (predecessors.get(step) == null) {
      return null;
    }
    path.add(step);
    while (predecessors.get(step) != null) {
      step = predecessors.get(step);
      path.add(step);
    }
    Collections.reverse(path);
    logger.info("Path " + path);
    return path;
  }

  /**
   * Looking for the Optimal path way between vertex.
   *
   * @param startVertexId first {@link Vertex} {@code id}.
   * @param finishVertexId second {@link Vertex} {@code id}.
   * @return shortest path.
   */
  public LinkedList<Vertex> getOptimalPath(int startVertexId, int finishVertexId) {
    execute(nodes.get(startVertexId));
    return getPath(nodes.get(finishVertexId));
  }
}
