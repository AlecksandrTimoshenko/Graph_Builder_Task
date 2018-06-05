package graph_buider;

public class Edge {

  private final Vertex start;
  private final Vertex finish;
  private final int weight;

  public Edge(String laneId, Vertex source, Vertex destination, int weight) {
    this.start = source;
    this.finish = destination;
    this.weight = weight;
  }

  public Vertex getFinish() {
    return finish;
  }

  public Vertex getStart() {
    return start;
  }

  public int getWeight() {
    return weight;
  }
}
