
# Introduction

This project demonstrates the Google+ Sign-In on Java Spring and Angular.

# Configuration and Build

Copy `ConstantsTemplate.java` to `Constants.java`. Visit the [Google Developers Consoler](https://console.developers.google.com) to create your own credentials, and fill in your own details.

Copy `application.properties.template` to `application.properties`. Set port and SSL properties. The default port is 8081.

Generate `keystore.jks` to the root directory of the project.

Sorry, cannot cover the SSL stuff here in more detail. 

Build with Maven.

# Execute

[http://localhost:8081/](http://localhost:8081/)

# Version History

 * 2.1: Remove application.properties so that it needs to be recreated based on template.
 * 2.0: SSL property handling added.
 * 1.0: The initial version.
 
