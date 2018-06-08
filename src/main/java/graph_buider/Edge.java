package graph_buider;

public class Edge {

  /**
   * Value for indicates the first {@link Vertex}.
   */
  private final Vertex start;
  /**
   * Value for indicates the second {@link Vertex}.
   */
  private final Vertex finish;
  /**
   * Value for storing the weight {@link Edge}.
   */
  private final int weight;

  /**
   * Constructor for creating new {@link Edge} between two vertex and save the weight.
   *
   * @param source first {@link Vertex}, destination second {@link Vertex}, weight set current value.
   */
  public Edge(Vertex source, Vertex destination, int weight) {
    this.start = source;
    this.finish = destination;
    this.weight = weight;
  }

  /**
   * @return first element {@link Vertex}
   */
  public Vertex getFinish() {
    return finish;
  }

  /**
   * @return second element {@link Vertex}
   */
  public Vertex getStart() {
    return start;
  }

  /**
   * @return value weight for {@link Edge}
   */
  public int getWeight() {
    return weight;
  }
}
