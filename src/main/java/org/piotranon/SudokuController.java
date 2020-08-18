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
    }

    int getNum(String input) {
        int foo;
        try {
            foo = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            foo = 0;
        }
        return foo;
    }

    @FXML
    void solve(ActionEvent event) {
        //conversion of input to grid int[][]

        int[][] grid = {
                {getNum(input00.getText()), getNum(input01.getText()), getNum(input02.getText()), getNum(input03.getText()), getNum(input04.getText()), getNum(input05.getText()), getNum(input06.getText()), getNum(input07.getText()), getNum(input08.getText())},
                {getNum(input10.getText()), getNum(input11.getText()), getNum(input12.getText()), getNum(input13.getText()), getNum(input14.getText()), getNum(input15.getText()), getNum(input16.getText()), getNum(input17.getText()), getNum(input18.getText())},
                {getNum(input20.getText()), getNum(input21.getText()), getNum(input22.getText()), getNum(input23.getText()), getNum(input24.getText()), getNum(input25.getText()), getNum(input26.getText()), getNum(input27.getText()), getNum(input28.getText())},
                {getNum(input30.getText()), getNum(input31.getText()), getNum(input32.getText()), getNum(input33.getText()), getNum(input34.getText()), getNum(input35.getText()), getNum(input36.getText()), getNum(input37.getText()), getNum(input38.getText())},
                {getNum(input40.getText()), getNum(input41.getText()), getNum(input42.getText()), getNum(input43.getText()), getNum(input44.getText()), getNum(input45.getText()), getNum(input46.getText()), getNum(input47.getText()), getNum(input48.getText())},
                {getNum(input50.getText()), getNum(input51.getText()), getNum(input52.getText()), getNum(input53.getText()), getNum(input54.getText()), getNum(input55.getText()), getNum(input56.getText()), getNum(input57.getText()), getNum(input58.getText())},
                {getNum(input60.getText()), getNum(input61.getText()), getNum(input62.getText()), getNum(input63.getText()), getNum(input64.getText()), getNum(input65.getText()), getNum(input66.getText()), getNum(input67.getText()), getNum(input68.getText())},
                {getNum(input70.getText()), getNum(input71.getText()), getNum(input72.getText()), getNum(input73.getText()), getNum(input74.getText()), getNum(input75.getText()), getNum(input76.getText()), getNum(input77.getText()), getNum(input78.getText())},
                {getNum(input80.getText()), getNum(input81.getText()), getNum(input82.getText()), getNum(input83.getText()), getNum(input84.getText()), getNum(input85.getText()), getNum(input86.getText()), getNum(input87.getText()), getNum(input88.getText())}
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
        input00.setText("");
        input10.setText("");
        input20.setText("");
        input30.setText("");
        input40.setText("");
        input50.setText("");
        input60.setText("");
        input70.setText("");
        input80.setText("");
        input01.setText("");
        input11.setText("");
        input21.setText("");
        input31.setText("");
        input41.setText("");
        input51.setText("");
        input61.setText("");
        input71.setText("");
        input81.setText("");
        input02.setText("");
        input12.setText("");
        input22.setText("");
        input32.setText("");
        input42.setText("");
        input52.setText("");
        input62.setText("");
        input72.setText("");
        input82.setText("");
        input03.setText("");
        input13.setText("");
        input23.setText("");
        input33.setText("");
        input43.setText("");
        input53.setText("");
        input63.setText("");
        input73.setText("");
        input83.setText("");
        input04.setText("");
        input14.setText("");
        input24.setText("");
        input34.setText("");
        input44.setText("");
        input54.setText("");
        input64.setText("");
        input74.setText("");
        input84.setText("");
        input05.setText("");
        input15.setText("");
        input25.setText("");
        input35.setText("");
        input45.setText("");
        input55.setText("");
        input65.setText("");
        input75.setText("");
        input85.setText("");
        input06.setText("");
        input16.setText("");
        input26.setText("");
        input36.setText("");
        input46.setText("");
        input56.setText("");
        input66.setText("");
        input76.setText("");
        input86.setText("");
        input07.setText("");
        input17.setText("");
        input27.setText("");
        input37.setText("");
        input47.setText("");
        input57.setText("");
        input67.setText("");
        input77.setText("");
        input87.setText("");
        input08.setText("");
        input18.setText("");
        input28.setText("");
        input38.setText("");
        input48.setText("");
        input58.setText("");
        input68.setText("");
        input78.setText("");
        input88.setText("");
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

    @FXML
    private TextField input01;

    @FXML
    private TextField input02;

    @FXML
    private TextField input03;

    @FXML
    private TextField input08;

    @FXML
    private TextField input10;

    @FXML
    private TextField input11;

    @FXML
    private TextField input12;

    @FXML
    private TextField input20;

    @FXML
    private TextField input21;

    @FXML
    private TextField input22;

    @FXML
    private TextField input00;

    @FXML
    private TextField input05;

    @FXML
    private TextField input06;

    @FXML
    private TextField input13;

    @FXML
    private TextField input14;

    @FXML
    private TextField input15;

    @FXML
    private TextField input23;

    @FXML
    private TextField input24;

    @FXML
    private TextField input25;

    @FXML
    private TextField input04;

    @FXML
    private TextField input31;

    @FXML
    private TextField input32;

    @FXML
    private TextField input40;

    @FXML
    private TextField input41;

    @FXML
    private TextField input42;

    @FXML
    private TextField input50;

    @FXML
    private TextField input51;

    @FXML
    private TextField input52;

    @FXML
    private TextField input30;

    @FXML
    private TextField input34;

    @FXML
    private TextField input35;

    @FXML
    private TextField input43;

    @FXML
    private TextField input44;

    @FXML
    private TextField input45;

    @FXML
    private TextField input53;

    @FXML
    private TextField input54;

    @FXML
    private TextField input55;

    @FXML
    private TextField input33;

    @FXML
    private TextField input61;

    @FXML
    private TextField input62;

    @FXML
    private TextField input70;

    @FXML
    private TextField input71;

    @FXML
    private TextField input72;

    @FXML
    private TextField input80;

    @FXML
    private TextField input81;

    @FXML
    private TextField input82;

    @FXML
    private TextField input60;

    @FXML
    private TextField input64;

    @FXML
    private TextField input65;

    @FXML
    private TextField input73;

    @FXML
    private TextField input74;

    @FXML
    private TextField input75;

    @FXML
    private TextField input83;

    @FXML
    private TextField input84;

    @FXML
    private TextField input85;

    @FXML
    private TextField input63;

    @FXML
    private TextField input8;

    @FXML
    private TextField input9;

    @FXML
    private TextField input16;

    @FXML
    private TextField input17;

    @FXML
    private TextField input18;

    @FXML
    private TextField input26;

    @FXML
    private TextField input27;

    @FXML
    private TextField input28;

    @FXML
    private TextField input07;

    @FXML
    private TextField input37;

    @FXML
    private TextField input38;

    @FXML
    private TextField input46;

    @FXML
    private TextField input47;

    @FXML
    private TextField input48;

    @FXML
    private TextField input56;

    @FXML
    private TextField input57;

    @FXML
    private TextField input58;

    @FXML
    private TextField input36;

    @FXML
    private TextField input67;

    @FXML
    private TextField input68;

    @FXML
    private TextField input76;

    @FXML
    private TextField input77;

    @FXML
    private TextField input78;

    @FXML
    private TextField input86;

    @FXML
    private TextField input87;

    @FXML
    private TextField input88;

    @FXML
    private TextField input66;

}

