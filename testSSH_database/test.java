import com.jcraft.jsch.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

    public static void main(String[] args) {
        final String dbUsername = "root";
	    final String dbPassword = "Ming@12321";
	    final String dbUrl = "sshop.tplinkdns.com:8806";
        

        try {
            JSch jSch = new JSch();
            Session session = jSch.getSession("ivetm", "sshop.tplinkdns.com", 3322);
            session.setPassword("ilovetmit@123");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println("session connected");

            int external_port = 8806;
            int internal_port = 3306;
            String internal_host = "localhost";

            int assinged_port=session.setPortForwardingL(external_port, internal_host, internal_port);
            System.out.println("localhost:"+assinged_port+" -> "+internal_host+":"+internal_port);

            System.out.println("Start to connect database");

            Connection myConn = null;
            //String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://" + dbUrl + "/";
            String db = "testing";

            //Class.forName(driver).newInstance();
            
            myConn = DriverManager.getConnection(url+db, dbUsername, dbPassword);
            System.out.println("database connected");

            myConn.close();
            
            session.disconnect();
            System.out.println("bye");

        } catch ( Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
}