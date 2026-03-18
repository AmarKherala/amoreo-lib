package net.amar.sqloreo;

import java.util.*;

public class DeleteQuery {
    private final Table table;
    private Condition condition;

    public DeleteQuery(Table table) {
        this.table = table;
    }

    public DeleteQuery where(Condition condition) {
        this.condition = condition;
        return this;
    }

    public BuiltQuery build() {
        String sql = "DELETE FROM " + table.getName();
        List<Object> params = new ArrayList<>();

        if (condition != null) {
            sql += " WHERE " + condition.getSql();
            params.addAll(condition.getParams());
        }

        return new BuiltQuery(sql, params);
    }
}