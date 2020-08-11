package infrastructure;

import application.MowerSimulatorFactory;
import domain.MowPosition;
import domain.MowerSimulationConfiguration;

import java.io.FileNotFoundException;
import java.util.List;

public class ApplicationController {
    private final ConfigurationFileReader configurationFileReader;
    private final MowerSimulatorFactory mowerSimulatorFactory;

    public ApplicationController(ConfigurationFileReader configurationFileReader, MowerSimulatorFactory mowerSimulatorFactory) {
        this.configurationFileReader = configurationFileReader;
        this.mowerSimulatorFactory = mowerSimulatorFactory;
    }

    public void readFileAndRunSimulation(String fileName) throws FileNotFoundException, InvalidLineException {
        MowerSimulationConfiguration mowerSimulationConfiguration = this.configurationFileReader.read(fileName);
        List<MowPosition> results = this.mowerSimulatorFactory.create(mowerSimulationConfiguration).execute();
        results.forEach(System.out::println);
    }
}
