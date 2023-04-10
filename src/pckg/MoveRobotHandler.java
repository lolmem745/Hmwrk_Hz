package pckg;

public class MoveRobotHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "move-robot";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        try {
            map.moveRobot(Long.parseLong(args[0]));
            System.out.println("Robot " + args[0] + " moved forward.\n");
        } catch (PositionException e) {
            System.out.println("При перемещении робота возникло исключение: " + e.getMessage() + "\n");
        }
    }


}
