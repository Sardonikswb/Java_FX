package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.User;
import sample.UserDaoHibernate;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button signInButton;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpCountry;

    @FXML
    private RadioButton signUpBoxMan;

    @FXML
    private RadioButton signUpBoxWoman;

    @FXML
    private Button buttonCancel;

    @FXML
    void initialize() {
        OpenNextWindow window = new OpenNextWindow();

        signInButton.setOnAction(event -> {
            signUpNewUserHibernate();
            signInButton.getScene().getWindow().hide();
            window.openWindow("/sample/fxml/MainPage.fxml");
        });

        buttonCancel.setOnAction(event -> {
            buttonCancel.getScene().getWindow().hide();
            window.openWindow("/sample/fxml/SignIn.fxml");
        });
    }

    private void signUpNewUserHibernate() {
        UserDaoHibernate hibernate = new UserDaoHibernate();

        String firstName = signUpName.getText();
        String lastName = signUpLastName.getText();
        String userName = loginField.getText();
        String password = passField.getText();
        String location = signUpCountry.getText();
        String gender = "";

        if (signUpBoxMan.isSelected()) {
            gender = "мужской";
        } else {
            gender = "женский";
        }
        User user = new User(firstName, lastName, userName, password, location, gender);
        hibernate.save(user);
    }

}
