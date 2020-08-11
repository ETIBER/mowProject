package application;

import domain.MowerSimulationConfiguration;

public class MowerSimulatorFactory {
    public MowerSimulator create(MowerSimulationConfiguration mowerSimulationConfiguration) {
        return MowerSimulator.initialize(mowerSimulationConfiguration.getUpperRightCornerPosition(), mowerSimulationConfiguration.getMowConfigurationList());
    }
}
