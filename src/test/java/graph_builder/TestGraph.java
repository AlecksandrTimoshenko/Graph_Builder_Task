package graph_builder;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import graph_buider.Dejkstra;
import graph_buider.Edge;
import graph_buider.Graph;
import graph_buider.Vertex;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class TestGraph {

  private List<Vertex> nodes;
  private List<Edge> edges;

  @Test
  public void testExcute() {
    nodes = new ArrayList<Vertex>();
    edges = new ArrayList<Edge>();

    for (int i = 0; i < 11; i++) {
      Vertex location = new Vertex("Node_" + i, "Node_" + i);
      nodes.add(location);
    }

    Graph graph = new Graph.Builder()
        .addEdge(0, 1, 85)
        .addEdge(0, 2, 217)
        .addEdge(0, 4, 173)
        .addEdge(2, 6, 186)
        .addEdge(2, 7, 103)
        .addEdge(3, 7, 183)
        .addEdge(5, 8, 250)
        .addEdge(8, 9, 84)
        .addEdge(7, 9, 167)
        .addEdge(4, 9, 502)
        .addEdge(9, 10, 40)
        .addEdge(1, 10, 600)
        .build();

    Dejkstra dijkstra = new Dejkstra(graph);
    dijkstra.execute(nodes.get(0));
    LinkedList<Vertex> path = dijkstra.getPath(nodes.get(10));

    assertNotNull(path);
    assertTrue(path.size() > 0);

    for (Vertex vertex : path) {
      System.out.println(vertex);
    }
  }
}
