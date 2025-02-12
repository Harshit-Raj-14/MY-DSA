import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class SnakeGame {
    // Direction constants
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    // Game board dimensions
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    // Snake representation
    private LinkedList<int[]> snake;
    private Direction direction;
    private int[] food;
    private boolean gameOver;

    public SnakeGame() {
        snake = new LinkedList<>();
        // Initialize snake with 1 segment at the center of the board
        snake.add(new int[]{HEIGHT / 2, WIDTH / 2});
        direction = Direction.RIGHT; // Default direction
        food = new int[2];
        placeFood();
        gameOver = false;
    }

    // Places food in a random location on the board
    private void placeFood() {
        Random random = new Random();
        food[0] = random.nextInt(HEIGHT);
        food[1] = random.nextInt(WIDTH);
    }

    // Moves the snake in the current direction
    public void move() {
        if (gameOver) return;

        int[] head = snake.peekFirst();
        int newHead[] = new int[]{head[0], head[1]};

        // Move the snake head based on the current direction
        switch (direction) {
            case UP:
                newHead[0]--;
                break;
            case DOWN:
                newHead[0]++;
                break;
            case LEFT:
                newHead[1]--;
                break;
            case RIGHT:
                newHead[1]++;
                break;
        }

        // Check for collisions with the walls
        if (newHead[0] < 0 || newHead[0] >= HEIGHT || newHead[1] < 0 || newHead[1] >= WIDTH) {
            gameOver = true;
            return;
        }

        // Check for collision with itself
        for (int[] part : snake) {
            if (newHead[0] == part[0] && newHead[1] == part[1]) {
                gameOver = true;
                return;
            }
        }

        // Add the new head to the snake
        snake.addFirst(newHead);

        // Check if the snake eats the food
        if (newHead[0] == food[0] && newHead[1] == food[1]) {
            placeFood(); // Generate new food
        } else {
            snake.removeLast(); // Remove the tail if no food is eaten
        }
    }

    // Changes the direction of the snake
    public void changeDirection(Direction newDirection) {
        // Prevent reversing direction
        if ((direction == Direction.UP && newDirection != Direction.DOWN) ||
            (direction == Direction.DOWN && newDirection != Direction.UP) ||
            (direction == Direction.LEFT && newDirection != Direction.RIGHT) ||
            (direction == Direction.RIGHT && newDirection != Direction.LEFT)) {
            direction = newDirection;
        }
    }

    // Displays the game board
    public void displayBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                boolean printed = false;

                // Print snake
                for (int[] part : snake) {
                    if (part[0] == i && part[1] == j) {
                        System.out.print("S ");
                        printed = true;
                        break;
                    }
                }

                // Print food
                if (!printed && food[0] == i && food[1] == j) {
                    System.out.print("F ");
                    printed = true;
                }

                // Print empty space
                if (!printed) {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        Scanner scanner = new Scanner(System.in);

        while (!game.isGameOver()) {
            game.displayBoard();
            System.out.println("Enter direction (W=up, S=down, A=left, D=right): ");
            char input = scanner.nextLine().toUpperCase().charAt(0);

            switch (input) {
                case 'W':
                    game.changeDirection(Direction.UP);
                    break;
                case 'S':
                    game.changeDirection(Direction.DOWN);
                    break;
                case 'A':
                    game.changeDirection(Direction.LEFT);
                    break;
                case 'D':
                    game.changeDirection(Direction.RIGHT);
                    break;
            }

            game.move();
        }

        System.out.println("Game Over!");
        scanner.close();
    }
}
