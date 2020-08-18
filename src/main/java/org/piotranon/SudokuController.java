package org.piotranon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuController {
    @FXML
    private TextField input00, input10, input20, input30, input40, input50, input60, input70, input80, input01, input11, input21, input31, input41, input51, input61, input71, input81, input02, input12, input22, input32, input42, input52, input62, input72, input82, input03, input13, input23, input33, input43, input53, input63, input73, input83, input04, input14, input24, input34, input44, input54, input64, input74, input84, input05, input15, input25, input35, input45, input55, input65, input75, input85, input06, input16, input26, input36, input46, input56, input66, input76, input86, input07, input17, input27, input37, input47, input57, input67, input77, input87, input08, input18, input28, input38, input48, input58, input68, input78, input88;
    List<TextField> inputs = new ArrayList<>();

    private Logger log = LogManager.getLogger(Sudoku.class);

    @FXML
    void rules(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rules");
        alert.setHeaderText(null);
        alert.setContentText(
                "Each of the nine blocks (3x3 squares) has to contain all the numbers 1-9.\n" +
                        "Each vertical line has to contain all the numbers 1-9.\n" +
                        "Each horizontal line has to contain all the numbers 1-9.\n");

        alert.showAndWait();
    }

    @FXML
    void initialize() {
        Configurator.setLevel(log.getName(), Level.INFO);
        inputs.addAll(Arrays.asList(input00, input10, input20, input30, input40, input50, input60, input70, input80, input01, input11, input21, input31, input41, input51, input61, input71, input81, input02, input12, input22, input32, input42, input52, input62, input72, input82, input03, input13, input23, input33, input43, input53, input63, input73, input83, input04, input14, input24, input34, input44, input54, input64, input74, input84, input05, input15, input25, input35, input45, input55, input65, input75, input85, input06, input16, input26, input36, input46, input56, input66, input76, input86, input07, input17, input27, input37, input47, input57, input67, input77, input87, input08, input18, input28, input38, input48, input58, input68, input78, input88));
    }

    int getNum(TextField input) {
        int foo;
        try {
            foo = Integer.parseInt(input.getText());
            input.setStyle("-fx-font-weight: 900;");
        } catch (NumberFormatException e) {
            foo = 0;
            input.setStyle("-fx-text-fill: green;");
        }
        return foo;
    }


    @FXML
    void solve(ActionEvent event) {
        //conversion of input to grid int[][]

        int[][] grid = {
                {getNum(input00), getNum(input01), getNum(input02), getNum(input03), getNum(input04), getNum(input05), getNum(input06), getNum(input07), getNum(input08)},
                {getNum(input10), getNum(input11), getNum(input12), getNum(input13), getNum(input14), getNum(input15), getNum(input16), getNum(input17), getNum(input18)},
                {getNum(input20), getNum(input21), getNum(input22), getNum(input23), getNum(input24), getNum(input25), getNum(input26), getNum(input27), getNum(input28)},
                {getNum(input30), getNum(input31), getNum(input32), getNum(input33), getNum(input34), getNum(input35), getNum(input36), getNum(input37), getNum(input38)},
                {getNum(input40), getNum(input41), getNum(input42), getNum(input43), getNum(input44), getNum(input45), getNum(input46), getNum(input47), getNum(input48)},
                {getNum(input50), getNum(input51), getNum(input52), getNum(input53), getNum(input54), getNum(input55), getNum(input56), getNum(input57), getNum(input58)},
                {getNum(input60), getNum(input61), getNum(input62), getNum(input63), getNum(input64), getNum(input65), getNum(input66), getNum(input67), getNum(input68)},
                {getNum(input70), getNum(input71), getNum(input72), getNum(input73), getNum(input74), getNum(input75), getNum(input76), getNum(input77), getNum(input78)},
                {getNum(input80), getNum(input81), getNum(input82), getNum(input83), getNum(input84), getNum(input85), getNum(input86), getNum(input87), getNum(input88)}
        };
        Sudoku sudoku = new Sudoku();
        if (validateInputNumber(grid)) {
            if (sudoku.validateIfTherIsNoInputMistakes(grid)) {

                input08.setStyle("-fx-text-fill: green;");

                sudoku.solve(grid);
                sudoku.viewGrid(grid);
                displayGrid(grid);
            } else {
                //invalid row/column/subgrid
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Input,Error");
                alert.setContentText("One of the rows/columns/subrid contains invalid values.");
                alert.showAndWait();
            }
        } else {
            // inputed value not correct
            //invalid input number not in sudoku avaible numbers (0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
            // 0 as a blank
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Input,Error");
            alert.setContentText("Inputed value does not restrict sudoku rules.");
            alert.showAndWait();
        }

    }

    List<Integer> allNumbers = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

    boolean validateInputNumber(int[][] grid) {
        for (int y = 0; y < 9; y++)
            for (int x = 0; x < 9; x++) {
                if (!allNumbers.contains(grid[y][x])) {
                    log.info(new Point(x, y).toString() + " contains invalid value = " + grid[y][x]);
                    return false;
                }
            }
        return true;
    }

    @FXML
    void clear(ActionEvent event) {
        log.info("clearing inputs");
        System.out.println(inputs.get(0));
        for (TextField input : inputs) {
            input.setText("");
            input.setStyle("");
        }
        System.out.println(inputs.get(0));
    }

    void displayGrid(int[][] grid) {
        //conversion from grid to input
        //first row
        input00.setText(String.valueOf(grid[0][0]));
        input01.setText(String.valueOf(grid[0][1]));
        input02.setText(String.valueOf(grid[0][2]));
        input03.setText(String.valueOf(grid[0][3]));
        input04.setText(String.valueOf(grid[0][4]));
        input05.setText(String.valueOf(grid[0][5]));
        input06.setText(String.valueOf(grid[0][6]));
        input07.setText(String.valueOf(grid[0][7]));
        input08.setText(String.valueOf(grid[0][8]));
        //second row
        input10.setText(String.valueOf(grid[1][0]));
        input11.setText(String.valueOf(grid[1][1]));
        input12.setText(String.valueOf(grid[1][2]));
        input13.setText(String.valueOf(grid[1][3]));
        input14.setText(String.valueOf(grid[1][4]));
        input15.setText(String.valueOf(grid[1][5]));
        input16.setText(String.valueOf(grid[1][6]));
        input17.setText(String.valueOf(grid[1][7]));
        input18.setText(String.valueOf(grid[1][8]));
        //third row
        input20.setText(String.valueOf(grid[2][0]));
        input21.setText(String.valueOf(grid[2][1]));
        input22.setText(String.valueOf(grid[2][2]));
        input23.setText(String.valueOf(grid[2][3]));
        input24.setText(String.valueOf(grid[2][4]));
        input25.setText(String.valueOf(grid[2][5]));
        input26.setText(String.valueOf(grid[2][6]));
        input27.setText(String.valueOf(grid[2][7]));
        input28.setText(String.valueOf(grid[2][8]));
        //fourth row
        input30.setText(String.valueOf(grid[3][0]));
        input31.setText(String.valueOf(grid[3][1]));
        input32.setText(String.valueOf(grid[3][2]));
        input33.setText(String.valueOf(grid[3][3]));
        input34.setText(String.valueOf(grid[3][4]));
        input35.setText(String.valueOf(grid[3][5]));
        input36.setText(String.valueOf(grid[3][6]));
        input37.setText(String.valueOf(grid[3][7]));
        input38.setText(String.valueOf(grid[3][8]));
        //fifth row
        input40.setText(String.valueOf(grid[4][0]));
        input41.setText(String.valueOf(grid[4][1]));
        input42.setText(String.valueOf(grid[4][2]));
        input43.setText(String.valueOf(grid[4][3]));
        input44.setText(String.valueOf(grid[4][4]));
        input45.setText(String.valueOf(grid[4][5]));
        input46.setText(String.valueOf(grid[4][6]));
        input47.setText(String.valueOf(grid[4][7]));
        input48.setText(String.valueOf(grid[4][8]));
        //six row
        input50.setText(String.valueOf(grid[5][0]));
        input51.setText(String.valueOf(grid[5][1]));
        input52.setText(String.valueOf(grid[5][2]));
        input53.setText(String.valueOf(grid[5][3]));
        input54.setText(String.valueOf(grid[5][4]));
        input55.setText(String.valueOf(grid[5][5]));
        input56.setText(String.valueOf(grid[5][6]));
        input57.setText(String.valueOf(grid[5][7]));
        input58.setText(String.valueOf(grid[5][8]));
        //seven row
        input60.setText(String.valueOf(grid[6][0]));
        input61.setText(String.valueOf(grid[6][1]));
        input62.setText(String.valueOf(grid[6][2]));
        input63.setText(String.valueOf(grid[6][3]));
        input64.setText(String.valueOf(grid[6][4]));
        input65.setText(String.valueOf(grid[6][5]));
        input66.setText(String.valueOf(grid[6][6]));
        input67.setText(String.valueOf(grid[6][7]));
        input68.setText(String.valueOf(grid[6][8]));
        //eight row
        input70.setText(String.valueOf(grid[7][0]));
        input71.setText(String.valueOf(grid[7][1]));
        input72.setText(String.valueOf(grid[7][2]));
        input73.setText(String.valueOf(grid[7][3]));
        input74.setText(String.valueOf(grid[7][4]));
        input75.setText(String.valueOf(grid[7][5]));
        input76.setText(String.valueOf(grid[7][6]));
        input77.setText(String.valueOf(grid[7][7]));
        input78.setText(String.valueOf(grid[7][8]));
        //nine row
        input80.setText(String.valueOf(grid[8][0]));
        input81.setText(String.valueOf(grid[8][1]));
        input82.setText(String.valueOf(grid[8][2]));
        input83.setText(String.valueOf(grid[8][3]));
        input84.setText(String.valueOf(grid[8][4]));
        input85.setText(String.valueOf(grid[8][5]));
        input86.setText(String.valueOf(grid[8][6]));
        input87.setText(String.valueOf(grid[8][7]));
        input88.setText(String.valueOf(grid[8][8]));
    }

}

