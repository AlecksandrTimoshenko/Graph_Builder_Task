package graph_buider;

public class Vertex {

  /** Value - {@value}, for storing the id Vertex.*/
  final private int id;
  /** Value - {@value}, for storing the name of Vertex.*/
  final private String name;

  /**
   * Constructor.
   * @param id for create new {@link Vertex}
   * @param name for create new {@link Vertex}
   * */
  public Vertex(int id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * @return id value for {@link Vertex}
   */
  public int getId() {
    return id;
  }
  /** @return name value for {@link Vertex} */
  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == 0) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Vertex other = (Vertex) obj;
    if (id == 0) {
      if (other.id != 0) {
        return false;
      }
    } else if (id != (other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return name;
  }


}
