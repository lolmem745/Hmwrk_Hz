package pckg;

import java.util.HashMap;
import java.util.Map;

public class RobotMap {
    private final int n;
    private final int m;

    private final Map<Long, Robot> robots;

    public RobotMap(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Недопустимые значения размера карты!");
        }
        this.n = n;
        this.m = m;
        this.robots = new HashMap<>();
    }

    public Long createRobot(Point position) throws PositionException {
        checkPosition(position);

        Robot robot = new Robot(position);
        robots.put(robot.id, robot);
        return robot.getId();
    }

    public void moveRobot(Long id) throws PositionException {
        robots.get(id).move();
    }

    public void changeRobotDirection (Long id, String newDirection) throws IllegalArgumentException {
        robots.get(id).changeDirection(newDirection);
    }

    private void checkPosition(Point position) throws PositionException {
        if (position.getX() < 0 || position.getY() < 0 || position.getX() > n || position.getY() > m) {
            throw new PositionException("Некорректное значение точки: " + position);
        }
        if (!isFree(position)) {
            throw new PositionException("Точка " + position + " занята!");
        }
    }

    boolean isFree(Point position) {
        return robots.values().stream()
                .map(Robot::getPosition)
                .noneMatch(position::equals);
    }

    public boolean mapIsEmpty() {
        return robots.size() == 0;
    }

    public class Robot {
        private static long robotId = 1L;

        private final long id;
        private Point position;
        private Direction direction;

        public Robot(Point position) {
            this.id = robotId++;
            this.position = position;
            this.direction = Direction.TOP;
        }

        public Point getPosition() {
            return position;
        }

        public void move() throws PositionException {
            Point newPosition = switch (direction) {
                case TOP -> new Point(position.getX() - 1, position.getY());
                case RIGHT -> new Point(position.getX(), position.getY() + 1);
                case BOTTOM -> new Point(position.getX() + 1, position.getY());
                case LEFT -> new Point(position.getX(), position.getY() - 1);
            };

            checkPosition(newPosition);
            position = newPosition;
        }

        public void changeDirection(String direction) throws IllegalArgumentException {
            this.direction = Direction.valueOf(direction);
        }

        public long getId() {
            return this.id;
        }

        @Override
        public String toString() {
            return String.format("[%d] %s", this.id, this.position.toString());
        }
    }

    public enum Direction {
        TOP, RIGHT, BOTTOM, LEFT
    }

}
