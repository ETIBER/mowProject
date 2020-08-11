import application.MowerSimulatorFactory;
import infrastructure.ApplicationController;
import infrastructure.ConfigurationFileReader;
import infrastructure.InvalidLineException;

import java.io.FileNotFoundException;

class Main {
    public static void main(String[] args) throws FileNotFoundException, InvalidLineException {
        ConfigurationFileReader configurationFileReader = new ConfigurationFileReader();
        MowerSimulatorFactory mowerSimulatorFactory = new MowerSimulatorFactory();
        ApplicationController applicationController = new ApplicationController(configurationFileReader, mowerSimulatorFactory);
        applicationController.readFileAndRunSimulation(args[0]);
    }
}
