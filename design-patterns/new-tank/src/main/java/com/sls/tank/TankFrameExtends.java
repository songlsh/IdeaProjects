package com.sls.tank;

import com.sls.tank.bean.GameModel;
import com.sls.tank.bean.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrameExtends extends Frame {

    GameModel gm = new GameModel();

    private static final int PAINT_WIDTH = 1080;
    private static final int PAINT_HEIGHT = 600;

    // 构造对象 设置弹出框的大小设置
    public TankFrameExtends() throws HeadlessException {
        //设置大小
        this.setSize(PAINT_WIDTH, PAINT_HEIGHT);

        // 设置拖动
        this.setResizable(true);

        // 设置标题
        this.setTitle("TankFrameExtends");

        //设置可见
        this.setVisible(true);
        // 设置键盘启动事件
        this.addKeyListener(new MykeyClassListener());

        //添加的window的监听类
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    // 画板对象
    Image image = null;

    /**
     * 消除屏幕闪烁问题
     */
    @Override
    public void update(Graphics g) {

        if (null == image) {
            //在缓存中创建一个和现在屏幕一样打的画板
            image = this.createImage(PAINT_WIDTH, PAINT_HEIGHT);
        }

        // 在内存中创建一个画笔
        Graphics graphics = image.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.gray);
        graphics.fillRect(0, 0, PAINT_WIDTH, PAINT_HEIGHT);
        graphics.setColor(color);
        // 调用paint的方法填充画板
        paint(graphics);
        // 调用g显示到屏幕上
        g.drawImage(image, 0, 0, null);
    }

    /**
     * paint方法是在每次刷新新窗口的时候进行的
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
//        super.paint(g);
        gm.paint(g);
    }
    public static int getPaintWidth() {
        return PAINT_WIDTH;
    }

    public static int getPaintHeight() {
        return PAINT_HEIGHT;
    }


    /**
     * 监听键盘
     */
    private class MykeyClassListener extends KeyAdapter {
        private boolean KL;
        private boolean KU;
        private boolean KD;
        private boolean KR;

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    KL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    KR = true;
                    break;
                case KeyEvent.VK_UP:
                    KU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    KD = true;
                    break;
                default:
                    break;
            }
            setMainDir();
        }

        /**
         * 设置方向 根据坦克来的
         */
        private void setMainDir() {
            Tank tank = gm.getMainTank();
            if (!KL && !KU && !KR && !KD)
                tank.setMoving(false);
            else {
                tank.setMoving(true);
                if (KL) tank.setDir(Direction.LEFT);
                if (KU) tank.setDir(Direction.UP);
                if (KR) tank.setDir(Direction.RIGHT);
                if (KD) tank.setDir(Direction.DOWN);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    KL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    KR = false;
                    break;
                case KeyEvent.VK_UP:
                    KU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    KD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    // 设置键盘释放的时候触发的方法
                    gm.getMainTank().fire();
                    break;
                default:
                    break;
            }
            setMainDir();
        }

        public MykeyClassListener() {
            super();
        }
    }
}

