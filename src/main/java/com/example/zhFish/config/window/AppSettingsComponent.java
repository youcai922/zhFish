package com.example.zhFish.config.window;

import com.example.zhFish.config.AppSettingsState;

import javax.swing.*;
import java.util.Objects;

public class AppSettingsComponent extends JDialog {

    private JComboBox comboBox1;
    private JPanel contentPane;
    private JLabel contentLabel;

    public AppSettingsComponent() {
        this.comboBox1.setSelectedItem(AppSettingsState.getInstance().fontSize);
    }

    public Integer getFontSize() {
        return Integer.valueOf(Objects.requireNonNull(comboBox1.getSelectedItem()).toString());
    }

    public void setFontSize(Integer fontSize) {
        this.comboBox1.setSelectedItem(fontSize);
    }


    public JComboBox getPreferredFocusedComponent() {
        return comboBox1;
    }

    public JPanel getPanel() {
        //这里需要传递一个类型相同的值，1≠“1”
        this.comboBox1.setSelectedItem(AppSettingsState.getInstance().fontSize.toString());
        return contentPane;
    }
}