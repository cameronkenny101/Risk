# Risk

## Running the Project

Java11 is required for this project.

### Build and run
Clone the repository, then (using Gradle):
* Build project and run JUnit tests
```
./gradlew build
```
* Run the game
```
./gradlew run
```  

*Note*: If you'd like to run the JAR file created by Gradle, you would need to install JavaFX separately, set path to
the installed JavaFX and add the required [VM arguments](https://stackoverflow.com/a/60113010/12842278) ```--module-path /path/to/javafx/lib --add-modules javafx
.controls,javafx
.fxml,javafx.web```. It is recommended to just build from gradle.