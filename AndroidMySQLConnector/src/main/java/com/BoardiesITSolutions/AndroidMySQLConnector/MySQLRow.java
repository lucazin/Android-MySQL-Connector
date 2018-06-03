package com.BoardiesITSolutions.AndroidMySQLConnector;

import com.BoardiesITSolutions.AndroidMySQLConnector.Exceptions.SQLColumnNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MySQLRow
{
    private HashMap<ColumnDefinition, String> columnAndRowValue;

    public MySQLRow()
    {
        this.columnAndRowValue = new HashMap<>();
    }

    public void addRowValue(ColumnDefinition columnDefinition, String rowValue)
    {
        this.columnAndRowValue.put(columnDefinition, rowValue);
    }

    public String getString(String column) throws SQLColumnNotFoundException
    {
        Set set = this.columnAndRowValue.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
        {
            Map.Entry entry = (Map.Entry)iterator.next();
            ColumnDefinition col = (ColumnDefinition)entry.getKey();
            if (col.getColumnName().equals(column))
            {
                return (String)entry.getValue();
            }
        }
        throw new SQLColumnNotFoundException("'"+column+"' was not found in result set");
    }

    public int getInt(String column) throws SQLColumnNotFoundException
    {
        String value = this.getString(column);
        return Integer.parseInt(value);
    }
}