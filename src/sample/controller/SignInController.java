package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.UserDaoHibernate;
import sample.animation.Shake;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    void initialize() {
        UserDaoHibernate hibernate = new UserDaoHibernate();
        OpenNextWindow window = new OpenNextWindow();

        authSignInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPass = passField.getText().trim();

            if (!(loginText.equals("")) && !(loginPass.equals(""))) {
                boolean flag = hibernate.checkIn(loginText, loginPass);
                if (flag) {
                    authSignInButton.getScene().getWindow().hide();
                    window.openWindow("/sample/fxml/MainPage.fxml");
                } else {
                    animation();
                }
            } else animation();
        });

        loginSignUpButton.setOnAction(event -> {
            loginSignUpButton.getScene().getWindow().hide();
            window.openWindow("/sample/fxml/signUp.fxml");
        });
    }

    public void animation() {
        Shake userLoginAnim = new Shake(loginField);
        Shake userPassAnim = new Shake(passField);
        userLoginAnim.playAnim();
        userPassAnim.playAnim();
    }
}

