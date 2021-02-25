package ru.ssau.tk.kaf.kudrinandfirsov.ui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModelX extends AbstractTableModel {
    private static final long serialVersionUID = -2085652328948175353L;
    private static final int INDEX_COLUMN_NUMBER = 0;
    private static final int VALUE_X_COLUMN_NUMBER = 1;
    private final ArrayList<String> strings;

    public TableModelX(ArrayList<String> strings) {
        this.strings = strings;
    }

    @Override
    public int getRowCount() {
        return strings.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
                return "Index";
            case VALUE_X_COLUMN_NUMBER:
                return "XValues";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
                return rowIndex;
            case VALUE_X_COLUMN_NUMBER:
                return strings.get(rowIndex);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        if (columnIndex == VALUE_X_COLUMN_NUMBER) {
            strings.set(rowIndex, String.valueOf(o));
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
                return false;
            case VALUE_X_COLUMN_NUMBER:
                return true;
        }
        return false;
    }
}