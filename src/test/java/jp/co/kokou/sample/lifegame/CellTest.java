/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.kokou.sample.lifegame;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 *
 * @author kozaki
 */
@RunWith(Enclosed.class)
public class CellTest {

    @RunWith(Theories.class)
    public static class 生死のルール {

        @AllArgsConstructor
        public static class Fixture {
            private int aroundLiveCellCount;
            private Cell currentCell;
            private Cell nextCell;

            @Override
            public String toString() {
                return String.format("around live cell: %d, current cell: %s -> next cell: %s",
                                     aroundLiveCellCount, currentCell.alive() ? "Live" : "Dead",
                                     nextCell.alive() ? "Live" : "Dead");
            }
        }

        @DataPoints
        public static List<Fixture> data() {
            return Arrays.asList(
                    new Fixture(0, new LiveCell(), new DeadCell()),
                    new Fixture(1, new LiveCell(), new DeadCell()),
                    new Fixture(2, new LiveCell(), new LiveCell()),
                    new Fixture(3, new LiveCell(), new LiveCell()),
                    new Fixture(4, new LiveCell(), new DeadCell()),
                    new Fixture(5, new LiveCell(), new DeadCell()),
                    new Fixture(6, new LiveCell(), new DeadCell()),
                    new Fixture(7, new LiveCell(), new DeadCell()),
                    new Fixture(8, new LiveCell(), new DeadCell()),
                    new Fixture(0, new DeadCell(), new DeadCell()),
                    new Fixture(1, new DeadCell(), new DeadCell()),
                    new Fixture(2, new DeadCell(), new DeadCell()),
                    new Fixture(3, new DeadCell(), new LiveCell()),
                    new Fixture(4, new DeadCell(), new DeadCell()),
                    new Fixture(5, new DeadCell(), new DeadCell()),
                    new Fixture(6, new DeadCell(), new DeadCell()),
                    new Fixture(7, new DeadCell(), new DeadCell()),
                    new Fixture(8, new DeadCell(), new DeadCell())
            );
        }

        @Theory
        public void 守られている(Fixture fixture) {
            assertThat(fixture.currentCell.next(fixture.aroundLiveCellCount), is(fixture.nextCell));
        }
    }
}
