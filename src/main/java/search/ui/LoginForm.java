package search.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import search.dao.UserDAO;
import search.entity.Userr;

import javax.swing.*;
import java.awt.*;

@Component
public class LoginForm {

    @Autowired
    private UserDAO userService;
    private JButton button1;
    private JPanel panel;

    public LoginForm() {

        JFrame frame = new JFrame();
        frame.setContentPane(panel);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        button1.addActionListener(e -> sa());
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.sa();
    }

    private void sa() {
        Userr usr = new Userr();
        usr.setLogin("Paul");
        usr.setPassword("qwerty");
        userService.save(usr);
    }
}
