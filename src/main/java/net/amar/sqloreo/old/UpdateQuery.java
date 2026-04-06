package net.amar.sqloreo.old;

import java.util.*;

public class UpdateQuery {
    private final Table table;
    private final Map<Column<?>, Object> updates = new LinkedHashMap<>();
    private Condition condition;

    public UpdateQuery(Table table) {
        this.table = table;
    }

    public <T> UpdateQuery set(Column<T> column, T value) {
        updates.put(column, value);
        return this;
    }

    public UpdateQuery where(Condition condition) {
        this.condition = condition;
        return this;
    }

    public BuiltQuery build() {
        String setClause = updates.keySet().stream()
                .map(c -> c.getFullName().split("\\.")[1] + " = ?")
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        String sql = "UPDATE " + table.getName() + " SET " + setClause;

        List<Object> params = new ArrayList<>(updates.values());

        if (condition != null) {
            sql += " WHERE " + condition.getSql();
            params.addAll(condition.getParams());
        }

        return new BuiltQuery(sql, params);
    }
}