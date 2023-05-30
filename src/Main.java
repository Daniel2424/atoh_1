import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Объект для работы с данными
        MyMap inMemoryCache = new MyMap();


    }


}


class MyMap{
    private final Map<Long,Record> mapByAccount = new TreeMap<>();
    private final Map<String, List<Record>> mapByName = new TreeMap<>();
    private final Map<Double, List<Record>> mapByValue = new TreeMap<>();

    // O(long(n))
    public void addRecord(Record record){
        mapByAccount.put(record.getAccount(), record);
        if(mapByName.containsKey(record.getName())){
            mapByName.get(record.getName()).add(record);
        }else{
            mapByName.put(record.getName(), new ArrayList<>(List.of(record)));
        }

        if(mapByValue.containsKey(record.getValue())){
            mapByValue.get(record.getValue()).add(record);
        }else{
            mapByValue.put(record.getValue(), new ArrayList<>(List.of(record)));
        }
    }

    // O(n)
    //
    public void removeRecord(long account){

        Record record = mapByAccount.remove(account);
        String name = record.getName();
        double value = record.getValue();
        mapByName.get(name).removeIf(rec-> rec.getAccount() == account);
        mapByValue.get(value).removeIf( rec -> rec.getAccount() == account);
    }

    // O(n)
    public void changeRecordByName(Record record){//?
        long account = record.getAccount();
        String newName = record.getName();
        Record curRecord = mapByAccount.get(account);
        String oldName = curRecord.getName();
        curRecord.setName(newName);
        mapByName.get(oldName).removeIf( rec -> rec.getAccount() == account);

        if(mapByName.containsKey(newName)){
            mapByName.get(newName).add(curRecord);
        }else{
            mapByName.put(newName, new ArrayList<>(List.of(curRecord)));
        }

    }

    // O(n)
    public void changeRecordByValue(Record newRecord){//?
        long account = newRecord.getAccount();
        double newValue = newRecord.getValue();
        Record curRecord = mapByAccount.get(account);
        double oldValue = curRecord.getValue();
        curRecord.setValue(newValue);
        mapByValue.get(oldValue).removeIf( rec -> rec.getAccount() == account);

        if(mapByValue.containsKey(newValue)){
            mapByValue.get(newValue).add(curRecord);
        }else{
            mapByValue.put(newValue, new ArrayList<>(List.of(curRecord)));
        }
    }

    // O(n)
    public void changeRecordByNameAndValue(Record record){
        long account = record.getAccount();
        String newName = record.getName();
        double newValue = record.getValue();
        Record curRecord = mapByAccount.get(account);
        String oldName = curRecord.getName();
        double oldValue = curRecord.getValue();
        curRecord.setName(newName);
        curRecord.setValue(newValue);
        mapByName.get(oldName).removeIf(rec -> rec.getAccount() == account);
        mapByValue.get(oldValue).removeIf(rec -> rec.getAccount() == account);

        if(mapByName.containsKey(newName)){
            mapByName.get(newName).add(curRecord);
        }else{
            mapByName.put(newName, new ArrayList<>(List.of(curRecord)));
        }

        if(mapByValue.containsKey(newValue)){
            mapByValue.get(newValue).add(curRecord);
        }else{
            mapByValue.put(newValue, new ArrayList<>(List.of(curRecord)));
        }
    }

    // O(log(n))
    public Record getRecordByAccount(long account){
        return mapByAccount.get(account);
    }

    // O(log(n))
    public List<Record> getRecordsByName(String name){
        return mapByName.get(name);
    }

    // O(log(n))
    public List<Record> getRecordsByValue(double value){
        return mapByValue.get(value);
    }






}

class Record{
    private long account;

    private String name;
    private double value;

    public Record(){}

    public Record(long account, String name, double value) {
        this.account = account;
        this.name = name;
        this.value = value;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
