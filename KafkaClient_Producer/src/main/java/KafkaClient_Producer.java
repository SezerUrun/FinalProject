import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.sql.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.*;

public class KafkaClient_Producer{
	static ArrayList<String> arrayList_events=new ArrayList<>();
	static String topic="",eventText="";
	static JList list_events;
	static Database database;
	static int topicId;
	static Event event;
	public static void main(String[] args) {
		database=new Database();
		//database.setName("FinalProject");
		JFrame frame = new JFrame("Kafka Producer");
		frame.setSize(800,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label_header=new JLabel("Olay Metni");
		//Creating the panel at bottom and adding components
		JPanel panel_bottom = new JPanel();
		JPanel panel_middle=new JPanel();
		JPanel panel_header=new JPanel();
		JLabel label = new JLabel("Topic : ");
		//JTextField textField = new JTextField(10); // accepts upto 10 characters
		JRadioButton radioButton_bilim=new JRadioButton("Bilim");
		JRadioButton radioButton_ekonomi=new JRadioButton("Ekonomi");
		JRadioButton radioButton_siyaset=new JRadioButton("Siyaset");
		JRadioButton radioButton_spor=new JRadioButton("Spor");
		JRadioButton radioButton_teknoloji=new JRadioButton("Teknoloji");
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton_bilim);
		group.add(radioButton_ekonomi);
		group.add(radioButton_siyaset);
		group.add(radioButton_spor);
		group.add(radioButton_teknoloji);
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setMargin(new Insets(10,10,10,10));
		//textArea.setSize(400,400);
		list_events=new JList<>();
		//list_events.setSize(400,400);
		JButton buttonSend = new JButton("Gonder");
		buttonSend.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				eventText = textArea.getText();
				if(radioButton_bilim.isSelected()){
					topic="bilim";
					topicId=1;
				}
				else if(radioButton_ekonomi.isSelected()){
					topic="ekonomi";
					topicId=2;
				}
				else if(radioButton_siyaset.isSelected()){
					topic="siyaset";
					topicId=3;
				}
				else if(radioButton_spor.isSelected()){
					topic="spor";
					topicId=4;
				}
				else if(radioButton_teknoloji.isSelected()){
					topic="teknoloji";
					topicId=5;
				}
				if(topic.length()!=0 && eventText.length()!=0){
					event=new Event();
					event.setTopic(topic);
					event.setValue(eventText);
					arrayList_events.add(topic+" - "+event);
					String[] array_events=new String[arrayList_events.size()];
					for(int i=0;i<arrayList_events.size();i++){
						array_events[i]= arrayList_events.get(i);
					}
					list_events=new JList<String>( array_events);
					event.send();
					database.sendEvent(topicId,eventText);
					JDialog dialog=new JDialog();
					dialog.add(new JLabel("Mesaj Gönderildi"));
					dialog.setSize(400,200);
					dialog.setLocation(frame.getX()+frame.getWidth()/4,frame.getY()+frame.getHeight()/4);
					dialog.setVisible(true);
				}
				else{
					JDialog dialog=new JDialog();
					dialog.add(new JLabel("Lutfen bir konu secin ve olay metni girin"));
					dialog.setSize(400,200);
					dialog.setLocation(frame.getX()+frame.getWidth()/4,frame.getY()+frame.getHeight()/4);
					dialog.setVisible(true);
				}
				textArea.setText("");
				topic="";
				eventText="";
				group.clearSelection();
			}
		});
		panel_middle.add(textArea);
		panel_middle.add(list_events);
		panel_bottom.add(label);
		panel_bottom.add(radioButton_bilim);
		panel_bottom.add(radioButton_ekonomi);
		panel_bottom.add(radioButton_siyaset);
		panel_bottom.add(radioButton_spor);
		panel_bottom.add(radioButton_teknoloji);
		panel_bottom.add(buttonSend);
		panel_header.add(label_header);
		frame.getContentPane().add(BorderLayout.NORTH, panel_header);
		frame.getContentPane().add(BorderLayout.CENTER, textArea);
		//frame.getContentPane().add(BorderLayout.CENTER, list_events);
		frame.getContentPane().add(BorderLayout.SOUTH, panel_bottom);
		//frame.getContentPane().add(BorderLayout.CENTER, textArea);
		//frame.getContentPane().add(BorderLayout.CENTER, list_events);
		frame.setVisible(true);

	}


}
