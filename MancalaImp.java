import java.util.LinkedList;
import java.util.Queue;

/**
 * A class that implements a Mancala agent
 * by implementing the MancalaAgent interface.
 * @author Bruce How (22242664)
 */

public class MancalaImp implements MancalaAgent {

    private final String agentName = "Bruce";

    private int[] simulateMiniMax(int[] board, int move, boolean isMax) {
        int seeds = board[move];
        board[move] = 0; // Pick up all seeds from current house

        int temp = 0;
        
        // Simulate seed sowing
        for (int i = 0; i < seeds; i++) {
            int houseSeed = move + i + 1;

            if (isMax) {
                if (houseSeed < 6 && (i + 1) == seeds && board[houseSeed] == 0) {
                    board[6] += board[12 - houseSeed] + 1;
                    board[12 - houseSeed] = 0;
                    break;
                } else if (houseSeed >= 13) {
                    board[temp++] += 1;
                } else {
                    board[houseSeed] += 1;
                }
            } else {
                if (houseSeed > 6 && houseSeed < 13 && i+1 == seeds && board[i+1] == 0) {
                    board[6] += board[12 - houseSeed] + 1;
                    board[12 - houseSeed] = 0;
                    break;
                } else if (houseSeed > 13) {
                    if (temp == 6) {
                        temp++;
                        board[temp++] += 1;
                        continue;
                    }
                    board[temp++] += 1;
                } else if (houseSeed < 13) {
                    board[houseSeed] += 1;
                }
            }
        }
        return board;
    }

    private int miniMax(int[] board, boolean isMax, int top, int depth, int limit, int max, int min) {
        if (depth > limit) {
            return board[13] - board[6];
        }

        Queue<Integer> moves = new LinkedList<>();
        int store = 0;

        if (isMax) {
            store = Integer.MIN_VALUE; // Sets the current best value to the minimum int
            for (int i = 0; i < 6; i++) {
                if (board[i] != 0) {
                    moves.add(i);
                }
            }
        } else {
            store = Integer.MAX_VALUE; // Vice versa to maximum int
            for (int i = 7; i < 13; i++) {
                if (board[i] != 0) {
                    moves.add(i);
                }
            }
        }

        // Run MiniMax simulation
        while (!moves.isEmpty()) {
            top = moves.remove();
            int opt = board[top] + top;
            board = simulateMiniMax(board, top, isMax);

            if (isMax && opt == 6) {
                isMax = false;
            } else if (!isMax && opt == 13) {
                isMax = true;
            }

            isMax = !isMax;
            int seeds = miniMax(board, isMax, top, depth + 1, limit, max, min);

            // Filter better Minimax values
            if (isMax) {
                store = Math.max(store, seeds);
                if (store >= min) {
                    return top;
                }
                store = Math.max(store, min);
            } else {
                store = Math.min(store, seeds);
                if (store <= max) {
                    return top;
                }
                min = Math.min(store, min);
            }
        }
        return top;
    }

    private int move(int[] board, boolean isMax, int limit) {
        Queue<Integer> moves = new LinkedList<>();
        int result = -1;
        int bestSeeds = 0;
        
        if (isMax) {
            bestSeeds = Integer.MIN_VALUE;
            for (int i = 0; i < 6; i++) {
                if (board[i] != 0) {
                    moves.add(i);
                }
            }
        } else {
            bestSeeds = Integer.MAX_VALUE;
            for (int i = 7; i < 13; i++) {
                if (board[i] != 0) {
                    moves.add(i);
                }
            }
        }

        int current = -1;
        while (!moves.isEmpty()) {
            isMax = true;
            current = moves.remove();
            int seeds = miniMax(board, isMax, current, 1, limit, Integer.MIN_VALUE, Integer.MAX_VALUE);
        
             // Set the best max/min values accordingly
            if (isMax) {
                if (seeds > bestSeeds) {
                    bestSeeds = seeds;
                    result = current;
                } else if (seeds < bestSeeds) {
                    bestSeeds = seeds;
                    result = current;
                }
            }
        }
        return result;
    }

    @Override
    public int move(int[] board) {
        return move(board, true, 50);
    }

    @Override
    public String name() {
        return agentName;
    }

    @Override
    public void reset() {
    }

}
