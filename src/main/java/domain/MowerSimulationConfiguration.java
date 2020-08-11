package domain;

import java.util.List;

public class MowerSimulationConfiguration {
    private final CartesianCoordinates upperRightCornerPosition;
    private final List<MowConfiguration> mowConfigurationList;

    public MowerSimulationConfiguration(CartesianCoordinates upperRightCornerPosition, List<MowConfiguration> mowConfigurationList) {
        this.upperRightCornerPosition = upperRightCornerPosition;
        this.mowConfigurationList = mowConfigurationList;
    }

    public CartesianCoordinates getUpperRightCornerPosition() {
        return this.upperRightCornerPosition;
    }

    public List<MowConfiguration> getMowConfigurationList() {
        return this.mowConfigurationList;
    }
}
