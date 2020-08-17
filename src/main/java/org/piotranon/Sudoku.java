package org.piotranon;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Sudoku {
    private int[][] grid;


    // assumes the current class is called MyLogger
    private Logger log = LogManager.getLogger(Sudoku.class);


    public Sudoku() {
        Configurator.setLevel(log.getName(), Level.DEBUG);
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public int[][] getGrid() {
        return grid;
    }

    public boolean solve(int[][] grid) {
        if (containsZeros(grid)) {

            //Iteration over columns
            for (int y = 0; y < 9; y++)

                //Iteration over rows
                for (int x = 0; x < 9; x++)

                    if (grid[y][x] == 0) {

                        Point p = new Point(x, y);

                        List<Integer> availableToFit = whatCanFit(grid, p);

                        log.info("found " + availableToFit.toString() + " that can fit " + p);

                        for (int number : availableToFit) {
                            grid[y][x] = number;
                            log.info("fiting " + number + " at " + p);

                            if (!solve(grid)) {
                                grid[y][x] = 0;
                            }

                            log.info("trying another number to fit at " + p);
                        }

                        if (!containsZeros(grid))
                            return true;
                        return false;
                    }
        }
        return true;
    }

    public boolean containsZeros(int[][] grid) {

        for (int y = 0; y < 9; y++)
            for (int x = 0; x < 9; x++)
                if (grid[y][x] == 0) {
                    log.debug("Grid contains zeros at x=" + x + " y=" + y);
                    return true;
                }
        log.info("Grid does not contains zeros.");
        return false;
    }

    public List<Integer> whatCanFit(int[][] grid, Point p) {
        List<Integer> vertical = whatCanFitVertical(grid, p);
        List<Integer> horizontal = whatCanFitHorizontal(grid, p);
        List<Integer> subGrid = whatCanFitSubGrid(grid, p);

        List<Integer> avaibleToFit = new ArrayList<>();

        avaibleToFit.addAll(vertical);
        avaibleToFit.retainAll(horizontal);
        avaibleToFit.retainAll(subGrid);

        Collections.sort(avaibleToFit);

        return avaibleToFit;
    }

    private List<Integer> allNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    public List<Integer> whatCanFitSubGrid(int[][] grid, Point p) {

        List<Integer> def = new ArrayList<>(allNumbers);

        List<Integer> xToCheck = whichXToValidate(p.getX());
        List<Integer> yToCheck = whichYToValidate(p.getY());

        List<Integer> inSubGrid = new ArrayList();

        for (int yCord : yToCheck) {
            for (int xCord : xToCheck) {
                inSubGrid.add(grid[yCord][xCord]);
            }
        }

        def.removeAll(inSubGrid);

        log.debug("What Fit SubGrid: " + p + " = " + def.toString());

        return def;
    }

    public List<Integer> whatCanFitVertical(int[][] grid, Point p) {

        List<Integer> def = new ArrayList<>(allNumbers);

        List<Integer> inColumn = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            inColumn.add(grid[i][p.getX()]);
        }

        def.removeAll(inColumn);

        log.debug("What Fit Vertical: " + p + " = " + def.toString());

        return def;
    }

    public List<Integer> whatCanFitHorizontal(int[][] grid, Point p) {
        List<Integer> def = new ArrayList<>(allNumbers);

        List<Integer> inRow = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            inRow.add(grid[p.getY()][i]);
        }

        def.removeAll(inRow);

        log.debug("What Fit Horizontal: " + p + " = " + def.toString());

        return def;
    }

    public boolean validate(int[][] grid, Point p, int value) {

        if (validateVertical(grid, p, value) && validateHorizontal(grid, p, value) && validateSubGrid(grid, p, value)) {
            log.info("Validation for " + p.toString() + " for Vertical, Horizontal, SubGrid Completed. No Error.");
            return true;
        }
        log.info("Validation for " + p.toString() + " Failed.");
        return false;

    }

    public boolean validateVertical(int[][] grid, Point p, int value) {

        List<Integer> inColumn = new ArrayList<>();
        for (int y = 0; y < 9; y++) {
            inColumn.add(grid[y][p.getX()]);
        }

        if (inColumn.contains(value)) {
            log.info("Vertical vaildation of " + p.toString() + " with value : " + value + " Failed. There is a same value in column.");
            return false;
        }
        log.info("Vertical validation of " + p.toString() + " with value : " + value + " Completed. No Error.");
        return true;

    }

    public boolean validateHorizontal(int[][] grid, Point p, int value) {
        List<Integer> inRow = new ArrayList<>();
        for (int x = 0; x < 9; x++) {
            inRow.add(grid[p.getY()][x]);
        }

        if (inRow.contains(value)) {
            log.info("Horizontal validation of " + p.toString() + " with value : " + value + " Failed. There is a same value in row.");
            return false;
        }
        log.info("Horizontal validation of " + p.toString() + " with value : " + value + " Completed. No Error.");
        return true;

    }

    public boolean validateSubGrid(int[][] grid, Point p, int value) {
        List<Integer> inSubGrid = new ArrayList<>();

        List<Integer> yToValidate = whichYToValidate(p.getY());
        List<Integer> xToValidate = whichXToValidate(p.getX());

        for (int x : xToValidate)
            for (int y : yToValidate) {
                inSubGrid.add(grid[p.getY()][p.getX()]);
            }

        if (inSubGrid.contains(value)) {
            log.info("SubGrid validation of " + p.toString() + " with value : " + value + " Failed. There is a same value in SubGrid.");
            return false;
        }
        log.info("SubGrid validation of " + p.toString() + " with value : " + value + " Completed.");
        return true;

    }

    private List<Integer> whichXToValidate(int x) {
        List<Integer> xToCheck = new ArrayList<>();
        //X
        if (x % 3 == 0) {
            //+2 right x,x+1,x+2
            xToCheck.add(x);
            xToCheck.add(x + 1);
            xToCheck.add(x + 2);
        }
        if (x % 3 == 1) {
            //+-1 left,right x-1,x,x+1
            xToCheck.add(x - 1);
            xToCheck.add(x);
            xToCheck.add(x + 1);

        }
        if (x % 3 == 2) {
            //-2 left x-2,x-1,x
            xToCheck.add(x - 2);
            xToCheck.add(x - 1);
            xToCheck.add(x);
        }
        return xToCheck;
    }

    private List<Integer> whichYToValidate(int y) {
        List<Integer> yToCheck = new ArrayList<>();

        //Y
        if (y % 3 == 0) {
            //+2 bot y,y+1,y+2
            yToCheck.add(y);
            yToCheck.add(y + 1);
            yToCheck.add(y + 2);
        }
        if (y % 3 == 1) {
            //+-1 top,bot y-1,y,y+1
            yToCheck.add(y - 1);
            yToCheck.add(y);
            yToCheck.add(y + 1);
        }
        if (y % 3 == 2) {
            //-2 top y-2,y-1,y
            yToCheck.add(y - 2);
            yToCheck.add(y - 1);
            yToCheck.add(y);
        }
        return yToCheck;
    }

    public boolean viewGrid() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    if (i == 4)
                        sb.append("Y | ");
                    else
                        sb.append("  | ");
                }
                sb.append(grid[i][j] + " ");
                if (j % 3 == 2)
                    sb.append("| ");
            }
            sb.substring(0, sb.length() - 1);

            if (i == 0) {
                System.out.println("              X            ");
                System.out.println("  -------------------------");
            }

            System.out.println(sb.toString());

            if (i % 3 == 2)
                System.out.println("  -------------------------");
            sb.setLength(0);
        }
        System.out.println();
        return true;
    }
}
