///Stillborn,nagy.david.7@stud.u-szeged.hu

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import game.gmk.GomokuAction;
import game.gmk.GomokuGame;
import game.gmk.GomokuPlayer;


public class SamplePlayer extends GomokuPlayer {

    protected ArrayList<GomokuAction> actions = new ArrayList<GomokuAction>();

    public SamplePlayer(int color, int[][] board, Random random) {
        super(color, board, random);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                actions.add(new GomokuAction(i, j));
            }
        }
    }


    @Override
    public GomokuAction getAction(GomokuAction prevAction, long[] remainingTimes) {
        // I am the first, center start (not necessary)
        if (prevAction == null) {
            int i = board.length / 2;
            int j = board[i].length / 2;
            while (board[i][j] != GomokuGame.EMPTY) {
                i = random.nextInt(board.length);
                j = random.nextInt(board[i].length);
            }
            board[i][j] = color;
            return new GomokuAction(i, j);
        }

        // store enemy's step
        board[prevAction.i][prevAction.j] = 1 - color;

        // find best steps and choose a random one
        Collections.shuffle(actions, random);
        GomokuAction action = null;
        int score = -1;

        // elméletileg megnézi hogy bármelyik irányban van-e 3 egymás mellett az ellenfélnek
        // és ha igen akkor a következő mezőre berak egyet. legalábbis ezt kéne csinálja de valami nem jó
        for (GomokuAction a : actions) {
            if ((board[a.i][a.j] == 1 - color && board[a.i + 1][a.j] == 1 - color
                    && board[a.i + 2][a.j] == 1 - color) && board[a.i + 3][a.j] == GomokuGame.EMPTY) {
                int s = score(a.i + 3, a.j, color) + score(a.i + 3, a.j, 1 - color);
                if (score < s) {
                    score = s;
                    action = a;
                }
                board[action.i + 3][action.j] = color;
                return action;
            }
            else if ((board[a.i][a.j] == 1 - color && board[a.i][a.j + 1] == 1 - color
                    && board[a.i][a.j + 2] == 1 - color) && board[a.i][a.j + 3] == GomokuGame.EMPTY) {
                int s = score(a.i, a.j + 3, color) + score(a.i, a.j + 3, 1 - color);
                if (score < s) {
                    score = s;
                    action = a;
                }
                board[action.i][action.j + 3] = color;
                return action;
            }
            else if ((board[a.i][a.j] == 1 - color && board[a.i][a.j - 1] == 1 - color
                    && board[a.i][a.j - 2] == 1 - color) && board[a.i][a.j - 3] == GomokuGame.EMPTY) {
                int s = score(a.i, a.j - 3, color) + score(a.i, a.j - 3, 1 - color);
                if (score < s) {
                    score = s;
                    action = a;
                }
                board[action.i][action.j - 3] = color;
                return action;
            }
            else if ((board[a.i][a.j] == 1 - color && board[a.i - 1][a.j] == 1 - color
                    && board[a.i - 2][a.j] == 1 - color) && board[a.i - 3][a.j] == GomokuGame.EMPTY) {
                int s = score(a.i - 3, a.j, color) + score(a.i - 3, a.j, 1 - color);
                if (score < s) {
                    score = s;
                    action = a;
                }
                board[action.i - 3][action.j] = color;
                return action;
            }
            else if ((board[a.i][a.j] == 1 - color && board[a.i + 1][a.j + 1] == 1 - color
                    && board[a.i + 2][a.j + 2] == 1 - color) && board[a.i + 3][a.j + 3] == GomokuGame.EMPTY) {
                int s = score(a.i + 3, a.j + 3, color) + score(a.i + 3, a.j + 3, 1 - color);
                if (score < s) {
                    score = s;
                    action = a;
                }
                board[action.i + 3][action.j + 3] = color;
                return action;
            }
            else if ((board[a.i][a.j] == 1 - color && board[a.i - 1][a.j - 1] == 1 - color
                    && board[a.i - 2][a.j - 2] == 1 - color) && board[a.i - 3][a.j - 3] == GomokuGame.EMPTY) {
                int s = score(a.i - 3, a.j - 3, color) + score(a.i - 3, a.j - 3, 1 - color);
                if (score < s) {
                    score = s;
                    action = a;
                }
                board[action.i - 3][action.j - 3] = color;
                return action;
            }
            else if ((board[a.i][a.j] == 1 - color && board[a.i + 1][a.j - 1] == 1 - color
                    && board[a.i + 2][a.j - 2] == 1 - color) && board[a.i + 3][a.j - 3] == GomokuGame.EMPTY) {
                int s = score(a.i + 3, a.j - 3, color) + score(a.i + 3, a.j - 3, 1 - color);
                if (score < s) {
                    score = s;
                    action = a;
                }
                board[action.i + 3][action.j - 3] = color;
                return action;
            }
            else if ((board[a.i][a.j] == 1 - color && board[a.i - 1][a.j + 1] == 1 - color &&
                    board[a.i - 2][a.j + 2] == 1 - color) && board[a.i - 3][a.j + 3] == GomokuGame.EMPTY) {
                int s = score(a.i - 3, a.j + 3, color) + score(a.i - 3, a.j + 3, 1 - color);
                if (score < s) {
                    score = s;
                    action = a;
                }
                board[action.i - 3][action.j + 3] = color;
                return action;
            }
            else if (board[a.i][a.j] == GomokuGame.EMPTY) {
                int s = score(a.i, a.j, color) + score(a.i, a.j, 1 - color);
                if (score < s) {
                    score = s;
                    action = a;
                }
                board[action.i][action.j] = color;
                return action;
            }
        }

        // store and do best step
        return action;
    }

    protected int score(int i, int j, int c) {
        int result = 0;
        // right up
        result += 1 << countDirection(i, j, 1, -1, c);
        // right
        result += 1 << countDirection(i, j, 1, 0, c);
        // right down
        result += 1 << countDirection(i, j, 1, 1, c);
        // down
        result += 1 << countDirection(i, j, 0, 1, c);
        // left down
        result += 1 << countDirection(i, j, -1, 1, c);
        // left
        result += 1 << countDirection(i, j, -1, 0, c);
        // left up
        result += 1 << countDirection(i, j, -1, -1, c);
        // up
        result += 1 << countDirection(i, j, 0, -1, c);
        return result;
    }

    protected int countDirection(int i, int j, int di, int dj, int c) {
        int ni = (i + board.length + di) % board.length;
        int nj = (j + board[ni].length + dj) % board[ni].length;
        if (board[ni][nj] != c) {
            return 0;
        }
        return 1 + countDirection(ni, nj, di, dj, c);
    }
}