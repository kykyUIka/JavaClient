package com.example.client.controller;

import com.example.client.Application;
import com.example.client.Entity.BookEntity;
import com.example.client.utils.HTTPUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ApplicationController {
    public static String api = "http://localhost:28242/api/v1/book";
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();
    @FXML
    public TableView<BookEntity> tableBooks;
    @FXML
    public TableColumn<BookEntity, String> bookName;
    @FXML
    public TableColumn<BookEntity, String> bookAuthor;
    @FXML
    public TableColumn<BookEntity, String> bookPublisher;
    @FXML
    public TableColumn<BookEntity, String> bookYear;
    @FXML
    public TableColumn<BookEntity, String> bookChapter;

    @FXML
    private void initialize() throws Exception {
        getData();
        updateTable();
    }

    private void updateTable() throws IOException {
        bookName.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("author"));
        bookPublisher.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("publishing"));
        bookYear.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("year"));
        bookChapter.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("kind"));
        tableBooks.setItems(booksData);
    }

    @FXML
    private void click_newBook() throws IOException {
        BookEntity tempBook = new BookEntity();
        booksData.add(tempBook);
        Application.showPersonEditDialog(tempBook, booksData.size() - 1);
        addBook(tempBook);
    }

    @FXML
    private void click_removeBook() {
        BookEntity selectedPerson = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            booksData.remove(selectedPerson);
            //ApplicationController.saveDB(booksData);
        } else {
            //Ничего не выбрано
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner;
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбраный пользователь");
            alert.setContentText("Пожалуйста,выберете пользователя в таблице");
            alert.setContentText("Пожалуйста,выберете пользователя в таблицеа");
            alert.showAndWait();
        }
    }

    @FXML
    private void click_dublicateBook() throws IOException {
        BookEntity selectedPerson = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            addBook(selectedPerson);
            booksData.add(booksData.indexOf(selectedPerson) + 1, selectedPerson);
            //ApplicationController.saveDB(booksData);
        } else {
            //Ничего не выбрано
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner;
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбраный пользователь");
            alert.setContentText("Пожалуйста,выберете пользователя в таблице");
            alert.setContentText("Пожалуйста,выберете пользователя в таблицеа");
            alert.showAndWait();
        }
    }

    @FXML
    private void click_editBook() {
        BookEntity selectedPerson = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) Application.showPersonEditDialog(selectedPerson, booksData.indexOf(selectedPerson));
        else {
            //Ничего не выбрано
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner;
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбраный пользователь");
            alert.setContentText("Пожалуйста,выберете пользователя в таблице");
            alert.setContentText("Пожалуйста,выберете пользователя в таблицеа");
            alert.showAndWait();
        }
    }

    public static void getData() throws IOException {
        String res = String.valueOf(http.get(api, "all"));
        //Получение базового значения по каркасу BaseEntity
        JsonObject base = gson.fromJson(res, JsonObject.class);
        JsonObject dataArr = base.getAsJsonArray("data").getAsJsonObject();
        //Парсинг значений со второго уровня вложенности (data) в массив книг в таблице ObservableList
        for (int i = 0; i < dataArr.size(); i++) {
            BookEntity newBook = gson.fromJson(dataArr.get(String.valueOf(i)).toString(), BookEntity.class);
            booksData.add(newBook);
        }
    }


    @FXML
    public static void addBook(BookEntity book) throws IOException {
        System.out.println(book.toString());
        book.setId(null);
        http.post(api + "add", gson.toJson(book).toString());
    }

    public static void updateBook(BookEntity book) throws IOException {
        http.put(api + "update", gson.toJson(book).toString());
    }
}