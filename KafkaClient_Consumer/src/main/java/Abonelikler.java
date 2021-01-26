import java.util.ArrayList;

public class Abonelikler{/*
    ListView listView_subscriptions,listView_nonsucscriptions;
    Database database;
    String userName;
    ArrayList<String> arrayList_subscriptions,arrayList_nonsubscriptions;
    int userId,topicId;
    KafkaClient kafkaClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abonelikler);

        listView_subscriptions=findViewById(R.id.listView_subscriptions);
        listView_nonsucscriptions=findViewById(R.id.listView_nonsubcriptions);

        userName=getIntent().getStringExtra("userName");
        kafkaClient=new KafkaClient(userName);
        database=new Database();
        //userId=getIntent().getIntExtra("userId",-1);
        userId=database.getUserId(userName);
        arrayList_subscriptions=database.getSubcriptions(userName);
        if(!arrayList_subscriptions.contains("bilim")){
            arrayList_nonsubscriptions.add("bilim");
        }
        if(!arrayList_subscriptions.contains("ekonomi")){
            arrayList_nonsubscriptions.add("ekonomi");
        }
        if(!arrayList_subscriptions.contains("siyaset")){
            arrayList_nonsubscriptions.add("siyaset");
        }
        if(!arrayList_subscriptions.contains("spor")){
            arrayList_nonsubscriptions.add("spor");
        }
        if(!arrayList_subscriptions.contains("teknoloji")){
            arrayList_nonsubscriptions.add("teknoloji");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrayList_subscriptions);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrayList_nonsubscriptions);
        listView_subscriptions.setAdapter(arrayAdapter);
        listView_nonsucscriptions.setAdapter(arrayAdapter2);
        listView_subscriptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String topicName=arrayList_subscriptions.get(position);
                if (topicName.equals("bilim")){
                    topicId=1;
                }
                else if (topicName.equals("ekonomi")){
                    topicId=2;
                }
                else if (topicName.equals("siyaset")){
                    topicId=3;
                }
                else if (topicName.equals("spor")){
                    topicId=4;
                }
                else if (topicName.equals("teknoloji")){
                    topicId=5;
                }
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Abonelikler.this);
                alertDialogBuilder.setMessage("Bu aboneligi kaldirmak istediginize emin misiniz?");
                alertDialogBuilder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        database.deleteSubscription(userId,topicId);
                        ArrayList<String> subscriptions=database.getSubcriptions(userName);
                        subscriptions.add(topicName);
                        kafkaClient.subscribe(subscriptions);
                        Toast.makeText(Abonelikler.this,"Abonelik Kaldirildi",Toast.LENGTH_LONG).show();
                    }
                });
                alertDialogBuilder.setNegativeButton("Hayır",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        listView_nonsucscriptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String topicName=arrayList_subscriptions.get(position);
                if (topicName.equals("bilim")){
                    topicId=1;
                }
                else if (topicName.equals("ekonomi")){
                    topicId=2;
                }
                else if (topicName.equals("siyaset")){
                    topicId=3;
                }
                else if (topicName.equals("spor")){
                    topicId=4;
                }
                else if (topicName.equals("teknoloji")){
                    topicId=5;
                }
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Abonelikler.this);
                alertDialogBuilder.setMessage("Bu konuya abone olmak istediginize emin misiniz?");
                alertDialogBuilder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        database.newSubscription(userId,topicId);
                        ArrayList<String> subscriptions=database.getSubcriptions(userName);
                        subscriptions.add(topicName);
                        kafkaClient.subscribe(subscriptions);
                        Toast.makeText(Abonelikler.this,"Abone Olundu",Toast.LENGTH_LONG).show();
                    }
                });
                alertDialogBuilder.setNegativeButton("Hayır",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }*/
}