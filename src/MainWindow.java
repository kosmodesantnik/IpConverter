import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {
    JFrame frame;
    JTextArea input;
    JTextArea output;
    JButton convert;
    JButton clear;
    JButton someButton;
    JButton oneMore;
    JButton left;
    JButton right;
    JLabel info;
    File pic = null;
    JFileChooser fileChooser;
    public static int count = 0;

    public MainWindow() throws HeadlessException {
        frame = new JFrame("IP Converter");
        frame.setSize(new Dimension(650, 550));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        info = new JLabel();
        info.setBounds(10, 460, 400, 40);

        input = new JTextArea();
        input.setBounds(10, 10, 400, 400);
        input.setBackground(Color.WHITE);

        output = new JTextArea();
        output.setBounds(430, 10, 200, 400);
        output.setEditable(false);
        output.setBackground(Color.WHITE);

        convert = new JButton("convert");
        convert.setBounds(10, 420, 85, 30);
        convert.addActionListener(e -> {
            String[] strArr = input.getText().split("\n");
            for (int i = 0; i < strArr.length; i++) {
                String one = strArr[i];
                if(one.equals("")|| one.equals(null)) {
                    info.setText("Введи информацию!");
                    break;
                }
                char[] ch = one.toCharArray();
                for (int y = 0; y < 8; y += 2) {
                    try {
                        String num = String.valueOf(ch[y]) + String.valueOf(ch[y + 1]);
                        int result = Integer.parseInt(num, 16);
                        if (MainWindow.count < 3) {

                            output.setText(output.getText() + result + ".");
                            count++;
                        } else {
                            output.setText(output.getText() + result);
                        }
                    } catch (NumberFormatException ee) {
                        info.setText("Как из этого ты IP получишь?");
                    }
                }
                output.append("\n");
                count = 0;
            }
        });

        someButton = new JButton("Show");
        someButton.setBounds(115, 420, 85, 30);
        someButton.addActionListener(e1 -> {
            PictureDrawer b;
            try {
                if(pic == null) {
                    b = new PictureDrawer(pic = new PictureSwitcher().check());
                    pic = null;
                } else {
                    b = new PictureDrawer(pic);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        oneMore = new JButton("more");
        oneMore.setBounds(220, 420, 85, 30);
        oneMore.addActionListener(e ->{
            fileChooser = new JFileChooser();
//            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int ret = fileChooser.showOpenDialog(frame);
            if(ret == JFileChooser.APPROVE_OPTION){
                pic = fileChooser.getSelectedFile();

                System.out.println("Selected file is "+pic);
            }
        });

        clear = new JButton("clear");
        clear.setBounds(325, 420, 85, 30);
        clear.addActionListener(e -> clear());

        frame.add(input);
        frame.add(output);
        frame.add(convert);
        frame.add(clear);
        frame.add(someButton);
        frame.add(oneMore);
        frame.add(info);
        frame.setVisible(true);
    }

    public void clear(){
        input.setText("");
        output.setText("");
        info.setText("");
        MainWindow.count = 0;
    }
}
