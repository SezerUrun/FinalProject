
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Events{/*
    KafkaClient kafkaClient;
    ListView listView_events;
    Database database;
    ArrayList<String> arrayList_events;
    int topicName;
    String userName;
    Button button_abonelikler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        userName=getIntent().getStringExtra("userName");
        kafkaClient=new KafkaClient(userName);
        arrayList_events=kafkaClient.getEvents();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrayList_events);
        database=new Database();
        button_abonelikler=findViewById(R.id.button_abonelikler);
        listView_events=findViewById(R.id.listView_events);
        listView_events.setAdapter(arrayAdapter);
        button_abonelikler.setOnClickListener(this);
        listView_events.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        if(viewId==button_abonelikler.getId()){
            Intent intent=new Intent(Events.this,Abonelikler.class);
            intent.putExtra("userName",userName);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(Events.this,arrayList_events.get(position),Toast.LENGTH_LONG).show();
    }*/
}