package com.example.db2.scalikejdbc

import scalikejdbc._

object DB2Client {

  def main(args: Array[String]) {

    /*
     * connection properties may be provided on url,
     * for example to generate a trace, include trace properties on the url:
     *
     * val url = "jdbc:db2://localhost:50000/DBTEST:traceFile=cpds.txt;traceLevel=-1;"
     */
    val url = "jdbc:db2://localhost:50000/DBTEST"    
    
    val user = "db2admin"
    val password = "db2admin"
    
    
    // logging settings used by scalikejdbc
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = true, // set to false to disable logging output
      singleLineMode = true,
      printUnprocessedStackTrace = false,
      stackTraceDepth = 15,
      logLevel = 'debug, // change to 'info, 'warn, 'error or 'off
      warningEnabled = false,
      warningThresholdMillis = 3000L,
      warningLogLevel = 'warn)

    Class.forName("com.ibm.db2.jcc.DB2Driver")

    ConnectionPool.singleton(url, user, password) // builds connection pool

    // connection is implicitly borrowed from connection pool here
    // and closed (returned) where the DB { } block ends
    DB autoCommit { implicit session =>
      SQL("create table emp (id integer not null primary key, name varchar(30))").executeUpdate.apply()
      SQL("insert into emp (id, name) values (?, ?)").bind(1, "name1").update.apply()
      SQL("insert into emp (id, name) values (?, ?)").bind(2, "name2").update.apply()
    }
    
    DB autoCommit { implicit session =>
      sql"select * from emp".foreach { rs =>
        println("Emp("+rs.int("id")+","+rs.string("name")+")")
      }
    }
  }
}
