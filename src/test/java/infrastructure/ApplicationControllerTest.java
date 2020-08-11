package infrastructure;

import application.MowerSimulator;
import application.MowerSimulatorFactory;
import domain.MowerSimulationConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.*;

class ApplicationControllerTest {

    ConfigurationFileReader configurationFileReader = mock(ConfigurationFileReader.class);
    MowerSimulatorFactory mowerSimulatorFactory = mock(MowerSimulatorFactory.class);
    MowerSimulator mowerSimulator = mock(MowerSimulator.class);
    MowerSimulationConfiguration mowerSimulationConfiguration = mock(MowerSimulationConfiguration.class);
    String fileName = "file.txt";

    @BeforeEach
    void setUp() throws FileNotFoundException, InvalidLineException {
        reset(configurationFileReader, mowerSimulatorFactory);
        when(configurationFileReader.read(fileName)).thenReturn(mowerSimulationConfiguration);
        when(mowerSimulatorFactory.create(mowerSimulationConfiguration)).thenReturn(mowerSimulator);
    }

    @Test
    void readFileAndRunSimulationShouldCallReadWithFilename() throws FileNotFoundException, InvalidLineException {
        ApplicationController applicationController = new ApplicationController(configurationFileReader, mowerSimulatorFactory);
        applicationController.readFileAndRunSimulation(fileName);
        verify(configurationFileReader).read(fileName);
    }

    @Test
    void readFileAndRunSimulationShouldCreateSimulationWithFileAndExecuteIt() throws FileNotFoundException, InvalidLineException {
        ApplicationController applicationController = new ApplicationController(configurationFileReader, mowerSimulatorFactory);
        applicationController.readFileAndRunSimulation(fileName);

        verify(mowerSimulatorFactory).create(mowerSimulationConfiguration);
        verify(mowerSimulator).execute();
    }
}
