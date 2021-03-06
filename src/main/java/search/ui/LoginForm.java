package search.ui;

import error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import search.entity.User;
import search.service.impl.UserService;

import javax.swing.*;
import java.awt.*;

@Component
public class LoginForm extends AbstractForm {

    @Autowired
    private UserService userService;

    @Autowired
    private SearchForm searchForm;

    private User user;

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
            try {
                user = userService.getUser(tfLogin.getText(), tfPassword.getText());
                hide();
                searchForm.show();
            } catch (NotFoundException ex) {
                showError("Authorization error", "Invalid login or password");
            }
        });
    }

    @Override
    void hide() {
        super.hide();
        userService.detachForm();
    }

    public static void main(String[] args) {
        new LoginForm();
    }

    private boolean checkLogin() {
        return userService.isUserExists(tfLogin.getText(), tfPassword.getText());
    }

    public User getUser() {
        return user;
    }
}
