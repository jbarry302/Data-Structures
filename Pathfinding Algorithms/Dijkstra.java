import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class Dijkstra {
    private static final int ROWS = 20;
    private static final int COLS = 20;
    private static final char START = 'S';
    private static final char END = 'E';
    private static final char WALL = 'X';
    private static final char PATH = '_';
    private static final char VISITED = '*';
    private static final char SHORTEST_PATH = 'O';
    private static final int NUM_WALLS = 100;

    public static void main(String[] args) {
        char[][] board = generateBoard();
        System.out.println("Initial board:");
        printBoard(board);

        int[] start = findStart(board);
        int[] end = findEnd(board);

        int[][] distances = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }
        distances[start[0]][start[1]] = 0;

        PriorityQueue < int[] > queue = new PriorityQueue < > ((a, b) -> distances[a[0]][a[1]] - distances[b[0]][b[1]]);
        queue.offer(start);

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == end[0] && curr[1] == end[1]) {
                break;
            }
            if (board[curr[0]][curr[1]] == VISITED) {
                continue;
            }
            board[curr[0]][curr[1]] = VISITED;

            for (int[] neighbor: getNeighbors(curr[0], curr[1], board)) {
                int distance = distances[curr[0]][curr[1]] + 1;
                if (distance < distances[neighbor[0]][neighbor[1]]) {
                    distances[neighbor[0]][neighbor[1]] = distance;
                    queue.offer(neighbor);
                }
            }
        }

        markShortestPath(board, distances, start, end);
        System.out.println("\n\nBoard with shortest path:");
        printBoard(board);
    }

    private static void markShortestPath(char[][] board, int[][] distances, int[] start, int[] end) {
        int[] curr = end;
        while (curr[0] != start[0] || curr[1] != start[1]) {
            board[curr[0]][curr[1]] = SHORTEST_PATH;
            curr = getMinDistanceNeighbor(curr[0], curr[1], distances);
        }
        board[start[0]][start[1]] = START;
        board[end[0]][end[1]] = END;
    }

    private static int[] getMinDistanceNeighbor(int row, int col, int[][] distances) {
        int[] minNeighbor = null;
        int minDistance = Integer.MAX_VALUE;
        for (int[] neighbor: getNeighbors(row, col, distances)) {
            if (distances[neighbor[0]][neighbor[1]] < minDistance) {
                minNeighbor = neighbor;
                minDistance = distances[neighbor[0]][neighbor[1]];
            }
        }
        return minNeighbor;
    }

    private static int[][] getNeighbors(int row, int col, char[][] board) {
        int[][] neighbors = new int[4][2];
        int count = 0;
        if (row > 0 && board[row - 1][col] != WALL) {
            neighbors[count++] = new int[] {
                row - 1, col
            };
        }
        if (col > 0 && board[row][col - 1] != WALL) {
            neighbors[count++] = new int[] {
                row,
                col - 1
            };
        }
        if (row < ROWS - 1 && board[row + 1][col] != WALL) {
            neighbors[count++] = new int[] {
                row + 1, col
            };
        }
        if (col < COLS - 1 && board[row][col + 1] != WALL) {
            neighbors[count++] = new int[] {
                row,
                col + 1
            };
        }
        return Arrays.copyOf(neighbors, count);
    }

    private static int[][] getNeighbors(int row, int col, int[][] distances) {
        int[][] neighbors = new int[4][2];
        int count = 0;
        if (row > 0 && distances[row - 1][col] != Integer.MAX_VALUE) {
            neighbors[count++] = new int[] {
                row - 1, col
            };
        }
        if (col > 0 && distances[row][col - 1] != Integer.MAX_VALUE) {
            neighbors[count++] = new int[] {
                row,
                col - 1
            };
        }
        if (row < ROWS - 1 && distances[row + 1][col] != Integer.MAX_VALUE) {
            neighbors[count++] = new int[] {
                row + 1, col
            };
        }
        if (col < COLS - 1 && distances[row][col + 1] != Integer.MAX_VALUE) {
            neighbors[count++] = new int[] {
                row,
                col + 1
            };
        }
        return Arrays.copyOf(neighbors, count);
    }

    private static char[][] generateBoard() {
        char[][] board = new char[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            Arrays.fill(board[i], PATH);
        }
        board[0][0] = START;
        board[ROWS-1][COLS-1] = END;
        Random rand = new Random();
        for (int i = 0; i < NUM_WALLS; i++) {
            int row = rand.nextInt(ROWS);
            int col = rand.nextInt(COLS);
            if (board[row][col] == START || board[row][col] == END) {
                // Skip if the randomly generated position is already the start or end point
                continue;
            }
            board[row][col] = WALL;
        }
        return board;
    }

    private static int[] findStart(char[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == START) {
                    return new int[] {
                        i,
                        j
                    };
                }
            }
        }
        return null;
    }

    private static int[] findEnd(char[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == END) {
                    return new int[] {
                        i,
                        j
                    };
                }
            }
        }
        return null;
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}