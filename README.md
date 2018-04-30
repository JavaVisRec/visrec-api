# VisualRecognitionApi
Specification of standard Visual Recognition API for Java (JSR work in progress)

## Examples
TODO General example text

There's support for the following build tools:
* Maven
* Gradle
* Gradle Wrapper

It's mandatory for the `VisRecDeepNettsImpl` to install DeepNetts in your local maven repository which
can be done by cloning the [GitHub repository](https://github.com/sevarac/deepnetts) and `mvn install` in the `deepnetts-core/` directory of the project. 
For you to that, it's required to have maven installed on your machine.

Note: Some of the examples are using specific maven repositories for specific libraries. If you're
behind a cooperate firewall, some example implementations may not work.

### Maven
TODO Instructions here.

### Gradle
Gradle 4.7 has been actively used to test the examples. We recommend to use Gradle 4.7 or higher to
run the examples.

Go to directory of the example and perform the following command:   
> `gradle run`

OR remain in the root directory of the project and perform the following command:   
> `gradle :VisRecOpenImaj:run`

You can change `VisRecOpenImaj` into any of the other examples' directory.

### Gradle Wrapper
Gradle 4.7 Wrapper has been actively used to test the examples. We recommend to use Gradle 4.7 Wrapper or higher to
run the examples. Gradle Wrapper is included into the project.

Remain in the root directory of the project and perform the following command:   
> `gradlew :VisRecOpenImaj:run`

You can change `VisRecOpenImaj` into any of the other examples' directory.