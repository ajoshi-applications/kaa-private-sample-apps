package app.main;

import app.mongoservice.MongoLogs;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        MongoLogs mongoLogs = new MongoLogs();
        mongoLogs.getLogRecords();
    }
}
