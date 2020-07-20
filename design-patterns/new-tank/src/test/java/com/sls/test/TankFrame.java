package com.sls.test;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 最基本的创建一个窗口
 */
public class TankFrame {

    public static void main(String[] args) {
        Frame frame = new Frame();
        // 设置长宽 像素
        frame.setSize(500,800);
        // 设置大小
        frame.setResizable(false);
        // 设置title
        frame.setTitle("tank");
        // 设置一个监听类
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}
