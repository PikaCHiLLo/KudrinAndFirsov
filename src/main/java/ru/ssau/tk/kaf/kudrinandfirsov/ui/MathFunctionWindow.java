package ru.ssau.tk.kaf.kudrinandfirsov.ui;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.*;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;

public class MathFunctionWindow extends JDialog {
    private String functionStr;
    private final Map<String, MathFunction> map = new HashMap<>();
    private MathFunction function;

    public MathFunctionWindow(TabulatedFunctionFactory factory) {
        JDialog dialog = new JDialog();
        setModal(true);
        setSize(new Dimension(400, 220));
        setLocationRelativeTo(null);
        JLabel labelHelperConst = new JLabel("Если вы хотите создать константную ф-ю, введите константу!");
        JLabel labelConst = new JLabel("Константа:");
        JTextField fieldConst = new JTextField();
        JButton buttonConst = new JButton("Добавить константу");
        buttonConst.addActionListener(e -> {
            try {
                int p = Integer.parseInt(fieldConst.getText());
                WindowSize windowSize = new WindowSize();
                windowSize.setVisible(true);
                TableMath tableMath = new TableMath(factory, new ConstantFunction(Double.parseDouble(fieldConst.getText())), windowSize.getSizeOf());
                tableMath.setVisible(true);
                dispose();
            }
            catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(dialog, "Введите корректную константу");
            }
        });
        JLabel labelCompositeHelper = new JLabel("Для создания математической или композитной функции");
        JButton compositeButton = new JButton("Сложная функция");
        compositeButton.addActionListener(e -> {
            CompositeParts composite = new CompositeParts(factory);
            composite.setVisible(true);
            dispose();
        });
        map.put("Единичная ф-я", new UnitFunction());
        map.put("Квадратная ф-я", new SqrFunction());
        map.put("Нулевая ф-я", new ZeroFunction());
        map.put("Тождественная ф-я", new IdentityFunction());
        JComboBox<String> box = new JComboBox<>(new String[]{"", "Двойная ф-я", "Единичная ф-я", "Квадратная ф-я", "Нулевая ф-я", "Тождественная ф-я"
        });
        box.setEditable(true);
        box.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    functionStr = e.getItem().toString();
                    System.out.println(functionStr);
                    function = map.get(functionStr);
                }
        });
        JButton create = new JButton("Создать");
        create.addActionListener(e -> {
            WindowSize windowSize = new WindowSize();
            windowSize.setVisible(true);
            TableMath tableMath = new TableMath(factory, function, windowSize.getSizeOf());
            tableMath.setVisible(true);
            dispose();
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup().addComponent(labelHelperConst))
                .addGroup(layout.createSequentialGroup().addComponent(labelConst).addComponent(fieldConst).addComponent(buttonConst))
                .addGroup(layout.createParallelGroup().addComponent(labelCompositeHelper).addComponent(compositeButton))
                .addGroup(layout.createSequentialGroup().addComponent(box).addComponent(create)));
        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createSequentialGroup().addComponent(labelHelperConst))
                .addGroup(layout.createParallelGroup().addComponent(labelConst).addComponent(fieldConst).addComponent(buttonConst))
                .addGroup(layout.createSequentialGroup().addComponent(labelCompositeHelper).addComponent(compositeButton))
                .addGroup(layout.createSequentialGroup().addComponent(box).addComponent(create)));

    }
}