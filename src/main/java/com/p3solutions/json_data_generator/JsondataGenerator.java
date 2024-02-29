package com.p3solutions.json_data_generator;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JsondataGenerator {
    public static void main(String args[]){
    {
        int noOfRecords = 100;
        generateJson(noOfRecords);
    }
  }

    private static JsonArray generateJson(int noOfRecords) {
        int i=0;
        JsonArray jsonArray = new JsonArray();
        while(i<noOfRecords) {
            Faker faker = new Faker(new Locale("en-US"));

            String id = faker.internet().uuid();
            String subjectId = faker.internet().uuid();
            String lastName = faker.name().lastName();
            String firstName = faker.name().firstName();
            String clientId = faker.number().digits(4);

            Date randomCrreatedDateTime = faker.date().past(1, java.util.concurrent.TimeUnit.DAYS);
            SimpleDateFormat createdsfd = new SimpleDateFormat("yyyy-MM-dd");
            String created = createdsfd.format(randomCrreatedDateTime);
//            Date randomDateTime1 = faker.date().past(1, java.util.concurrent.TimeUnit.DAYS);
            int monthNumber = faker.number().numberBetween(1, 12);
            String month = String.format("%02d", monthNumber);
            // Format the date and time to "yyyy-MM-dd'T'HH:mm:ss.SSS"
            Date randomDateTime = faker.date().past(1, java.util.concurrent.TimeUnit.DAYS);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String begin = sdf.format(randomDateTime);
            // Format the date and time to "yyyy-MM-dd'T'HH:mm:ss.SSS"

            Date randomDateTime1 = faker.date().past(1, java.util.concurrent.TimeUnit.DAYS);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String end = sdf1.format(randomDateTime1);

            String contactName = faker.name().fullName();
            String contactId = faker.internet().uuid();
            String phoneNumber = faker.phoneNumber().subscriberNumber(10);
            String delegateName = faker.name().fullName();
            String delegateId = faker.number().digits(6);
            String emailAddress = faker.internet().emailAddress();
            String contactEmailAddress = faker.internet().emailAddress();
            String externalRefId = faker.internet().uuid();
            String channel = faker.options().option("web", "voice", "mobile", "chat", "email", "text", "sms", "fax", "officall");
            String system = faker.options().option("upoint", "worklife", "careportal", "cspro", "alis", "spm");
            String initiated = faker.options().option("internal", "external");
            String details = faker.lorem().sentence();
            String description = faker.lorem().sentence();
            String contactType = faker.options().option("ach","sp","poa","lgl","lgf","roe","goa","3po","sa");
            String type = faker.options().option("EE", "MGR","HRP","UR","QDRO","BENE","CW","POI");
            String ipaddr =  String.format("%d.%d.%d.%d",
                    faker.number().numberBetween(1, 256),
                    faker.number().numberBetween(0, 256),
                    faker.number().numberBetween(0, 256),
                    faker.number().numberBetween(0, 256));
            String json = String.format("{\n" +
                            "\"_id\": \"%s\",\n" +
                            "\"subject\": {\n" +
                            "    \"id\": \"%s\",\n" +
                            "    \"lastName\": \"%s\",\n" +
                            "    \"firstName\": \"%s\",\n" +
                            "    \"type\": \"%s\"\n" +
                            "},\n" +
                            "\"clientId\": \"%s\",\n" +
                            "\"created\": \"%s\",\n" +
                            "\"month\": \"%s\",\n" +
                            "\"begin\": \"%s\",\n" +
                            "\"end\": \"%s\",\n" +
                            "\"channel\": \"%s\",\n" +
                            "\"system\": \"%s\",\n" +
                            "\"initiated\": \"%s\",\n" +
                            "\"contact\": {\n" +
                            "    \"name\": \"%s\",\n" +
                            "    \"id\": \"%s\",\n" +
                            "    \"type\": \"%s\",\n" +
                            "    \"phoneNumber\": \"%s\",\n" +
                            "    \"emailAddress\": \"%s\",\n" +
                            "    \"ipAddress\": \"%s\"\n" +
                            "},\n" +
                            "\"delegate\": {\n" +
                            "    \"name\": \"%s\",\n" +
                            "    \"id\": \"%s\",\n" +
                            "    \"emailAddress\": \"%s\"\n" +
                            "},\n" +
                            "\"description\": \"%s\",\n" +
                            "\"details\": \"%s\",\n" +
                            "\"externalRefId\": \"%s\"\n" +
                            "}", id, subjectId, lastName, firstName, type, clientId, created, month, begin, end, channel, system, initiated,
                    contactName, contactId, contactType, phoneNumber,contactEmailAddress, ipaddr, delegateName, delegateId, emailAddress, description, details, externalRefId);
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            generateBucketValue(jsonObject);
             jsonArray.add(jsonObject);
             i++;
        }
        return jsonArray;
    }

    private static void generateBucketValue(JsonObject jsonObject) {
        Faker faker = new Faker(new Locale("en-US"));
        JsonObject isSecured = new JsonObject();
        isSecured.addProperty("isSecured",  faker.options().option("true","false"));
        JsonObject securityMethod = new JsonObject();
        securityMethod.addProperty("securityMethod",  faker.options().option("IVR","oneTimeCode","pinEntry","indicativeData"));
        int randomNumber = faker.number().numberBetween(0, 11);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(isSecured);
        jsonArray.add(securityMethod);
        for(int i=1; i<=randomNumber; i++){
          JsonObject object= new JsonObject();
          object.addProperty("secondaryTopic"+i, faker.lorem().sentence());
          jsonArray.add(object);
          i++;
        }
        jsonObject.add("buckets", jsonArray);
    }
}
