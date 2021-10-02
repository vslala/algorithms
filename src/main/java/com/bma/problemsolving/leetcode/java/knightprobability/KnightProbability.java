package com.bma.problemsolving.leetcode.java.knightprobability;

import java.util.Set;

import static java.lang.System.out;

public class KnightProbability {

    private static final Set<KnightMove> possibleMoves = Set.of(
            new KnightMove(2, 1),
            new KnightMove(2, -1),
            new KnightMove(-2, 1),
            new KnightMove(-2, -1),
            new KnightMove(1, 2),
            new KnightMove(1, -2),
            new KnightMove(-1, 2),
            new KnightMove(-1, -2)
    );
    private final Integer row;
    private final Integer col;

    private Integer boardSize;
    private Integer k;
    private KnightMove startMove;

    public KnightProbability(Integer boardSize, Integer k, Integer row, Integer column) {
        this.boardSize = boardSize;
        this.k = k;
        this.startMove = new KnightMove(row, column);
        this.row = row;
        this.col = column;
    }


    public double probabilityTotalBoundedMovesByTotalMoves() {
        double [][] currBoard = new double[boardSize][boardSize];
        double [][] probableBoard = new double[boardSize][boardSize];

        currBoard[row][col] = 1.0D;
        for (int m = 0; m < k; m++) {
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    var currMove = new KnightMove(i, j);
                    Set<KnightMove> validMoves = currMove.findAllBoundedMoves(possibleMoves, boardSize);
                    for (KnightMove validMove : validMoves) {
                        probableBoard[validMove.getX()][validMove.getY()] += currBoard[currMove.getX()][currMove.getY()] / 8;
                    }
                }
            }

            currBoard = probableBoard;
            probableBoard = new double[boardSize][boardSize];
            fillBoardWithProbabilityZero(probableBoard);
        }

        return calcProbability(currBoard);
    }

    private double calcProbability(double[][] currBoard) {
        var probability = 0.0D;
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                probability += currBoard[i][j];
        return probability;
    }

    private void print(double[][] currBoard) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                out.print(currBoard[i][j] + "|");
            }
            out.println();
        }
    }

    private void fillBoardWithProbabilityZero(double[][] currBoard) {
        for (int i = 0; i < boardSize; i++) for (int j = 0; j < boardSize; j++) currBoard[i][j] = 0.0D;
    }

}
