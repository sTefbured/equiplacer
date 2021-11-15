package com.kotikov.equiplacer.jbinarytreepanel.node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Node<T extends Number> extends JComponent {
    private static final Color BACKGROUND_COLOR = Color.YELLOW;
    private static final Color BOUND_COLOR = Color.BLACK;
    private static final BasicStroke BOUND_STROKE = new BasicStroke(2);
    private static final Font VALUE_FONT = new Font(Font.MONOSPACED, Font.BOLD, 14);

    private final Ellipse2D.Double shape;
    private final int diameter;

    private T value;

    public Node(T value, int diameter, Color backgroundColor) {
        super();
        this.value = value;
        this.diameter = diameter;
        setBackground(backgroundColor);
        setSize(diameter, diameter);
        shape = new Ellipse2D.Double(0, 0, diameter, diameter);
    }

    public Node(T value, int diameter) {
        this(value, diameter, BACKGROUND_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(getBackground());
        graphics2D.fill(shape);
        graphics2D.setColor(BOUND_COLOR);
        graphics2D.setStroke(BOUND_STROKE);
        graphics2D.draw(shape);

        graphics2D.setFont(VALUE_FONT);
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        String valueStr = String.valueOf(value);
        int textWidth = fontMetrics.stringWidth(valueStr);
        int textHeight = fontMetrics.getHeight();
        graphics2D.drawString(valueStr, diameter / 2 - textWidth / 2, diameter / 2 + textHeight / 4);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
