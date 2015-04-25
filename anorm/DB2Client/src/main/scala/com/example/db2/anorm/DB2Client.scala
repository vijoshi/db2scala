package com.example.db2.anorm

import anorm._

object DB2Client {

  def connectURL(): java.sql.Connection = {

    /*
     * connection properties may be provided on url,
     * for example to generate a trace, include trace properties on the url:
     *
     * val url = "jdbc:db2://localhost:50000/DBTEST:traceFile=cpds.txt;traceLevel=-1;"
     */
    val url = "jdbc:db2://localhost:50000/dbtest"

    val driver = "com.ibm.db2.jcc.DB2Driver"
    val username = "db2admin"
    val password = "db2admin"

    // acquire the connection
    Class.forName(driver)
    var connection = java.sql.DriverManager.getConnection(url, username, password)

    connection
  }

  def main(args: Array[String]) {

    implicit val conn: java.sql.Connection = connectURL()

    try {
      println("obtained connection " + conn)

      SQL("create table emp (id integer not null primary key, name varchar(30))").execute()

      SQL("insert into emp (id, name) values ({id}, {name})")
        .on("id" -> 1, "name" -> "name1").executeUpdate()
      SQL("insert into emp (id, name) values ({id}, {name})")
        .on("id" -> 2, "name" -> "name2").executeUpdate()

      val selectAll: SqlQuery = SQL("select * from emp")
      selectAll() foreach println

      // below scala code maps row data to a case class
      case class Emp(id: Int, name: String)

      val res: Stream[Emp] = SQL("select id,name from emp")().collect {
        case Row(id: Int, Some(name: String)) => Emp(id, name)
      }
      res foreach println
      
    } finally {
      conn.close()
    }
  }
}