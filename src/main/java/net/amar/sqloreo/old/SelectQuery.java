package net.amar.sqloreo.old;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelectQuery {

  private final Table table;
  private final List<Column<?>> columns;
  private boolean selectAll;
  private Condition condition;

  public SelectQuery(Table table, Column<?>... columns) {
    this.table = table;
    this.columns = new ArrayList<>(Arrays.asList(columns));
  }

  public SelectQuery where(Condition condition) {
    this.condition = condition;
    return this;
  }

  public void selectAll(boolean selectAll) {
    this.selectAll = selectAll;
  }

  public BuiltQuery build() {
    String cols = columns.stream()
            .map(Column::getFullName)
            .collect(Collectors.joining(", "));

    String sql = "";

    if (selectAll) 
      sql = "SELECT * FROM "+table.getName();
    else 
    sql = "SELECT " + cols + " FROM " + table.getName();

    List<Object> params = new ArrayList<>();
    if (condition != null) {
        sql += " WHERE " + condition.getSql();
        params.addAll(condition.getParams());
    }
    return new BuiltQuery(sql, params);
  }
}
