import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Database implements Runnable{
    static String databaseName="finalproject",topic="",ipAdress="localhost",userName_database="admin",password_database="password";
    static int topicId=0;
    String getDatabaseName(){
        return this.databaseName;
    }
    void setDatabaseName(String databaseName){
        this.databaseName=databaseName;
    }

    public static String getTopic() {
        return topic;
    }

    public static void setTopic(String topic) {
        Database.topic = topic;
    }

    public static String getIpAdress() {
        return ipAdress;
    }

    public static void setIpAdress(String ipAdress) {
        Database.ipAdress = ipAdress;
    }

    public static int getTopicId() {
        return topicId;
    }

    public static void setTopicId(int topicId) {
        Database.topicId = topicId;
    }

    public boolean checkUserNameAndPassword(String userName,String password){
        boolean matches=false;
        try{
            //Class.forName("com.mysql.jdbc.Driver"); ?useSSL=false
            Connection con=DriverManager.getConnection("jdbc:mysql://"+ipAdress+":3306/"+databaseName,userName_database,password_database);
            Statement stmt=con.createStatement();
            ResultSet resultSet=stmt.executeQuery("select password from users where username='"+userName+"';");
            if (resultSet.next()){
                if(resultSet.getString("password").equals(password)) {
                    matches = true;
                }
            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return matches;
    }

    public int getUserId(String userName){
        int userId=-1;
        try{
            //Class.forName("com.mysql.jdbc.Driver"); ?useSSL=false
            Connection con=DriverManager.getConnection("jdbc:mysql://"+ipAdress+":3306/"+databaseName,userName_database,password_database);
            Statement stmt=con.createStatement();
            ResultSet resultSet=stmt.executeQuery("select id from users where userName='"+userName+"'");
            if (resultSet.next()){
                userId=resultSet.getInt("id");
            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return userId;
    }

    public ArrayList<String> getSubcriptions(String userName){
        ArrayList<String> arrayList_subscriptions=new ArrayList<>();
        try{
            //Class.forName("com.mysql.jdbc.Driver"); ?useSSL=false
            Connection con=DriverManager.getConnection("jdbc:mysql://"+ipAdress+":3306/"+databaseName,userName_database,password_database);
            Statement stmt=con.createStatement();
            ResultSet resultSet=stmt.executeQuery("select * from subscriptions where subscriberId="+getUserId(userName));
            while (resultSet.next()){
                int topicId=resultSet.getInt(3);
                if (topicId==1){
                    arrayList_subscriptions.add("bilim");
                }
                else if (topicId==2){
                    arrayList_subscriptions.add("ekonomi");
                }
                else if (topicId==3){
                    arrayList_subscriptions.add("siyaset");
                }
                else if (topicId==4){
                    arrayList_subscriptions.add("spor");
                }
                else if (topicId==5){
                    arrayList_subscriptions.add("teknoloji");
                }
            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return arrayList_subscriptions;
    }
    public void newUser(String userName, String password){
        try{
            //Class.forName("com.mysql.jdbc.Driver"); ?useSSL=false
            Connection con=DriverManager.getConnection("jdbc:mysql://"+ipAdress+":3306/"+databaseName,userName_database,password_database);
            Statement stmt=con.createStatement();
            stmt.execute("insert into users values(0,'"+userName+"','"+password+"')");
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void newSubscription(int userId,int topicId){
        try{
            //Class.forName("com.mysql.jdbc.Driver"); ?useSSL=false
            Connection con=DriverManager.getConnection("jdbc:mysql://"+ipAdress+":3306/"+databaseName,userName_database,password_database);
            Statement stmt=con.createStatement();
            stmt.execute("insert into subscriptions values(0,"+userId+","+topicId+",0)");
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteSubscriptions(int userId){
        try{
            //Class.forName("com.mysql.jdbc.Driver"); ?useSSL=false
            Connection con=DriverManager.getConnection("jdbc:mysql://"+ipAdress+":3306/"+databaseName,userName_database,password_database);
            Statement stmt=con.createStatement();
            stmt.execute("delete from subscriptions where subscriberId="+userId);
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void markAsReaded(int userId,int topicId,int offsetNumber){
        try{
            //Class.forName("com.mysql.jdbc.Driver"); ?useSSL=false
            Connection con=DriverManager.getConnection("jdbc:mysql://"+ipAdress+":3306/"+databaseName,userName_database,password_database);
            Statement stmt=con.createStatement();
            stmt.execute("update subcriptions set lastOffset="+offsetNumber+" where subscriberId="+userId+" and topicId="+topicId);
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}

