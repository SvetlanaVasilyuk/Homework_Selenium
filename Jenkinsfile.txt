pipeline {
   agent any
   tools {
        maven 'Maven 3.6.3'
        jdk 'JavaDK'
    }
   stages {
      stage('Git') {
         steps {
            git branch: 'cucumber_homework_29_30', url: 'https://github.com/SvetlanaVasilyuk/Homework_Selenium.git'
         }
      }
      stage('Test') {
         steps {
            bat label: '', script: 'mvn clean test'
         }
      }
   }
   post {
      always{
         allure includeProperties: false, jdk: 'JavaDK', results: [[path: 'target/allure-results']]
      }
   }
}