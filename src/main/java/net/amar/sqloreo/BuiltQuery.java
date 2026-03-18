package net.amar.sqloreo;

import java.util.List;

public class BuiltQuery {

  private final String sql;
  private final List<Object> params;

  public BuiltQuery(String sql, List<Object> params) {
    this.sql = sql;
    this.params = params;
  }

  public String getSql() {
    return sql;
  }

  public List<Object> getParams() {
    return params;
  }
}
