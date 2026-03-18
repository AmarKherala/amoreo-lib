package net.amar.sqloreo;

public class Table {
    private final String name;

    /*
    * Table objects represent DB tables
    * They also act as the entry point to building queries
    * (nothing gets executed by the Table object it only builds queries)
    */

    public Table(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public <T> Column<T> column(String name) {
        return new Column<>(this, name);
    }

    public SelectQuery select(Column<?>... columns) {
        return new SelectQuery(this, columns);
    }

    public InsertQuery insert() {
        return new InsertQuery(this);
    }

    public UpdateQuery update() {
        return new UpdateQuery(this);
    }

    public DeleteQuery delete() {
        return new DeleteQuery(this);
    }
}
