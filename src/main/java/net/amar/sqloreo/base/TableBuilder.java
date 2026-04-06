package net.amar.sqloreo.base;

import net.amar.sqloreo.base.annotations.*;

import java.lang.reflect.Field;

public class TableBuilder {

    private static String buildCreateStmt(Class<?> table) {

        StringBuilder sb = new StringBuilder();
        Table t = table.getAnnotation(Table.class);
        sb.append("CREATE TABLE IF NOT EXISTS %s (".formatted(t.name()));

        for (Field f : table.getDeclaredFields()) {
            validateField(f);
            sb.append(f.getName()).append(" ");

            if (f.isAnnotationPresent(IntField.class))
                sb.append("INTEGER");
            if (f.isAnnotationPresent(StringField.class))
                sb.append("TEXT");
            if (f.isAnnotationPresent(BooleanField.class))
                sb.append("INTEGER").append(" CHECK (")
                        .append(f.getName())
                        .append(" IN (0, 1))");
            if (f.isAnnotationPresent(NonNull.class))
                sb.append(" NOT NULL");
            if (f.isAnnotationPresent(Primary.class)) {
                sb.append(" PRIMARY KEY");
                if (f.getAnnotation(Primary.class).autoIncrement()) {
                    sb.append(" AUTOINCREMENT");
                }
            }
            sb.append(",");
        }

        sb.deleteCharAt(sb.length()-1);
        if (t.showTime())
            sb.append(", time_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP").append(");");
        else sb.append(")");
        return sb.toString();
    }

    private static void validateField(Field field) {
        boolean intField = field.isAnnotationPresent(IntField.class);
        boolean stringField = field.isAnnotationPresent(StringField.class);
        boolean booleanField = field.isAnnotationPresent(BooleanField.class);

        int count = 0;
        if (intField) count++;
        if (stringField) count++;
        if (booleanField) count++;

        if (count != 1) {
            throw new IllegalStateException("Field '" + field.getName() + "' must have exactly ONE type annotation");
        }
    }
}
