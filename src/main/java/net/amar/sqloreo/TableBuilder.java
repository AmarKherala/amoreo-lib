package net.amar.sqloreo;

public class TableBuilder {

  private final String name;

  public TableBuilder(String name) {
    this.name = name;
  }

  public static TableBuilder create(String name) {
    return new TableBuilder(name);
  }

  public Table build() {
    return new Table(name);
  }
}
