package com.company;

import java.awt.*;

public class MyLayout implements LayoutManager {

    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }

    @Override
    public void layoutContainer(Container parent) {
        for(int i = 0; i < parent.getComponentCount(); i++){
            Component component = parent.getComponent(i);
            switch (i){
                case 0:
                    component.setBounds(215, 0, 200, 40);
                    break;
                case 1:
                    component.setBounds(95, 40, 200, 40);
                    break;
                case 2:
                    component.setBounds(300, 40, 200, 40);
                    break;
                case 3:
                    component.setBounds(95, 90, 200, 40);
                    break;
                case 4:
                    component.setBounds(300, 90, 200, 40);
                    break;
                case 5:
                    component.setBounds(95, 140, 200, 40);
                    break;
                case 6:
                    component.setBounds(300, 140, 200, 40);
                    break;
                case 7:
                    component.setBounds(95, 190, 200, 40);
                    break;
                case 8:
                    component.setBounds(300, 190, 200, 40);
                    break;
                case 9:
                    component.setBounds(200, 240, 200, 40);
            }
        }
    }
}
