package net.amar.sqloreo.old;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Condition {

  private final String sql;
  private final List<Object> paarms;

  public Condition(String sql, Object... params) {
    this.sql = sql;
    this.paarms = new ArrayList<>(Arrays.asList(params));
  }

  public Condition and(Condition other) {
    String newSql = "(" + this.sql + " AND " + other.sql + ")";
    List<Object> params = new ArrayList<>(this.paarms);
    params.addAll(other.paarms);
    return new Condition(newSql, params.toArray());
  }

  public String getSql() {
    return sql;
  }

  public List<Object> getParams() {
    return paarms;
  }
}
