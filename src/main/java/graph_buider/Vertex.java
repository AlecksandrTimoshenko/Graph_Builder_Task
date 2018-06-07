package graph_buider;

public class Vertex {

  final private int id;
  final private String name;


  public Vertex(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

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
