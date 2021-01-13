package com.sidneysimmons.gradlepluginexternalpropertiestests.test;

import static org.junit.Assert.assertTrue;

import com.sidneysimmons.gradlepluginexternalpropertiestests.TestUtil;
import java.io.IOException;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Tests for project 03. Meant for testing the addition of custom property resolvers.
 */
public class Project03 {

    @ClassRule
    public static TemporaryFolder projectDirectory = new TemporaryFolder();

    @BeforeClass
    public static void beforeClass() throws IOException {
        // Create the root project
        TestUtil.copyFile("project-03/settings.gradle", projectDirectory.newFile("settings.gradle"));
        TestUtil.copyFile("project-03/build.gradle", projectDirectory.newFile("build.gradle"));

        // Create the custom property files
        TestUtil.copyFile("project-03/custom-resolver-01.properties", projectDirectory.newFile("custom-resolver-01.properties"));
        TestUtil.copyFile("project-03/custom-resolver-02.properties", projectDirectory.newFile("custom-resolver-02.properties"));
        TestUtil.copyFile("project-03/custom-resolver-03.properties", projectDirectory.newFile("custom-resolver-03.properties"));
    }

    /**
     * Exists in all files (file 1, file 2, and file 3).
     */
    @Test
    public void testProperty01() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments("getProperty01", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        assertTrue(result.getOutput().contains("I'm in resolver 01."));
    }

    /**
     * Exists in file 2 and file 3.
     */
    @Test
    public void testProperty02() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments("getProperty02", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        assertTrue(result.getOutput().contains("I'm in resolver 02."));
    }

    /**
     * Exists in file 3.
     */
    @Test
    public void testProperty03() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments("getProperty03", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        assertTrue(result.getOutput().contains("I'm in resolver 03."));
    }

    /**
     * Doesn't exist in any files.
     */
    @Test
    public void testProperty04() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments("getProperty04", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        assertTrue(result.getOutput().contains("false"));
    }

    /**
     * Only available through the custom resolver.
     */
    @Test
    public void testProperty05() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments("getProperty05", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        assertTrue(result.getOutput().contains("You found me."));
    }

    /**
     * Test that the task to show the property resolvers works.
     */
    @Test
    public void showPropertyResolvers() {
        GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot()).withArguments("showPropertyResolvers")
                .build();
    }

}
