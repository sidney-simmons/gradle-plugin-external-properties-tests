# gradle-plugin-external-properties-tests

gradle-plugin-external-properties-tests is a gradle project meant for unit testing the [gradle-plugin-external-properties](https://github.com/sidney-simmons/gradle-plugin-external-properties) plugin against multiple versions of gradle.

## Testing the Plugin

1. Clone the `gradle-plugin-external-properties` plugin repository mentioned above and execute `./gradlew build` (just to make sure it builds!).
2. Clone this repository and execute `./gradlew testAgainstAllGradleVersions` to run all the tests against all the configured gradle versions. Gradle's "composite builds" feature will replace the dependency in the unit test projects with the locally built artifact from the `gradle-plugin-external-properties` project.

## Publishing the Plugin

1. Increment the "version" within build.gradle.

2. Run the following to publish.

```
./gradlew -Dgradle.publish.key=${PUBLISH_KEY} -Dgradle.publish.secret=${PUBLISH_SECRET} publishPlugins
```

3. Add a new release to the GitHub releases.

## License
[MIT](https://choosealicense.com/licenses/mit/)