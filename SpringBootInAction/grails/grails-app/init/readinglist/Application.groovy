package readinglist

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
}
// chapter 6 在 Spring Boot 中使用 Grails
// 6.3 结合 Spring Boot 与 Grails 3
// grails create-app readinglist
// grails run-app
// gradle bootRun
// gradle build
// grails create-domain-class Book
// grails create-controller ReadingList