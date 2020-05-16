# gradle-plugin-external-properties-tests

gradle-plugin-external-properties-tests is a multi-project gradle project meant for testing the [gradle-plugin-external-properties](https://github.com/sidney-simmons/gradle-plugin-external-properties) plugin.

## Usage

Clone the plugin repository and execute `./gradlew build`.  Then execute `./gradlew publish`.  This will publish the plugin to a local maven repository directory `[PLUGIN PROJECT ROOT]/build/test-maven-repo`.  Now you can use the local plugin in this project by setting the following in this project's build files.

> build.gradle

``` gradle
plugins {
    id 'com.sidneysimmons.gradle-plugin-external-properties' version '[PLUGIN VERSION]'
}
```

> settings.gradle

``` gradle
pluginManagement {
    repositories {
        maven {
            url '[PLUGIN PROJECT ROOT]/build/test-maven-repo'
        }
        gradlePluginPortal()
    }
}
```

## License
[MIT](https://choosealicense.com/licenses/mit/)