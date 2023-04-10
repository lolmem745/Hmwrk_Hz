package pckg;


public class ChangeDirectionHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "change-direction";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        try {
            map.changeRobotDirection(Long.parseLong(args[0]),
                    args[1].toUpperCase());
            System.out.println("Robot " + args[0] + " looking " + args[1].toUpperCase() + " now.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Такого направления не существует.\n");
        }
    }


}
