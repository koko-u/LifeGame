/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.kokou.sample.lifegame;

import jp.co.kokou.sample.lifegame.exceptions.InvalidSizeException;
import net.avh4.test.junit.Nested;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

/**
 *
 * @author kozaki
 */
@RunWith(Nested.class)
public class BoardTest {

    public class 指定した縦横幅が不正の場合 {

        @Rule
        public ExpectedException exception = ExpectedException.none();

        @Test
        public void 指定した横幅がゼロの場合_InvalidSizeExceptionが発生する() {
            exception.expect(InvalidSizeException.class);
            new Board(0, 10);
        }

        @Test
        public void 指定した横幅がマイナス値の場合_InvalidSizeExceptionが発生する() {
            exception.expect(InvalidSizeException.class);
            new Board(-1, 10);
        }

        @Test
        public void 指定した縦幅がゼロの場合_InvalidSizeExceptionが発生する() {
            exception.expect(InvalidSizeException.class);
            new Board(10, 0);
        }

        @Test
        public void 指定した縦幅がマイナス値の場合_InvalidSizeExceptionが発生する() {
            exception.expect(InvalidSizeException.class);
            new Board(10, -1);
        }

    }

    public class 縦横2x3のボードを生成した場合 {

        private Board board;

        @Before
        public void setUp() {
            board = new Board(3, 2);
        }

        public class 初期状態 {
            @Test
            public void 表示すると世界はまっ白() {
                assertThat(board.toString(), is("□□□\n□□□\n"));
            }

            @Test
            public void 生存しているセルはいない() {
                assertFalse(board.hasSurvior());
            }
        }

        public class ボード内に生きているセルを配置する {

            @Before
            public void setUp() {
                board.initialize(new Position(1, 1));
            }

            @Test
            public void 生存しているセルが1x1に存在している世界となる() {
                assertThat(board.toString(), is("□□□\n□■□\n"));
            }

            @Test
            public void 生存しているセルがいる() {
                assertTrue(board.hasSurvior());
            }
        }

        public class ボード内に一度に複数のセルを配置した場合 {

            @Before
            public void setUp() {
                board.initialize(
                        new Position(1, 1),
                        new Position(2, 0)
                );
            }

            @Test
            public void 生存しているセルが複数存在している世界となる() {
                assertThat(board.toString(), is("□□■\n□■□\n"));
            }

            @Test
            public void 生存しているセルがいる() {
                assertTrue(board.hasSurvior());
            }
        }

        public class ボードの外にセルを配置した場合 {

            @Before
            public void setUp() {
                board.initialize(new Position(1, 2));
            }

            @Test
            public void 表示される世界は白いまま() {
                assertThat(board.toString(), is("□□□\n□□□\n"));
            }

            @Test
            public void 生存しているセルはいない() {
                assertFalse(board.hasSurvior());
            }
        }
    }

    public class 周辺のセルを数える {

        private Board board;

        //
        //  ■■□
        //  ■□□
        //  □□□
        //
        @Before
        public void setUp() {
            board = new Board(3, 3);
            board.initialize(new Position(0, 0), new Position(1, 0), new Position(0, 1));
        }

        @Test
        public void 隅0x0の周りにはには生きているセルが2つある() {
            assertThat(board.aroundCount(new Position(0, 0)), is(2));
            }

        @Test
        public void 中央1x1の周りには生きているセルが3つある() {
            assertThat(board.aroundCount(new Position(1, 1)), is(3));
            }

            @Test
        public void 反対の隅2x2には生きているセルはいない() {
            assertThat(board.aroundCount(new Position(2, 2)), is(0));
        }

    }
}
