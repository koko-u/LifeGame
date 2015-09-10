/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.kokou.sample.lifegame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import jp.co.kokou.sample.lifegame.exceptions.InvalidSizeException;

/**
 *
 * @author kozaki
 */
public class Board {

    private final Map<Position, Cell> board = new HashMap<>();
    private final int width;
    private final int height;

    /**
     * コンストラクタ、縦横のサイズを指定したライフゲームの土台を作成する 作成した時点では死んだセルで埋め尽くされている
     *
     * @param width  横幅
     * @param height 縦幅
     * @throws InvalidSizeException 指定されたサイズがマイナス値である等、不適切な場合
     */
    Board(int width, int height) throws InvalidSizeException {
        if (width <= 0 || height <= 0) {
            throw new InvalidSizeException();
        }

        this.width = width;
        this.height = height;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board.put(new Position(x, y), new DeadCell());
            }
        }
    }

    /**
     * ボードを初期化する
     *
     * @param positions 生きているセルを配置する場所の配列
     */
    void initialize(Position... positions) {
        Arrays.asList(positions)
                .stream()
                .filter(position -> position.inRange(width, height))
                .forEach(position -> board.put(position, new LiveCell()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(board.get(new Position(x, y)));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * ボード上に生存しているセルがいるかどうか
     *
     * @return 生存しているセルがひとつでもあれば true
     */
    boolean hasSurvior() {
        return board.values().stream().anyMatch(cell -> cell.alive());
    }

    /**
     * 次の世代へと世代交代する
     */
    void nextGeneration() {
        // TODO 世代交代のロジックを実装する
        Map<Position, Cell> newBoard = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell currentCell = board.getOrDefault(new Position(x, y), new DeadCell());
                Cell nextCell = currentCell.next(aroundCount(new Position(x, y)));
                newBoard.put(new Position(x, y), nextCell);
            }
        }
    }



    /**
     * 指定した位置の周りにいくつ生きているセルがあるかを返す
     *
     * @param position 知りたいセルの位置
     * @return 周りで生存しているセルの数
     */
    int aroundCount(Position position) {
        return position.around()
                .stream()
                .map(pos -> board.getOrDefault(pos, new DeadCell()))
                .filter(cell -> cell.alive())
                .mapToInt(c -> 1)
                .sum();
    }

}
