package org.piotranon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Sudoku sudoku = new Sudoku();
        int[][] grid = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        int[][] emptygrid = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        sudoku.setGrid(grid);
        sudoku.viewGrid();

        Point p = new Point(1, 1);
        Point p1 = new Point(2, 3);
        Point p2 = new Point(3, 3);

//        sudoku.validate(sudoku.getGrid(), p, 1);
        sudoku.solve(grid);
        sudoku.setGrid(grid);
        sudoku.viewGrid();

//        sudoku.solve(grid);
//        sudoku.solver2(grid);


//
//        sudoku.validateSubGrid(p);
//        sudoku.validateSubGrid(p1);
//        sudoku.validateSubGrid(p2);
//
//        System.out.println();
//
//        sudoku.validateVertical(p);
//        sudoku.validateVertical(p1);
//        sudoku.validateVertical(p2);
//
//        System.out.println();
//
//        sudoku.validateHorizontal(p);
//        sudoku.validateHorizontal(p1);
//        sudoku.validateHorizontal(p2);
//
//        System.out.println();
//
//        sudoku.whatCanFitSubGrid(p);
//        sudoku.whatCanFitSubGrid(p1);
//        sudoku.whatCanFitSubGrid(p2);
//
//        System.out.println();
//
//        sudoku.whatCanFitVertical(p);
//        sudoku.whatCanFitVertical(p1);
//        sudoku.whatCanFitVertical(p2);
//
//        System.out.println();
//
//        sudoku.whatCanFitHorizontal(p);
//        sudoku.whatCanFitHorizontal(p1);
//        sudoku.whatCanFitHorizontal(p2);
//
//        System.out.println();

//        sudoku.validate();

//        sudoku.viewGrid();

//        scene = new Scene(loadFXML("primary"));
//        stage.setScene(scene);
//        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}