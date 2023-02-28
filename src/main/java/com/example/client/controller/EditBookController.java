package com.example.client.controller;


import com.example.client.Entity.BookEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.controller.ApplicationController.booksData;
import static com.example.client.controller.ApplicationController.updateBook;

//import static com.example.client.controller.ApplicationController.booksData;
//import static com.example.client.controller.ApplicationController.updateBook;
//import static com.example.client.controller.ApplicationController.updateBook;

public class EditBookController {
    @FXML
    private TextField bookName_filed;
    @FXML
    private TextField bookAuthor_filed;
    @FXML
    private TextField bookPublisher_filed;
    @FXML
    private TextField bookYear_filed;
    @FXML
    private TextField bookChapter_filed;

    private Stage editBookStage;
    private BookEntity book;
    private int bookID;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.editBookStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setLabels(BookEntity bookIn, int book_id) {
        this.book = bookIn;
        this.bookID = book_id;

        bookName_filed.setText(book.getTitle());
        bookAuthor_filed.setText(book.getAuthor());
        bookPublisher_filed.setText(book.getPublishing());
        bookYear_filed.setText(book.getYear());
        bookChapter_filed.setText(book.getYear());
    }

    @FXML
    private void handleOk() throws IOException {
        if (isInputValid()) {
            book.setTitle(bookName_filed.getText());
            book.setAuthor(bookAuthor_filed.getText());
            book.setPublishing(bookPublisher_filed.getText());
            book.setYear(bookYear_filed.getText());
            book.setKind(bookChapter_filed.getText());

            okClicked = true;
            editBookStage.close();
            booksData.set(bookID, book);
            updateBook(book);
        }
    }

    @FXML
    private void handleCancel() {
        editBookStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (bookName_filed.getText() == null || bookName_filed.getText().length() == 0)
            errorMessage += "Не обнаружено название книги!\n";
        if (bookAuthor_filed.getText() == null || bookAuthor_filed.getText().length() == 0)
            errorMessage += "Не обнаружен автор книги!\n";
        if (bookPublisher_filed.getText() == null || bookPublisher_filed.getText().length() == 0)
            errorMessage += "Не обнаружено издательство книги!\n";
        if (bookYear_filed.getText() == null || bookYear_filed.getText().length() == 0)
            errorMessage += "Не обнаружен год выпуска книги!\n";
        else {
            try {
                Integer.parseInt(bookYear_filed.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Не корректное значение года выпуска книги(Должно быть целочисленным)!\n";
            }
        }
        if (bookChapter_filed.getText()==null||bookChapter_filed.getText().length()==0) errorMessage+="Не обнаружен раздел книги!\n";
        //Сообщение об ошибке заполнения/изменения
        if (errorMessage.length()==0) return true;
        else {
            //Показываем сообщение об ошибке
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editBookStage);
            alert.setTitle("Ошибка заполнения!");
            alert.setHeaderText("Пожалуйста, укажите корректное значение текстовых полей");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }
}
