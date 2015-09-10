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
public class LiveCell implements Cell {

    @Override
    public String toString() {
        return "■";
    }

    @Override
    public boolean alive() {
        return true;
    }

    @Override
    public Cell next(int aroundLiveCellCount) {
        if (aroundLiveCellCount == 2 || aroundLiveCellCount == 3) {
            return new LiveCell();
        } else {
            return new DeadCell();
        }
    }

    /**
     * 等値性のテスト。生きているセルは全部同じと判定される
     *
     * @param obj 比較対象のオブジェクト
     * @return オブジェクトが LiveCell なら等しい
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof LiveCell;
    }

    /**
     * 死んだセルのハッシュコード。常に 1
     *
     * @return ハッシュコード
     */
    @Override
    public int hashCode() {
        return 1;
    }

}
