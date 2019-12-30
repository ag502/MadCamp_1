package com.example.week1;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class GetXML  extends AsyncTask<String, Void, ArrayList<LandMark>> {
    private ArrayList<LandMark> landMarksList;
    private LandMark landMark;


    @Override
    protected ArrayList<LandMark> doInBackground(String... strings) {
        URL url;
        try {
            url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey=jG9PX8i%2BoC9KR%2BHgXx9KnkCfNed54pdkGVoLIEYO%2Fqpq3Hn17zjx%2BB%2B%2BXiZFeWxl13XMhiRu7aeW7%2BvvJI%2B%2Bpw%3D%3D&MobileApp=AppTest&MobileOS=ETC&pageNo=1&numOfRows=10&listYN=Y&arrange=A&contentTypeId=12&keyword=%ED%98%B8%ED%85%94");
            boolean bAddress = false, bTitle = false, bImage = false;

            InputStream inputStream = url.openStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(inputStream, "UTF-8"));

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        landMarksList = new ArrayList<>();
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item") && landMark !=null) {
                            landMarksList.add(landMark);
                        }
                        break;
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("item")) {
                            landMark = new LandMark();
                        }
                        if (parser.getName().equals("addr1")) {
                            bAddress = true;
                        }
                        if (parser.getName().equals("title")) {
                            bTitle = true;
                        }
                        if (parser.getName().equals("firstimage")) {
                            bImage = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if (bAddress) {
                            landMark.getTagList().put("addr1", parser.getText());
                            bAddress = false;
                        } else if (bTitle) {
                            landMark.getTagList().put("title", parser.getText());
                            bTitle = false;
                        } else if (bImage) {
                            landMark.getTagList().put("firstimage", parser.getText());
                            bImage = false;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            Log.d("Error", "------------------" + e + "--------------------");
        }

        return landMarksList;
    }

    @Override
    protected void onPostExecute(ArrayList<LandMark> document) {
        super.onPostExecute(document);
        for (int i = 0; i < landMarksList.size(); i++) {
            Log.d("Print Element", "---------" + landMarksList.get(i).getTagList().get("firstimage"));
        }
    }
}
