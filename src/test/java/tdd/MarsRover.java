package tdd;

class MarsRover {

    private Direction facing = Direction.NORTH;

    public void sendCommands(String commands) {

        for (char command : commands.toCharArray()) {
            if (command == 'r') {
                facing = facing.right();
            }

        }

    }

    public Direction facing() {
        return facing;
    }

    public Position position() {
        return new Position(0, 0);
    }
}