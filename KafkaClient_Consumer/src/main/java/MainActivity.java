
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;

import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;


public class MainActivity {/*
    EditText editText_userName,getEditText_password;
    Button button_signIn,button_signUp;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=new Database();
        editText_userName=findViewById(R.id.editText_userName);
        getEditText_password=findViewById(R.id.editText_password);
        button_signIn=findViewById(R.id.button_signIn);
        button_signUp=findViewById(R.id.button_signUp);
        button_signUp.setOnClickListener(this);
        button_signIn.setOnClickListener(this);

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("bilim", "ekonomi"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }

    }

    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        Intent intent;
        String userName=editText_userName.getText().toString();
        String password=getEditText_password.getText().toString();
        if(viewId==button_signIn.getId()){
            if (database.checkUserNameAndPassword(userName,password)){
                intent=new Intent(this,Events.class);
                intent.putExtra("userName",userName);
                intent.putExtra("password",password);
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this,"Kullanici adi veya sifre yanlis",Toast.LENGTH_SHORT).show();
            }

        }
        else if(viewId==button_signUp.getId()){
            if (userName.length()>3&&password.length()>3){
                database.newUser(userName,password);
                Toast.makeText(MainActivity.this,"Kullanici olusturuldu",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this,"Kullanici adi ve sifreniz en az 4 karakter olmalı",Toast.LENGTH_SHORT).show();
            }

        }
    }*/
}
