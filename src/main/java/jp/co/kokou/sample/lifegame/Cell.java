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
public interface Cell {

    /**
     * セルが生きているか死んでいるかを示す
     *
     * @return 生きていれば true 死んでいれば false
     */
    public boolean alive();

    /**
     * 自身の周囲にあるセルの数から、次のセルを生み出す
     *
     * @param aroundLiveCellCount 周辺に存在しているセルの数
     * @return 新たに誕生するセル
     */
    public Cell next(int aroundLiveCellCount);
}
