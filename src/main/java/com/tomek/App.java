package com.tomek;

import static java.util.Arrays.asList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;


/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws ParseException
    {

        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("test");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        db.getCollection("restaurants").insertOne(
                new Document("address",
                        new Document()
                                .append("street", "2 Avenue")
                                .append("zipcode", "10075")
                                .append("building", "1480")
                                .append("coord", asList(-73.9557413, 40.7720266)))
                        .append("borough", "Manhattan")
                        .append("cuisine", "Italian")
                        .append("grades", asList(
                                new Document()
                                        .append("date", format.parse("2014-10-01T00:00:00Z"))
                                        .append("grade", "A")
                                        .append("score", 11),
                                new Document()
                                        .append("date", format.parse("2014-01-16T00:00:00Z"))
                                        .append("grade", "B")
                                        .append("score", 17)))
                        .append("name", "Vella")
                        .append("restaurant_id", "6666666"));

        FindIterable<Document> iterable = db.getCollection("restaurants").find();
        //iterable = db.getCollection("restaurants").find(new Document("borough", "Manhattan"));
        //iterable = db.getCollection("restaurants").find(new Document("address.zipcode", "10075"));
        //iterable = db.getCollection("restaurants").find(new Document("cuisine", "Italian").append("address.zipcode", "10075"));
        //iterable = db.getCollection("restaurants").find().sort(new Document("borough", 1).append("address.zipcode", 1));

        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                System.out.println(document);
            }
        });


        db.getCollection("restaurants").updateOne(new Document("name", "Juni"),
                new Document("$set", new Document("cuisine", "American (New)"))
                        .append("$currentDate", new Document("lastModified", true)));


        db.getCollection("restaurants").replaceOne(new Document("restaurant_id", "41704620"),
                new Document("address",
                        new Document()
                                .append("street", "2 Avenue")
                                .append("zipcode", "10075")
                                .append("building", "1480")
                                .append("coord", asList(-73.9557413, 40.7720266)))
                        .append("name", "Vella 2"));


        //db.getCollection("restaurants").deleteMany(new Document("borough", "Manhattan"));







        /*String auth = "%3C%3Fxml+version%3D%221.0%22+encoding%3D%22utf-8%22%3F%3E%3CclickAPI%3E%0D%0A%3Cauth%3E%0D%0A%3Capi_id%3E3157861%3C%2Fapi_id%3E%0D%0A%3Cuser%3Emrted%3C%2Fuser%3E%0D%0A%3Cpassword%3EBXZacdjor6%3C%2Fpassword%3E%0D%0A%3C%2Fauth%3E%0D%0A%3C%2FclickAPI%3E%0D%0A";

        String message = "%3C%3Fxml+version%3D%221.0%22+encoding%3D%22utf-8%22%3F%3E%3CclickAPI%3E%0D%0A%3CsendMsg%3E%0D%0A%3Csession_id%3Ef461140b461cb8fed14be64bdc49a9a3%3C%2Fsession_id%3E%0D%0A%3Ctext%3Econtact+by+sms%3C%2Ftext%3E%0D%0A%3Cto%3E48663275222%3C%2Fto%3E%0D%0A%3C%2FsendMsg%3E%0D%0A%3C%2FclickAPI%3E%0D%0A";

        try {
            String decode = URLDecoder.decode(auth, "UTF-8");
            System.out.println(decode);

            String decodeMessage = URLDecoder.decode(message, "UTF-8");
            System.out.println(decodeMessage);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String str = "<script>alert('testing')</script>";
        System.out.println(str);

        String escapeHtml = escapeHtml(str);
        System.out.println(escapeHtml);

        String unescapeHtml = unescapeHtml(str);
        System.out.println(unescapeHtml);



        boolean exp1 = false;
        boolean exp2 = true;

        if(!(exp1)) {

        }

        boolean exp3 = true;
        boolean exp4 = false;

        if((exp3 && exp4)) {

        }*/




        //JSONObject jsonObject = JSONObject.fromObject(''{\"foo\":\"\"}");
        //String str = jsonObject.getString("foo");
        //System.out.println(str);
        /*try {

            FileReader inputFile = new FileReader("c:\\test.json");

            BufferedReader bufferReader = new BufferedReader(inputFile);

            String jsonArray;

            jsonArray = bufferReader.readLine();

            bufferReader.close();

            System.out.println(jsonArray);

            Collection collection = JSONArray.toCollection(JSONArray.fromObject(jsonArray), ApplicationProcessDocument.class);
            collection.removeAll(Collections.singleton(null));
            ArrayList<ApplicationProcessDocument> arrayList = new ArrayList<ApplicationProcessDocument>(collection);

            for (ApplicationProcessDocument applicationProcessDocument : arrayList) {
                System.out.println(applicationProcessDocument.getQuestionnaireFatherId() + " " + applicationProcessDocument.getValue());

                applicationProcessDocument.getValue();
                if (StringUtils.isNotEmpty(applicationProcessDocument.getValue())) {
                    System.out.println("not null");
                    long formId = Long.parseLong(applicationProcessDocument.getValue());
                    System.out.println("formId" + formId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
