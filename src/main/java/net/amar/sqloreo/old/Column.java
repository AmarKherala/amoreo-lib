package net.amar.sqloreo.old;

public class Column<T> {

  private final Table table;
  private final String name;

  public Column(Table table, String name) {
    this.name = name;
    this.table = table;
  }
  
  public String getFullName() {
    return table.getName() + "." + name;
  }

  public Condition eq(T value) {
        return new Condition(getFullName() + " = ?", value);
  }

  public Condition gt(T value) {
        return new Condition(getFullName() + " > ?", value);
  }
}
