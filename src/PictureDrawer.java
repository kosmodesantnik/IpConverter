import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PictureDrawer extends JFrame {
    JFrame main;
    JLabel label;
    JLabel secLab;
    BufferedImage file;
    File mf;
    List<File> fileList = new ArrayList<>();

    public PictureDrawer(File f) throws HeadlessException, IOException {
        file = ImageIO.read(f);
        main = new JFrame();
        main.setSize(new Dimension(file.getWidth(), file.getHeight()));

        main.setDefaultCloseOperation(main.DISPOSE_ON_CLOSE);
        main.setLocationRelativeTo(null);


        label = new JLabel(new ImageIcon(file));

        main.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                secLab = new JLabel();
                if (e.getKeyCode() == 37 || e.getKeyCode() == 39) {
                    mf = new File(f.getParent());
                    File[] files = mf.listFiles();
                    Random r = new Random();
                    try {
                        label.setIcon(null);
                        BufferedImage bufIm = ImageIO.read(files[r.nextInt(files.length)]);
                        label.setIcon(new ImageIcon(bufIm));
                        label.setSize(bufIm.getWidth(), bufIm.getHeight());
                        main.setSize(bufIm.getWidth(), bufIm.getHeight());
                        System.out.println("Picture width " + bufIm.getWidth() + " label width " + label.getWidth() + " picture height " + bufIm.getHeight() + " label height " + secLab.getHeight());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        main.add(label);
        main.setVisible(true);
    }
}
