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
 * Tests for project 04. Multi-project structure where the plugin is only applied in the sub project
 * - not the root.
 */
public class Project04 {

    @ClassRule
    public static TemporaryFolder projectDirectory = new TemporaryFolder();

    @BeforeClass
    public static void beforeClass() throws IOException {
        // Create the root project
        TestUtil.copyFile("project-04/settings.gradle", projectDirectory.newFile("settings.gradle"));
        TestUtil.copyFile("project-04/build.gradle", projectDirectory.newFile("build.gradle"));

        // Create the subproject
        projectDirectory.newFolder("subproject");
        TestUtil.copyFile("project-04/subproject/build.gradle", projectDirectory.newFile("subproject/build.gradle"));
        TestUtil.copyFile("project-04/subproject/build.properties", projectDirectory.newFile("subproject/build.properties"));
    }

    /**
     * Exists in the sub project. Called from the sub project.
     */
    @Test
    public void testProperty01() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments(":subproject:getProperty01", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        assertTrue(result.getOutput().contains("I'm in the subproject project."));
    }

    /**
     * Exists nowhere. Called from the sub project. Meant to test that the code traverses up to the
     * parent and doesn't blow up because the parent doesn't apply the plugin.
     */
    @Test
    public void testProperty02() {
        BuildResult result = GradleRunner.create().withGradleVersion(TestUtil.getGradleVersion()).withProjectDir(projectDirectory.getRoot())
                .withArguments(":subproject:getProperty02", TestUtil.getUserHomeSystemProperty(projectDirectory)).build();
        assertTrue(result.getOutput().contains("false"));
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
