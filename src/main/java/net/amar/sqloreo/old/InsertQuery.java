package net.amar.sqloreo.old;

import java.util.*;

public class InsertQuery {

  private final Table table;
  private final Map<Column<?>, Object> values = new LinkedHashMap<>();

  public InsertQuery(Table table) {
    this.table = table;
  }

  public <T> InsertQuery value(Column<T> column, T value) {
    values.put(column, value);
    return this;
  }

  public BuiltQuery build() {
    String cols = values.keySet().stream()
              .map(c -> c.getFullName().split("\\.")[1])
              .reduce((a, b) -> a + ", " + b)
              .orElse("");

    String placeholders = values.keySet().stream()
              .map(c -> "?")
              .reduce((a, b) -> a + ", " + b)
              .orElse("");

    String sql = "INSERT INTO " + table.getName()
                + " (" + cols + ") VALUES (" + placeholders + ")";

    List<Object> params = new ArrayList<>(values.values());

    return new BuiltQuery(sql, params);
  }
}
