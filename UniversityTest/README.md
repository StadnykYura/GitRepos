# Local Console program "University"

University consist of departments and lectors. The lectors could work in more than one department. A lector could have one of degree (assistant,associate professor,professor).
All data is stored in relational database.

The application is developed using JPA/Hibernate core, mysql-connector-java.

# Build and run
1. Check out the project source code from github : git clone 
2. Run mysql server on your local computer with standart configuration
ip address=127.0.0.1
port=3306. Username and password can be set by your own(but also you have to set them in configuration file in the project. it would be explained later).
3. With the help of "MySql Workbench vers. 6.3" + connect to this server and create schema "universitydb";
4. Import project from your local repository to any IDE(Eclipse, IntelliJ IDEA, etc.) as maven project for convinience;
5. find "hibernate.properties" file inside project and set your "username", "password" to your local mysql server configuration;
6. find .java class "Main" and run this class with the help of any IDE;
7. If everything is ok, you will see "University created successfully" message in console with another explanation.
8. Choose your number!

P.S: if you faced with java.lang.ClassNotFoundException: com.mysql.jdbc.Driver during runnig, try this:
Add the jar library manually to the project.
Right Click the project -- > build path -- > configure build path
In Libraries Tab press Add External Jar and Select your jar from your local.
Previously you should download a jar file or take it from your m2.repository


