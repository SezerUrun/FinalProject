import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaClient{
    private static ArrayList<String> arrayList_events;
    private static Properties props;
    private static KafkaConsumer<String, String> consumer;
    private static String ipAddress="localhost";
    private static String userName;
    private final AtomicBoolean closed = new AtomicBoolean(false);

    KafkaClient(String userName){
        arrayList_events=new ArrayList<>();
        props = new Properties();
        props.setProperty("bootstrap.servers", ipAddress+":9092");
        props.setProperty("group.id", userName);
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("auto.offset.reset","latest");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
    }

    public static ArrayList<String> getArrayList_events() {
        return arrayList_events;
    }

    public static void setArrayList_events(ArrayList<String> arrayList_events) {
        KafkaClient.arrayList_events = arrayList_events;
    }

    public static Properties getProps() {
        return props;
    }

    public static void setProps(Properties props) {
        KafkaClient.props = props;
    }

    public static KafkaConsumer<String, String> getConsumer() {
        return consumer;
    }

    public static void setConsumer(KafkaConsumer<String, String> consumer) {
        KafkaClient.consumer = consumer;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        KafkaClient.userName = userName;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void subscribe(List<String> subscriptions){
        this.consumer.subscribe(subscriptions);
    }

    public ArrayList<String> getEvents(){
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        for (ConsumerRecord<String, String> record : records){
            record.timestamp();
            arrayList_events.add("("+record.topic()+") "+record.value());
        }
        return arrayList_events;
    }
}
