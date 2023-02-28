package com.example.client;

import com.example.client.Entity.BookEntity;
import com.example.client.controller.ApplicationController;
import com.example.client.controller.EditBookController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {launch();}

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ApplicationController.class.getResource("main.fxml"));
        AnchorPane mainApp = (AnchorPane) loader.load();
        Scene scene = new Scene(mainApp, 800, 600 );
        stage.setTitle("LibraryApp");
        stage.setScene(scene);

        ApplicationController controller = loader.getController();
        stage.show();
    }


    public static boolean showPersonEditDialog(BookEntity bookObj, int id){
        try {
            //Загрузка fxml создание сцены для всплывающего окна
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("editBook.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            //Создание окна Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование книги");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            //Передаём адресата в контроллер
            EditBookController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(bookObj, id);
            //Отображаем диалоговое окно и ждем, пока пользователь его не закроет
            dialogStage.showAndWait();
            return controller.isOkClicked();
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
