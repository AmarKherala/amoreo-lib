package net.amar.sqloreo.base;

import java.lang.reflect.Field;

import net.amar.sqloreo.base.annotations.Primary;
import net.amar.sqloreo.base.annotations.Table;

public class TableInserter {

  public static String insert(Object table, String[] columnsArray) {
    Class<?> t = table.getClass();
    if (!t.isAnnotationPresent(Table.class))
      throw new IllegalArgumentException("Can't find Table annotation on passed object, make sure your class is annotated with the net.amar.sqloreo.base.annotations.Table annotation");

    Table ta = t.getAnnotation(Table.class);
    StringBuilder columns = new StringBuilder();
    StringBuilder values = new StringBuilder();

    for (String s : columnsArray) {
      columns.append(s).append(",");
      values.append("?,");
    }

    columns.deleteCharAt(columns.length() - 1);
    values.deleteCharAt(values.length() - 1);

    return "INSERT INTO TABLE %s (%s) VALUES (%s);".formatted(ta.name(), columns.toString(), values.toString());
  }

  public static String insertAllFields(Object table) {
    Class<?> tableClass = table.getClass();

    if (!tableClass.isAnnotationPresent(Table.class))
      throw new IllegalArgumentException("Can't find Table annotation on passed object, make sure your class is annotated with the net.amar.sqloreo.base.annotations.Table annotation");

    Table tableAnnotation = tableClass.getAnnotation(Table.class);
     
    StringBuilder columns = new StringBuilder();
    StringBuilder values = new StringBuilder();

    for (Field field : tableClass.getDeclaredFields()) {

      if (field.isAnnotationPresent(Primary.class))
        continue;

      columns.append(field.getName()).append(",");
      values.append("?,");
    }

    columns.deleteCharAt(columns.length() - 1);
    values.deleteCharAt(columns.length() - 1);

    return "INSERT INTO TABLE %s (%s) VALUES (%s);".formatted(tableAnnotation.name(), columns.toString(), values.toString());
  }
}
