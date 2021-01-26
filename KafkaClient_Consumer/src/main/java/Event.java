import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Event implements Runnable{
    private String topic, key, value,ipadress="localhost";
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public void send(){
        run();
    }
    @Override
    public void run() {
        try {
            Properties props = new Properties();
            props.put("bootstrap.servers", ipadress+":9092");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("linger.ms", 1);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            Producer<String, String> producer = new KafkaProducer<>(props);
            producer.send(new ProducerRecord<String, String>(topic, "key", value));
            producer.flush();
            producer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
