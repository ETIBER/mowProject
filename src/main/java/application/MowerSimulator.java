package application;

import domain.CartesianCoordinates;
import domain.Lawn;
import domain.MowConfiguration;
import domain.MowPosition;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MowerSimulator {
    private final List<MowRunner> mowRunnerList;

    public MowerSimulator(List<MowRunner> mowRunnerList) {

        this.mowRunnerList = mowRunnerList;
    }

    public static MowerSimulator initialize(CartesianCoordinates upperRightCornerPosition, List<MowConfiguration> mowConfigurationList) {
        Lawn lawn = Lawn.generate(upperRightCornerPosition);
        List<MowRunner> mowRunnerList = mowConfigurationList.stream().map(mowConfiguration -> MowRunner.initialize(lawn, mowConfiguration)).filter(Objects::nonNull).collect(Collectors.toList());
        return new MowerSimulator(mowRunnerList);
    }

    public List<MowPosition> execute() {
        List<Thread> threadList = this.mowRunnerList.stream().map(Thread::new).collect(Collectors.toList());
        threadList.forEach(Thread::start);
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.mowRunnerList.stream().map(MowRunner::getMowPosition).collect(Collectors.toList());
    }
}
