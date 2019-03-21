import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputFIODialog extends JFrame {
    public InputFIODialog(int width, int height, JTextField fieldToSetFIO) {
        setTitle("Диалог выбора фамилии...");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        MyWindow.setWindowPosToScreenCenter(320, 240, this);
        setLayout(null);

        int Y_OFFSET = 8;
        JTextField[] fields = {new JTextField("Фамилия"), new JTextField("Имя"), new JTextField("Отчество")};
        fields[0].setBounds(10, 10, 300, 24);
        add(fields[0]);

        for(int i = 1; i < fields.length; ++i){
            fields[i].setBounds(fields[i-1].getX(), fields[i-1].getY() + fields[i-1].getHeight() + Y_OFFSET,
                    fields[i-1].getWidth(), fields[i-1].getHeight());

            add(fields[i]);
        }

        JButton jbt1 = new JButton("OK");

        int i = fields.length - 1;
        jbt1.setBounds(fields[i].getX(), fields[i].getY() + fields[i].getHeight() + Y_OFFSET, 60, fields[i].getHeight());
        add(jbt1);

        jbt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fieldToSetFIO != null)
                    fieldToSetFIO.setText(fields[0].getText() + " " + fields[1].getText() + " " + fields[2].getText());

                setVisible(false);
            }
        });

        setAlwaysOnTop(true);
        setResizable(false);
    }
}
