import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingMain {
    public static void main(String[] args) {
        new MyWindow();
    }
}

class MyWindow extends JFrame {

    private static InputFIODialog fioDialog;

    public static void setWindowPosToScreenCenter(int width, int height, JFrame window){
        if(window == null)
            return;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int locationX = (screenSize.width - width) / 2;
        int locationY = (screenSize.height - height) / 2;

        window.setBounds(locationX, locationY, width, height);
    }

    public MyWindow() {
        setTitle("Форма выбора фамилии...");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setWindowPosToScreenCenter(800, 600, this);
        setLayout(null);

        JTextField jtfFIO = new JTextField("Фамилия, имя, отчество");
        jtfFIO.setBounds(10, 10, 300, 24);
        jtfFIO.setEditable(false);

        JButton jbt1 = new JButton("Задать...");
        jbt1.setBounds(jtfFIO.getX(),jtfFIO.getY() + jtfFIO.getHeight() + 8,85,30);

        add(jtfFIO);
        add(jbt1);

        fioDialog = new InputFIODialog(320, 240, jtfFIO);

        jbt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fioDialog.setVisible(true);
            }
        });

        setVisible(true);
    }
}
