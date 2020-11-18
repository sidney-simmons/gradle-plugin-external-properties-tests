# gradle-plugin-external-properties-tests

gradle-plugin-external-properties-tests is a multi-project gradle project meant for testing the [gradle-plugin-external-properties](https://github.com/sidney-simmons/gradle-plugin-external-properties) plugin.

## Usage

1. Clone the `gradle-plugin-external-properties` plugin repository mentioned above and execute `./gradlew build` (just to make sure it builds!).
2. Clone this repository and make sure the plugin version matches the plugin version declared in `gradle-plugin-external-properties`. Gradle's "composite builds" feature will replace the dependency in this project with the locally built artifact from the `gradle-plugin-external-properties` project.

## License
[MIT](https://choosealicense.com/licenses/mit/)