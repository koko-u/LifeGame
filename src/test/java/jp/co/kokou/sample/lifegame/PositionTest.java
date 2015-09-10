/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.kokou.sample.lifegame;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
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
public class PositionTest {

    @RunWith(Theories.class)
    public static class Xが1でYが2の場合に範囲内だと判定されるケース {

        @AllArgsConstructor
        @ToString
        public static class Fixture {

            @Getter
            private int width;
            @Getter
            private int height;
        }

        Position position;

        @Before
        public void setUp() {
            position = new Position(1, 2);
        }

        @DataPoints
        public static List<Fixture> data() {
            return Arrays.asList(
                    new Fixture(2, 3),
                    new Fixture(2, 4),
                    new Fixture(3, 3)
            );
        }

        @Theory
        public void 範囲内にあると判定される(Fixture fixture) {
            assertTrue(position.inRange(fixture.width, fixture.height));
        }
    }

    @RunWith(Theories.class)
    public static class Xが1でYが2の場合に範囲外であると判定されるケース {

        @AllArgsConstructor
        @ToString
        public static class Fixture {

            @Getter
            private int width;
            @Getter
            private int height;
        }

        Position position;

        @Before
        public void setUp() {
            position = new Position(1, 2);
        }

        @DataPoints
        public static List<Fixture> data() {
            return Arrays.asList(
                    new Fixture(1, 2),
                    new Fixture(1, 3),
                    new Fixture(2, 2),
                    new Fixture(1, -2)
            );
        }

        @Theory
        public void 範囲外であると判定される(Fixture fixture) {
            assertFalse(position.inRange(fixture.width, fixture.height));
        }
    }

    public static class 周辺の位置を得ることができる {

        private Position position;

        @Before
        public void setUp() {
            position = new Position(1, 1);
        }

        @Test
        public void 周辺の場所として8つのポジションを得る() {
            assertThat(position.around(), hasSize(8));
        }

        @Test
        public void 周辺のセルが得られる() {
            assertThat(position.around(),
                       hasItems(
                               new Position(0, 0), new Position(1, 0), new Position(2, 0),
                               new Position(0, 1), new Position(2, 1),
                               new Position(0, 2), new Position(1, 2), new Position(2, 2)));
        }
    }
}
