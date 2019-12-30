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
    private String getPage;
    private String totalPage = null;
    private String numOfRows = null;


    @Override
    protected ArrayList<LandMark> doInBackground(String... strings) {

        URL url;
        try {
            getPage = strings[0];
        } catch (Exception e) {
            getPage = "1";
        }
        try {
            url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?ServiceKey=FmD3e%2FhDm47HLlFHkAp7gBaIK8WlW5BDMs7OhhU9EJ95VyiOWW%2BGtQ39sCPOpn0OF%2FfE0rYzt%2Fxwdta9FJN16w%3D%3D&keyword=캠핑&MobileOS=ETC&MobileApp=AppTest&pageNo=" + getPage);
            boolean bAddress = false, bTitle = false, bImage = false, bNumOfRows = false, bTotalCount = false, bContentId = false;
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
                        if (parser.getName().equals("numOfRows")) {
                            bNumOfRows = true;
                        }
                        if (parser.getName().equals("totalCount")) {
                            bTotalCount = true;
                        }
                        if (parser.getName().equals("contentid")) {
                            bContentId = true;
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
                        } else if (bNumOfRows) {
                            numOfRows = parser.getText();
                            bNumOfRows = false;
                        } else if (bTotalCount) {
                            numOfRows = parser.getText();
                            bTotalCount = false;
                        } else if (bContentId) {
                            landMark.getTagList().put("contentid", parser.getText());
                            bContentId = false;
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
