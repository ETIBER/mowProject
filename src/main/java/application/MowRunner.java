package application;

import domain.*;

import java.util.Queue;

public class MowRunner implements Runnable {
    private final MowComputer mowComputer;
    private final Queue<Direction> commands;

    public MowRunner(MowComputer mowComputer, Queue<Direction> commands) {

        this.mowComputer = mowComputer;
        this.commands = commands;
    }

    public static MowRunner initialize(Lawn lawn, MowConfiguration mowConfiguration) {
        Mow mow = new Mow(mowConfiguration.getInitialOrientation());
        try {
            lawn.addMow(mowConfiguration.getInitialPosition(), mow);
            MowComputer mowComputer = MowComputer.initializeMowComputer(lawn, mow);
            return new MowRunner(mowComputer, mowConfiguration.getCommandList());
        } catch (OutOfLawnException | UsedCoordinateException | MowNotInLawnException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void run() {
        while (!this.commands.isEmpty()) mowComputer.moveMow(this.commands.poll());
    }

    public MowPosition getMowPosition() {
        return mowComputer.getMowPosition();
    }
}
