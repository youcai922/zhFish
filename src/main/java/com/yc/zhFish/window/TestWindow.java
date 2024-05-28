package com.yc.zhFish.window;

import com.yc.zhFish.config.AppSettingsState;
import com.yc.zhFish.entity.Content;
import com.yc.zhFish.service.MessageService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yucan
 * @date 2024/5/24 14:51
 */
public class TestWindow {
    private JButton nextButton;
    private JButton closeButton;
    private JPanel contentPanel;
    private JLabel titleJLabel;
    private JScrollPane contentScrollPane;
    private JTextArea contentTextArea1;
    private JTextField urlText;


    //用于计数
    int count = 0;
    int index = 0;
    //内容信息
    List<Content> contentList = new ArrayList<>();

    private void init() {
        // 激活自动换行功能
        contentTextArea1.setLineWrap(true);
        // 激活断行不断字功能
        contentTextArea1.setWrapStyleWord(true);
        // 设置为不可编辑
        contentTextArea1.setEditable(false);
        //设置字体大小
        Font newFont = new Font("Serif", Font.PLAIN, AppSettingsState.getInstance().fontSize); // 可以替换"Serif"为你想要的字体名称
        // 应用新的字体到JTextArea
        contentTextArea1.setFont(newFont);

        // 初始化contentPanel并设置其为可聚焦
        this.contentPanel.setFocusable(true);
        //设置键盘监听
        this.contentPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                    readNextContent();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public TestWindow(Project project, ToolWindow toolWindow) {
        this.init();

        /**
         * 下一个按钮点击事件
         */
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readNextContent();
            }
        });

        /**
         * 关闭按钮点击事件
         */
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindow.hide(null);
            }
        });
    }

    private void readNextContent() {
        //设置字体大小
        Font newFont = new Font("Serif", Font.PLAIN, AppSettingsState.getInstance().fontSize); // 可以替换"Serif"为你想要的字体名称
        // 应用新的字体到JTextArea
        contentTextArea1.setFont(newFont);
        if (index == count) {
            contentList = MessageService.getConteng();
            count = contentList.size() - 1;
            index = 0;
        }
        titleJLabel.setText(contentList.get(index).getTitle());
        urlText.setText(contentList.get(index).getUrl());
        contentTextArea1.setText(contentList.get(index).getContent());
        index++;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
