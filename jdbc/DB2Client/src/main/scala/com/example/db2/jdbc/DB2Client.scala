package com.example.db2.jdbc

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
    
    val conn: java.sql.Connection = connectURL()
   
    println("obtained connection ")
    
    try {
      var st = conn.createStatement()
      st.executeUpdate("create table emp (id integer not null primary key, name varchar(30))")
      st.close
      
      var pst = conn.prepareStatement("insert into emp (id, name) values (?, ?)")
      pst.setInt (1, 1)
      pst.setString (2, "name1")      
      pst.executeUpdate()
      
      pst.clearParameters()
      pst.setInt (1, 2)
      pst.setString (2, "name2")      
      pst.executeUpdate()
      
      pst.close

      st = conn.createStatement()
      var rs = st.executeQuery ("select * from emp")
      
      while (rs.next()) {
        println("Emp("+rs.getInt(1)+","+rs.getString(2)+")")
      }
      
      rs.close()
      st.close()
    
    } catch {
      case ex : java.sql.SQLException => println("SQLException: "+ex)
    } finally {
       conn.close()
    }
  }
}