package com.sidneysimmons.gradlepluginexternalpropertiestests.test;

import static org.junit.Assert.assertTrue;

import com.sidneysimmons.gradlepluginexternalpropertiestests.TestConstants;
import com.sidneysimmons.gradlepluginexternalpropertiestests.TestUtil;
import java.io.IOException;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Tests for project 02. Multi-project structure with user home directory overrides for both the
 * root and the subproject.
 */
public class Project02 {

    @ClassRule
    public static TemporaryFolder projectDirectory = new TemporaryFolder();

    @BeforeClass
    public static void beforeClass() throws IOException {
        // Create the root project
        TestUtil.copyFile("project-02/settings.gradle", projectDirectory.newFile("settings.gradle"));
        TestUtil.copyFile("project-02/build.gradle", projectDirectory.newFile("build.gradle"));
        TestUtil.copyFile("project-02/build.properties", projectDirectory.newFile("build.properties"));

        // Create the subproject
        projectDirectory.newFolder("subproject");
        TestUtil.copyFile("project-02/subproject/build.gradle", projectDirectory.newFile("subproject/build.gradle"));
        TestUtil.copyFile("project-02/subproject/build.properties", projectDirectory.newFile("subproject/build.properties"));

        // Create the user home directory
        projectDirectory.newFolder(TestConstants.USER_HOME_PROPERTIES_DIRECTORY + "/project-02/subproject");
        TestUtil.copyFile("project-02/user-home/project-02/build.properties",
                projectDirectory.newFile(TestConstants.USER_HOME_PROPERTIES_DIRECTORY + "/project-02/build.properties"));
        TestUtil.copyFile("project-02/user-home/project-02/subproject/build.properties",
                projectDirectory.newFile(TestConstants.USER_HOME_PROPERTIES_DIRECTORY + "/project-02/subproject/build.properties"));
    }

    /**
     * Exists in all (home root, home subproject, root, and subproject).
     */
    @Test
    public void testProperty01() {
        BuildResult rootResult = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments(":getProperty01", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        BuildResult subprojectResult = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments(":subproject:getProperty01", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        assertTrue(rootResult.getOutput().contains("I'm in the user home root directory."));
        assertTrue(subprojectResult.getOutput().contains("I'm in the user home subproject directory."));
    }

    /**
     * Exists in home root and home subproject.
     */
    @Test
    public void testProperty02() {
        BuildResult rootResult = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments(":getProperty02", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        BuildResult subprojectResult = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments(":subproject:getProperty02", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        assertTrue(rootResult.getOutput().contains("I'm in the user home root directory."));
        assertTrue(subprojectResult.getOutput().contains("I'm in the user home subproject directory."));
    }

    /**
     * Exists in root and subproject.
     */
    @Test
    public void testProperty03() {
        BuildResult rootResult = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments(":getProperty03", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        BuildResult subprojectResult = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments(":subproject:getProperty03", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        assertTrue(rootResult.getOutput().contains("I'm in the root project."));
        assertTrue(subprojectResult.getOutput().contains("I'm in the subproject project."));
    }

    /**
     * Exists in root.
     */
    @Test
    public void testProperty04() {
        BuildResult rootResult = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments(":getProperty04", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        BuildResult subprojectResult = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments(":subproject:getProperty04", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        assertTrue(rootResult.getOutput().contains("I'm in the root project."));
        assertTrue(subprojectResult.getOutput().contains("I'm in the root project."));
    }

    /**
     * Exists in subproject.
     */
    @Test
    public void testProperty05() {
        BuildResult rootResult = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments(":getProperty05", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        BuildResult subprojectResult = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments(":subproject:getProperty05", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        assertTrue(rootResult.getOutput().contains("false"));
        assertTrue(subprojectResult.getOutput().contains("I'm in the subproject project."));
    }

}
