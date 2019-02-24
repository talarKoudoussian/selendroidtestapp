# selendroid-test-app

This repo includes QA tests run on the `selendroid-test-app` application. The tests are written in `Java` using `TestNG` and `Selenium`.

It is a `Maven` project, so all the dependencies can be found in the `pom.xml` file.

## Set up the environment

You'll need to install:

- Java JDK
- Android Studio
- Android emulator (Nexus 5, API 28)
- JAVA IDE (Eclipse, IntelliJ, or any other IDE you are comfortable with)
- [Appium](https://github.com/appium/appium-desktop/releases/tag/v1.10.0)

## Run the tests

### First, Run Appium

1. Start Appium
1. Click on `start session`
1. Start your Android emulator

### Open the project

1. Start your Java IDE
1. Open this project
1. Edit the path to the `selendroid-test-app` `apk` file in `/src/test/java/SelendroidTest.java`

  ![apk_path_edit](https://user-images.githubusercontent.com/38904721/53296366-9b73bc00-3816-11e9-8d8a-50c5d289b187.PNG)

### Check the test results

You can run the tests either manually or using the command line with the `mvn` command.

- manually

 1. Right click on `SelendroidTest` in `/src/test/java/SelendroidTest.java`
 1. Click on `Run SelendroidTest`
 1. The automated tests should start on the emulator
 1. In your IDE console you can see the tests passing

 ![image](https://user-images.githubusercontent.com/38904721/53296413-471d0c00-3817-11e9-8c91-6ff339699a03.png)

- using command line `mvn test`

 1. First, you'll need to install [Maven](https://maven.apache.org/download.cgi)
 1. Modify your environment variables by following these [instructions](https://maven.apache.org/install.html)
 1. Check if `Maven` is working by running `mvn -v`
 1. Open `Powershell`
 1. Run `cd /path/to/this/repo`
 1. Run `mvn test`

  ![image](https://user-images.githubusercontent.com/38904721/53296434-a549ef00-3817-11e9-9843-b4f3e5388765.png)

## Test Cases

This project include 5 test cases:

|Test Case| Description|
|------------|-----------|
|Display update popup|Checks if popup title and description are written correctly and clicks `ok`|
|Display text|Clicks on `Display Text View` Button and checks if expected text is displayed|
|Display text and focus on input|Clicks on `Display and focus on layout` button and checks if expected text is displayed and the input is focused|
|Display toast|Clicks on `Displays a Toast` Button and checks if expected toast text is displayed|
|Register user|Clicks on the folder Button, inserts all the fields with data read from the excel sheet in `/src/test/java/resources/testData.xlsx`, clicks on `Register User`, checks if the displayed data on the view are the ones inserted by the user and clicks on `Register`|
