package com.supermarket.GUI.components;

import java.awt.*;

public class Layout3 extends Layout1{
    public RoundedPanel leftContent;
    public RoundedPanel rightContent;
    public RoundedPanel topContent;
    public RoundedPanel bottomContent;
    public Layout3() {
        super();
        initComponents();
        setVisible(true);
    }

    public void initComponents(){
        super.initComponents();

        leftContent = new RoundedPanel();
        rightContent = new RoundedPanel();
        topContent = new RoundedPanel();
        bottomContent = new RoundedPanel();

        bottom.setBackground(new Color(0xFFFFFF));

        leftContent.setLayout(new BorderLayout());
        leftContent.setBackground(new Color(0xFFFFFF));
        leftContent.setPreferredSize(new Dimension(800, 710));
        bottom.add(leftContent, BorderLayout.WEST);

        rightContent.setLayout(new BorderLayout());
        rightContent.setBackground(new Color(0xA8A8AF));
        rightContent.setPreferredSize(new Dimension(350, 710));
        bottom.add(rightContent, BorderLayout.EAST);

        topContent.setLayout(new BorderLayout());
        topContent.setBackground(new Color(0xA8A8AF));
        topContent.setPreferredSize(new Dimension(800, 350));
        leftContent.add(topContent, BorderLayout.NORTH);

        bottomContent.setLayout(new BorderLayout());
        bottomContent.setBackground(new Color(0xA8A8AF));
        bottomContent.setPreferredSize(new Dimension(800, 350));
        leftContent.add(bottomContent, BorderLayout.SOUTH);

    }
}
