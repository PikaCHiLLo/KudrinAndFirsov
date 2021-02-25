package ru.ssau.tk.kaf.kudrinandfirsov.ui;

import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.*;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableMath extends JDialog {
    private final ArrayList<String> stringsX;
    private TabulatedFunction functionTab;
    private int ex = 1;

    public TableMath(TabulatedFunctionFactory factory, MathFunction function, int size) {
        JDialog dialog = new JDialog();
        setModal(true);
        stringsX = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            stringsX.add("");
        }
        AbstractTableModel tableModelX = new TableModelX(stringsX);
        JTable tableX = new JTable(tableModelX);
        setSize(new Dimension(500, 500));
        tableX.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JButton create = new JButton("Создать");
        create.addActionListener(e -> {
            try {
                if (stringsX.contains("")) {
                    JOptionPane.showMessageDialog(dialog, "Введите корректно значения иксов");
                } else {
                    double[] xValues = new double[size];
                    double[] yValues = new double[size];
                    for (int i = 0; i < size; i++) {
                        xValues[i] = Double.parseDouble(stringsX.get(i));
                        yValues[i] = function.apply(xValues[i]);
                    }
                    functionTab = factory.create(xValues, yValues);
                    System.out.println(functionTab.toString());
                    dispose();
                }
            } catch (NumberFormatException | ArrayIsNotSortedException exception) {
                JOptionPane.showMessageDialog(dialog, "Иксы введены некорректно или неупорядоченно");
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPaneX = new JScrollPane(tableX);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addGroup(layout.createSequentialGroup().addComponent(tableScrollPaneX)).addGroup(layout.createSequentialGroup().addComponent(create)));

        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(tableScrollPaneX)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(create)));
        setLocationRelativeTo(null);
    }
}