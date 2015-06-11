# Work with DB2 using Scala
This project contains Sample Scala applications to demonstrate the use of IBM Data Server Driver for JDBC and SQLJ within Scala applications to work with IBM DB2. 

The IBM Data Server Driver for JDBC and SQLJ is a pure Java driver that can connect to IBM DB2 running on any platform - Linux, Unix, Windows, z/OS or iSeries. A Scala application can also therefore work with DB2 on any of these platforms using this driver.

Three sample applications, using three different approaches are included:

* `anorm/DB2Client`: A DB2 Client application implemented using the [Anorm](https://www.playframework.com/documentation/2.1.0/ScalaAnorm) data access library
* `jdbc/DB2Client`: A DB2 Client application implemented using direct consumption of standard JDBC API within Scala
* `scalikejdbc/DB2Client`: A DB2 Client application implemented using the [scalikejdbc](http://scalikejdbc.org/) library

## Sample application requirements

* [Scala language](http://www.scala-lang.org/) version 2.11.6
* Scala *Simple Build Tool* [sbt](http://www.scala-sbt.org/) version 0.13.8
* IBM Data Server Driver for JDBC and SQLJ (any version)

## Sample application structure

The sample applications are structured based on the the [sbt](http://www.scala-sbt.org/) project structure. There are three applications, each rooted in its respective `DB2Client/` directory. These are: `anorm/DB2Client/`, `jdbc/DB2Client/` and `scalikejdbc/DB2Client/`. 

## The IBM Data Server Driver for JDBC and SQLJ

The IBM Data Server Driver for JDBC and SQLJ comes packaged as a single jar file: db2jcc.jar for JDBC 3.0 and db2jcc4.jar for JDBC 4.0. To run the sample applications you need to place the driver jar in the `lib/` directory for each `DB2Client/` application. 

You can obtain the driver jar file from an existing DB2 installation, for example on a DB2 for Linux, Unix and Windows it is present in `sqllib/java` directory. Alternately one can obtain the driver from the IBM Data Server Driver for JDBC and SQLJ (JCC Driver) download location provided  [here](http://www-01.ibm.com/software/data/db2/linux-unix-windows/downloads.html).

To run the applications issue:
    
* application with Anorm implementation

```sh
    $ chdir db2scala/anorm/DB2Client
    $ sbt run
```    

* application with JDBC implementation

```sh
    $ chdir db2scala/jdbc/DB2Client
    $ sbt run
```     

* application with scalikejdbc implementation 

```sh
    $ chdir db2scala/scalikejdbc/DB2Client
    $ sbt run
``` 

    

