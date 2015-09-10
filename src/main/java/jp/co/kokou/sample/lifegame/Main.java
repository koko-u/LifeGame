/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.kokou.sample.lifegame;

/**
 *
 * @author kozaki
 */
public class Main {
    public static void main(String[] args) {
        Board board = new Board(10, 5);
        /*
         * ボードの初期化 (ダイハード)
         * 
         * □□□□□□□□□□
         * □□□□□□□■□□
         * □■■□□□□□□□
         * □□■□□□■■■□
         * □□□□□□□□□□
         */
        board.initialize(
                new Position(1, 2),
                new Position(2, 2),
                new Position(2, 3),
                new Position(7, 1),
                new Position(6, 3),
                new Position(7, 3),
                new Position(8, 3)
        );

        while (board.hasSurvior()) {
            System.out.println(board);
            board.nextGeneration();
        }
    }
}
