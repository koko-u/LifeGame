/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.kokou.sample.lifegame;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * ボードの位置を表現する
 *
 * @author kozaki
 */
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Position {
    @Getter
    private int x;
    @Getter
    private int y;

    /**
     * 自身が指定された幅と高さの範囲に収まっているかを判定する
     *
     * @param width
     * @param height
     * @return
     */
    boolean inRange(int width, int height) {
        return x < width && y < height;
    }

    /**
     * 自分自身の周りの位置をリストで返す
     *
     * @return 周辺のポジションのリスト
     */
    List<Position> around() {
        return Arrays.asList(
                new Position(x - 1, y - 1),
                new Position(x, y - 1),
                new Position(x + 1, y - 1),
                new Position(x - 1, y),
                new Position(x + 1, y),
                new Position(x - 1, y + 1),
                new Position(x, y + 1),
                new Position(x + 1, y + 1)
        );
    }

}
