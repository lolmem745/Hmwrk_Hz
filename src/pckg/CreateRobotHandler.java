package pckg;

public class CreateRobotHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "create-robot";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        try {
            System.out.println("Robot`s id is " + map.createRobot(
                    new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]))) + "\n");
        } catch (PositionException e) {
            System.out.println("При создании робота возникло исключение: " + e.getMessage() + "\n");
        }
    }


}