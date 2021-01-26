import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database implements Runnable{
    static String databaseName="",event="";
    static int topicId=0;

    String getDatabaseName(){
        return this.databaseName;
    }
    void setDatabaseName(String databaseName){
        this.databaseName=databaseName;
    }
    void sendEvent(int topicId, String event){
        this.topicId=topicId;
        this.event=event;
        run();
    }
    @Override
    public void run() {
        try{
            //Class.forName("com.mysql.jdbc.Driver"); ?useSSL=false
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject","admin","password");
            Statement stmt=con.createStatement();
            stmt.execute("insert into events values(0,"+this.topicId+",'"+this.event+"')");
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

