This is a project of combing both Java and MariaDB. In this project, 
me and Maciej Baginski created Java Swing application to perform operations
on the database called "Zakupy"(Shopping). Some methods and even classes ar called in Polish.
This is because of usage of business schemes of Polish online shops.<br /> 
In this application, users should use their logins and passwords. Thanks to connecting
to database, there is a procedure of checking up of the correctness of both login
and password. If these parameters are correct, the next step is connection to database
and modifying it. In other case, user should try his login and password more times.<br />
All the processes are provided with JDBC. Ad additional possibility, there is a table of MD5
passwords which also is used to check up the correctness of given parameters. In MariaDB
we have REGEX checks. <br /> 
In the case all parameters are okay, then every user has its own possibilities for
modifying the database. For example, just Admin has possibility to delete data from tables.
On the other hand, counter cannot do anything like this.<br /> 
For better understanding, there is documentation of this application, instruction for user and details about it.
![alt text](https://github.com/Foxxich/dataBaseImproves/blob/master/src/Screens/Application.png)

  