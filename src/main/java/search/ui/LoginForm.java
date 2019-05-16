package search.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import search.dao.UserDAO;
import search.entity.User;
import search.service.impl.UserService;

import javax.swing.*;
import java.awt.*;

@Component
public class LoginForm {

    @Autowired
    private UserService userService;

    private JButton btnLogIn;
    private JPanel panel;
    private JTextField tfLogin;
    private JTextField tfPassword;
    private JLabel lLogin;
    private JLabel lPassword;

    public LoginForm() {

        JFrame frame = new JFrame("Authorization");
        frame.setContentPane(panel);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setLocation(new Point(800, 350));
        frame.pack();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnLogIn.addActionListener(e -> {
            if (checkLogin()) {
                //TODO go next window
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Invalid login or password",
                        "Authorization error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new LoginForm();
    }

    private boolean checkLogin() {
        return userService.isUserExists(tfLogin.getText(), tfPassword.getText());
    }
}
