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
 * Tests for project 01. Basic single project structure.
 */
public class Project01 {

    @ClassRule
    public static TemporaryFolder projectDirectory = new TemporaryFolder();

    @BeforeClass
    public static void beforeClass() throws IOException {
        TestUtil.copyFile("project-01/settings.gradle", projectDirectory.newFile("settings.gradle"));
        TestUtil.copyFile("project-01/build.gradle", projectDirectory.newFile("build.gradle"));
        TestUtil.copyFile("project-01/build.properties", projectDirectory.newFile("build.properties"));
    }

    /**
     * Property exists.
     */
    @Test
    public void testPropertyGetTrue() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments("propertyGetTrue").build();
        assertTrue(result.getOutput().contains("Hi there."));
    }

    /**
     * Property doesn't exist (exception should be thrown).
     */
    @Test
    public void propertyGetFalse() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments("propertyGetFalse").build();
        assertTrue(result.getOutput().contains("Exception was thrown."));
    }

    /**
     * Property exists so the default shouldn't be returned.
     */
    @Test
    public void testPropertyDefaultExistsTrue() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments("propertyDefaultExistsTrue").build();
        assertTrue(result.getOutput().contains("Hi there."));
    }

    /**
     * Property doesn't exist so the default should be returned.
     */
    @Test
    public void testPropertyDefaultExistsFalse() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments("propertyDefaultExistsFalse").build();
        assertTrue(result.getOutput().contains("This is a default value."));
    }

    /**
     * Property exists so this should return true.
     */
    @Test
    public void testPropertyExistsTrue() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments("propertyExistsTrue").build();
        assertTrue(result.getOutput().contains("true"));
    }

    /**
     * Property doesn't exist so this should return false.
     */
    @Test
    public void propertyExistsFalse() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments("propertyExistsFalse").build();
        assertTrue(result.getOutput().contains("false"));
    }

}
