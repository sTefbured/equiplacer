package com.kotikov.equiplacer.client.desktop.view.panel.tabbedpane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabCloseButton extends JPanel {
    private static final Color HOVER_BACKGROUND_COLOR = new Color(255, 59, 59);
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(0, 0, 0, 0);
    private static final Color DEFAULT_CROSS_COLOR = Color.BLACK;
    private static final Color HOVER_CROSS_COLOR = Color.WHITE;

    private static final Dimension PREFERRED_SIZE = new Dimension(16, 16);
    private static final Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 10);
    private static final int Y_OFFSET = 3;
    private static final String CROSS_STR = "\u2573";

    private Color crossColor;

    public TabCloseButton(JTabbedPane tabbedPane, JComponent tabComponent) {
        setPreferredSize(PREFERRED_SIZE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(DEFAULT_BACKGROUND_COLOR);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tabbedPane.removeTabAt(tabbedPane.indexOfTabComponent(tabComponent));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(HOVER_BACKGROUND_COLOR);
                crossColor = HOVER_CROSS_COLOR;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(DEFAULT_BACKGROUND_COLOR);
                crossColor = DEFAULT_CROSS_COLOR;
                revalidate();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        var oldColor = g.getColor();
        g.setColor(crossColor);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(FONT);
        var clipBounds = g.getClipBounds();
        var strWidth = g.getFontMetrics().stringWidth(CROSS_STR);
        g.drawString(CROSS_STR, clipBounds.width / 2 - strWidth / 2, clipBounds.height / 2 + Y_OFFSET);
        g.setColor(oldColor);
    }
}
