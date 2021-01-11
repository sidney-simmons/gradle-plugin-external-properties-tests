package com.sidneysimmons.gradlepluginexternalpropertiestests;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.junit.rules.TemporaryFolder;

/**
 * Utility methods for testing.
 */
public class TestUtil {

    private TestUtil() {
        // Only used for static utility methods
    }

    /**
     * Get the gradle version we should be running tests against.
     * 
     * @return the gradle version
     */
    public static String getGradleVersion() {
        return System.getProperty("gradle.version");
    }

    /**
     * Get a file for the given path.
     * 
     * @param path the path
     * @return the file
     */
    public static File getFile(String path) {
        ClassLoader classLoader = TestUtil.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        try {
            return new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Cannot get resource file for path = " + path, e);
        }
    }

    /**
     * Copy the contents of the file located at the source path to the destination file.
     * 
     * @param sourcePath the source path
     * @param destinationFile the destination file
     * @throws IOException thrown if there is a problem copying the file content
     */
    public static void copyFile(String sourcePath, File destinationFile) throws IOException {
        FileUtils.copyFile(getFile(sourcePath), destinationFile);
    }

    /**
     * Get the user home directory system property override to tell the gradle build where to find the
     * home directory within the temporary directory structure.
     * 
     * @param projectDirectory the project directory
     * @return the user home directory system property override
     */
    public static String getUserHomeSystemProperty(TemporaryFolder projectDirectory) {
        File userHomeDirectory = new File(projectDirectory.getRoot(), TestConstants.USER_HOME_DIRECTORY);
        return "-Duser.home=" + userHomeDirectory.getAbsolutePath();
    }

}
