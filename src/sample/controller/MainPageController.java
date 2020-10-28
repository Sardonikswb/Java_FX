package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.User;
import sample.UserDaoHibernate;
import sample.animation.Shake;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> columnName;

    @FXML
    private TableColumn<User, String> columnLastName;

    @FXML
    private TableColumn<User, String> columnLogin;

    @FXML
    private TableColumn<User, String> columnCountry;

    @FXML
    private TableColumn<User, String> columnGender;

    @FXML
    private Button returnButtonMainPage;

    @FXML
    private Button deleteButton;

    @FXML
    void initialize() {
        fillTable();
        UserDaoHibernate hibernate = new UserDaoHibernate();
        OpenNextWindow window = new OpenNextWindow();

        returnButtonMainPage.setOnAction(event -> {
            returnButtonMainPage.getScene().getWindow().hide();
            window.openWindow("/sample/fxml/SignIn.fxml");

        });

        deleteButton.setOnAction(event -> {
            User user = tableView.getSelectionModel().getSelectedItem();
            if (user != null) {
                hibernate.delete(user);
                deleteButton.getScene().getWindow().hide();
                window.openWindow("/sample/fxml/MainPage.fxml");
            } else {
                Shake animButton = new Shake(deleteButton);
                animButton.playAnim();
            }
        });
    }

    void fillTable() {
        UserDaoHibernate hibernate = new UserDaoHibernate();
        List<User> users = hibernate.findAll();

        columnName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(
                (EventHandler<CellEditEvent<User, String>>) t -> {
                    ((User) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setFirstName(t.getNewValue());
                    hibernate.update(tableView.getSelectionModel().getSelectedItem());
                });

        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnLastName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnLastName.setOnEditCommit(
                (EventHandler<CellEditEvent<User, String>>) t -> {
                    ((User) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setLastName(t.getNewValue());
                    hibernate.update(tableView.getSelectionModel().getSelectedItem());
                });

        columnLogin.setCellValueFactory(new PropertyValueFactory<>("userName"));
        columnLogin.setCellFactory(TextFieldTableCell.forTableColumn());
        columnLogin.setOnEditCommit(
                (EventHandler<CellEditEvent<User, String>>) t -> {
                    ((User) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setUserName(t.getNewValue());
                    hibernate.update(tableView.getSelectionModel().getSelectedItem());
                });

        columnCountry.setCellValueFactory(new PropertyValueFactory<>("location"));
        columnCountry.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCountry.setOnEditCommit(
                (EventHandler<CellEditEvent<User, String>>) t -> {
                    ((User) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setLocation(t.getNewValue());
                    hibernate.update(tableView.getSelectionModel().getSelectedItem());
                });

        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        columnGender.setCellFactory(TextFieldTableCell.forTableColumn());
        columnGender.setOnEditCommit(
                (EventHandler<CellEditEvent<User, String>>) t -> {
                    ((User) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setGender(t.getNewValue());
                    hibernate.update(tableView.getSelectionModel().getSelectedItem());
                });

        ObservableList<User> list = FXCollections.observableArrayList(users);
        tableView.setItems(list);
        tableView.setEditable(true);
    }
}
