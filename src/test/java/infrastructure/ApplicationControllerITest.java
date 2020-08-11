package infrastructure;

import application.MowerSimulatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationControllerITest {

    final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    final static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    final static PrintStream originalOut = System.out;
    final static PrintStream originalErr = System.err;

    ConfigurationFileReader configurationFileReader = new ConfigurationFileReader();
    MowerSimulatorFactory mowerSimulatorFactory = new MowerSimulatorFactory();
    ApplicationController applicationController = new ApplicationController(configurationFileReader, mowerSimulatorFactory);

    @BeforeAll
    public static void setUpStreams() {

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void restoreStreams() {

        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void readFileAndRunSimulationForExampleFile() throws FileNotFoundException, InvalidLineException {
        applicationController.readFileAndRunSimulation("testFiles/exampleConfigurationFile.txt");
        assertEquals("1 5 N\n5 3 E\n", outContent.toString());
    }

    // This test fail sometimes because thread  execution is random and execution order can change
    @Test
    void readFileAndRunSimulationForBigSimulationFile() throws FileNotFoundException, InvalidLineException {
        applicationController.readFileAndRunSimulation("testFiles/bigSimulationFile.txt");
        assertEquals("8 2 E\n3 0 S\n1 8 N\n0 5 W\n8 4 E\n3 8 N\n", outContent.toString());
    }
}



