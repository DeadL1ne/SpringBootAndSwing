package search.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import search.service.impl.UserService;

import javax.swing.*;
import java.awt.*;

@Component
public class LoginForm extends AbstractForm {

    @Autowired
    private UserService userService;

    @Autowired
    private SearchForm searchForm;

    private JButton btnLogIn;
    private JPanel panel;
    private JTextField tfLogin;
    private JTextField tfPassword;
    private JLabel lLogin;
    private JLabel lPassword;

    public LoginForm() {
        final int WIDTH_FORM = 400;
        final int HEIGHT_FORM = 300;
        final String TITLE_FORM = "Authorization";
        frame = new JFrame(TITLE_FORM);

        frame.setContentPane(panel);
        frame.setPreferredSize(new Dimension(WIDTH_FORM, HEIGHT_FORM));
        frame.setSize(WIDTH_FORM, HEIGHT_FORM);
        setFrameToCenterScreen();
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnLogIn.addActionListener(e -> {
            if (checkLogin()) {
                hide();
                searchForm.show();
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
