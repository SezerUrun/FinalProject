import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.*;
import java.sql.*;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.*;

public class KafkaClient_Consumer{
    static ArrayList<String> arrayList_events,arrayList_topics;
    static String topic="",eventText="",userName,password;
    static JList list_events;
    static Database database;
    static int topicId,lastOffset,userId;
    static Event event;
    static KafkaClient kafkaClient;
    static boolean signedIn=false;
    static int lastOffset_bilim,lastOffset_ekonomi,lastOffset_siyaset,lastOffset_spor,lastOffset_teknoloji;
    public static void main(String[] args) {
        arrayList_events=new ArrayList<>();
        arrayList_topics=new ArrayList<>();
        database=new Database();
        //database.setName("FinalProject");
        JFrame frame = new JFrame("Kafka Consumer");
        frame.setSize(800,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        JMenu m1 = new JMenu("Dosya");
        JMenu m2 = new JMenu("Yardim");
        menuBar.add(m1);
        menuBar.add(m2);
        JMenuItem m11 = new JMenuItem("Kaydet");
        JMenuItem m22 = new JMenuItem("Hakkinda");
        m1.add(m11);
        m1.add(m22);
        JPanel panel_bottom = new JPanel();
        JPanel panel_middle1=new JPanel();
        JPanel panel_middle2=new JPanel();
        JPanel panel_middle3=new JPanel();
        JPanel panel_middle4=new JPanel();
        JTextArea textArea_events=new JTextArea();
        textArea_events.setDisabledTextColor(Color.black);
        JScrollPane scrollPane = new JScrollPane (textArea_events,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea_events.setLineWrap(true);
        textArea_events.setMargin(new Insets(10,10,10,10));
        textArea_events.setEnabled(false);
        JLabel label = new JLabel("Topic : ");
        JCheckBox checkBox_bilim=new JCheckBox("Bilim");
        JCheckBox checkBox_ekonomi=new JCheckBox("Ekonomi");
        JCheckBox checkBox_siyaset=new JCheckBox("Siyaset");
        JCheckBox checkBox_spor=new JCheckBox("Spor");
        JCheckBox checkBox_teknoloji=new JCheckBox("Teknoloji");
        checkBox_bilim.setEnabled(false);
        checkBox_siyaset.setEnabled(false);
        checkBox_spor.setEnabled(false);
        checkBox_ekonomi.setEnabled(false);
        checkBox_teknoloji.setEnabled(false);
        JLabel label_userName=new JLabel("Kullanici Adi");
        JLabel label_password=new JLabel("Sifre");
        JTextField textField_userName = new JTextField();
        //textField_userName.setMargin(new Insets(10,10,10,10));
        textField_userName.setColumns(10);
        JPasswordField textField_password = new JPasswordField();
        textField_password.setColumns(10);
        //textField_password.setMargin(new Insets(10,10,10,10));
        list_events=new JList<>();
        JButton button_getEvents = new JButton("Olayları Yenile");
        JButton button_renewSubscriptions = new JButton("Abonelikleri Guncelle");
        button_getEvents.setEnabled(false);
        button_renewSubscriptions.setEnabled(false);
        JButton button_signIn = new JButton("Giris Yap");
        JButton button_signUp = new JButton("Kayıt Ol");
        JButton button_markAsRead=new JButton("Hepsini okundu olarak isaretle");
        button_markAsRead.setEnabled(false);
        button_markAsRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*database.markAsReaded(userId,1,lastOffset_bilim);
                database.markAsReaded(userId,2,lastOffset_ekonomi);
                database.markAsReaded(userId,3,lastOffset_siyaset);
                database.markAsReaded(userId,4,lastOffset_spor);
                database.markAsReaded(userId,5,lastOffset_teknoloji);*/
            }
        });
        button_signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName=textField_userName.getText();
                password=textField_password.getText();
                if(database.getUserId(userName)!=-1){
                    JDialog dialog = new JDialog();
                    dialog.add(new JLabel("Kullanici mevcut"));
                    dialog.setSize(frame.getWidth()/2,frame.getHeight()/2);
                    dialog.setLocation(frame.getX() + frame.getWidth() / 4, frame.getY() + frame.getHeight() / 4);
                    dialog.setVisible(true);
                }
                else {
                    database.newUser(userName, password);
                    JDialog dialog = new JDialog();
                    dialog.add(new JLabel("Kullanici olusturuldu"));
                    dialog.setSize(frame.getWidth()/2,frame.getHeight()/2);
                    dialog.setLocation(frame.getX() + frame.getWidth() / 4, frame.getY() + frame.getHeight() / 4);
                    dialog.setVisible(true);
                }
            }
        });
        button_signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                userName=textField_userName.getText();
                password=textField_password.getText();
                if(database.checkUserNameAndPassword(userName,password)){
                    signedIn=true;
                    kafkaClient=new KafkaClient(userName);
                    userId=database.getUserId(userName);
                    arrayList_topics=database.getSubcriptions(userName);
                    kafkaClient.subscribe(arrayList_topics);
                    button_getEvents.setEnabled(true);
                    button_renewSubscriptions.setEnabled(true);
                    button_markAsRead.setEnabled(true);
                    checkBox_bilim.setEnabled(true);
                    checkBox_siyaset.setEnabled(true);
                    checkBox_spor.setEnabled(true);
                    checkBox_ekonomi.setEnabled(true);
                    checkBox_teknoloji.setEnabled(true);
                    if (arrayList_topics.contains("bilim")){
                        checkBox_bilim.setSelected(true);
                    }
                    if (arrayList_topics.contains("ekonomi")){
                        checkBox_ekonomi.setSelected(true);
                    }
                    if (arrayList_topics.contains("siyaset")){
                        checkBox_siyaset.setSelected(true);
                    }
                    if (arrayList_topics.contains("spor")){
                        checkBox_spor.setSelected(true);
                    }
                    if (arrayList_topics.contains("teknoloji")){
                        checkBox_teknoloji.setSelected(true);
                    }
                    JDialog dialog=new JDialog();
                    dialog.add(new JLabel("Giris basarili"));
                    dialog.setSize(frame.getWidth()/2,frame.getHeight()/2);
                    dialog.setLocation(frame.getX()+frame.getWidth()/4,frame.getY()+frame.getHeight()/4);
                    dialog.setVisible(true);
                    userName=textField_userName.getText();
                    password=textField_password.getText();
                    arrayList_events=kafkaClient.getEvents();
                    textArea_events.setText("");
                    for (int i=0;i<arrayList_events.size();i++){
                        textArea_events.append(arrayList_events.get(i)+"\n\n");
                    }
                }
                else{
                    JDialog dialog=new JDialog();
                    dialog.add(new JLabel("Kullanici adi veya sifre yanlis"));
                    dialog.setSize(frame.getWidth()/2,frame.getHeight()/2);
                    dialog.setLocation(frame.getX()+frame.getWidth()/4,frame.getY()+frame.getHeight()/4);
                    dialog.setVisible(true);
                }
            }
        });
        button_getEvents.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                userName=textField_userName.getText();
                password=textField_password.getText();
                arrayList_events=kafkaClient.getEvents();
                textArea_events.setText("");
                for (int i=0;i<arrayList_events.size();i++){
                    textArea_events.append(arrayList_events.get(i)+"\n\n");
                }
            }
        });
        button_renewSubscriptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox_bilim.isSelected()) {
                    if (!arrayList_topics.contains("bilim")) {
                        arrayList_topics.add("bilim");
                    }
                } else {
                    if (arrayList_topics.contains("bilim")) {
                        arrayList_topics.remove("bilim");
                    }
                }
                if (checkBox_ekonomi.isSelected()) {
                    if (!arrayList_topics.contains("ekonomi")) {
                        arrayList_topics.add("ekonomi");
                    }
                } else {
                    if (arrayList_topics.contains("ekonomi")) {
                        arrayList_topics.remove("ekonomi");
                    }
                }
                if (checkBox_siyaset.isSelected()) {
                    if (!arrayList_topics.contains("siyaset")) {
                        arrayList_topics.add("siyaset");
                    }
                } else {
                    if (arrayList_topics.contains("siyaset")) {
                        arrayList_topics.remove("siyaset");
                    }
                }
                if (checkBox_spor.isSelected()) {
                    if (!arrayList_topics.contains("spor")) {
                        arrayList_topics.add("spor");
                    }
                } else {
                    if (arrayList_topics.contains("spor")) {
                        arrayList_topics.remove("spor");
                    }
                }
                if (checkBox_teknoloji.isSelected()) {
                    if (!arrayList_topics.contains("teknoloji")) {
                        arrayList_topics.add("teknoloji");
                    }
                } else {
                    if (arrayList_topics.contains("teknoloji")) {
                        arrayList_topics.remove("teknoloji");
                    }
                }
                database.deleteSubscriptions(userId);
                for(int i=0;i<arrayList_topics.size();i++){
                    String topicName=arrayList_topics.get(i);
                    if(topicName.equals("bilim")) {
                        topicId=1;
                    }
                    if(topicName.equals("ekonomi")) {
                        topicId=2;
                    }
                    if(topicName.equals("siyaset")) {
                        topicId=3;
                    }
                    if(topicName.equals("spor")) {
                        topicId=4;
                    }
                    if(topicName.equals("teknoloji")) {
                        topicId=5;
                    }
                    database.newSubscription(userId,topicId);
                }
                kafkaClient.subscribe(arrayList_topics);
                JDialog dialog=new JDialog();
                dialog.add(new JLabel("Abonelikler guncellendi"));
                dialog.setSize(frame.getWidth()/2,frame.getHeight()/2);
                dialog.setLocation(frame.getX()+frame.getWidth()/4,frame.getY()+frame.getHeight()/4);
                dialog.setVisible(true);
            }
        });
        panel_middle1.add(label_userName);
        panel_middle1.add(textField_userName);
        panel_middle1.add(label_password);
        panel_middle1.add(textField_password);
        panel_middle1.add(button_signUp);
        panel_middle1.add(button_signIn);
        //panel_middle2.add(textArea_events);
        panel_bottom.add(label);
        panel_bottom.add(checkBox_bilim);
        panel_bottom.add(checkBox_ekonomi);
        panel_bottom.add(checkBox_siyaset);
        panel_bottom.add(checkBox_spor);
        panel_bottom.add(checkBox_teknoloji);
        panel_bottom.add(button_getEvents);
        panel_bottom.add(button_renewSubscriptions);
        panel_bottom.add(button_markAsRead);
        //frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.NORTH, panel_middle1);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.getContentPane().add(BorderLayout.SOUTH, panel_bottom);
        //frame.getContentPane().add(BorderLayout.CENTER, textArea);
        //frame.getContentPane().add(BorderLayout.CENTER, list_events);
        frame.setVisible(true);

    }


}
