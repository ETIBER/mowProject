package infrastructure;

import domain.MowerSimulationConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigurationFileReader {


    public ConfigurationFileReader() {
    }

    public MowerSimulationConfiguration read(String fileName) throws FileNotFoundException, InvalidLineException {
        MowerSimulationConfigurationBuilder mowerSimulationConfigurationBuilder = new MowerSimulationConfigurationBuilder();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(
                classLoader.getResource(fileName).getFile()
        );
        Scanner myReader = new Scanner(file);
        String upperRightCornerPosition = myReader.nextLine();
        mowerSimulationConfigurationBuilder.withUpperRightCornerPosition(upperRightCornerPosition);
        while (myReader.hasNextLine()) {
            String mowInitialConfiguration = myReader.nextLine();
            String mowCommandList = myReader.nextLine();
            mowerSimulationConfigurationBuilder.withMowConfiguration(mowInitialConfiguration, mowCommandList);
        }
        myReader.close();

        return mowerSimulationConfigurationBuilder.build();
    }
}
