package com.aalasolutions.apps.cricketlivescores.classes;

import android.content.Context;
import android.os.AsyncTask;

import com.aalasolutions.apps.cricketlivescores.model.MatchDetail;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class XmlParser {
    public volatile boolean parsingComplete = true;
    protected CricketLiveScore cricketLiveScore;
    Context context;
    private String urlString = "http://www.aalasolutions.com/api/cricket/getlivescores.php";
    private XmlPullParserFactory xmlFactoryObject;

    public XmlParser(Context c) {
        cricketLiveScore = (CricketLiveScore) c;
        cricketLiveScore.parsing = true;
        context = c;
        new GetLiveMatches().execute();
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text = null;
        boolean itemstarted = false;
        try {
            event = myParser.getEventType();
            MatchDetail matchDetail = new MatchDetail();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (name.equals("item")) {
                            itemstarted = true;
                            matchDetail = new MatchDetail();
                            matchDetail.matchLink = " ";
                            matchDetail.matchTitle = " ";
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (itemstarted) {
                            if (name.equals("title")) {
                                matchDetail.matchTitle = text;
                            } else if (name.equals("link")) {
                                matchDetail.matchLink = text;
                            }
                            if (name.equals("item")) {
                                itemstarted = false;
                                cricketLiveScore.addMatchDetails(matchDetail);
                            }
                        }
                        break;
                }
                event = myParser.next();
            }

            parsingComplete = false;
            cricketLiveScore.parsing = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class GetLiveMatches extends AsyncTask<String, String, String> {
        

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected String doInBackground(String... args) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection)
                        url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                InputStream stream = conn.getInputStream();

                xmlFactoryObject = XmlPullParserFactory.newInstance();
                XmlPullParser myparser = xmlFactoryObject.newPullParser();

                myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
                        , false);
                myparser.setInput(stream, null);
                parseXMLAndStoreIt(myparser);
                stream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }



        protected void onPostExecute(String file_url) {
            if (file_url != null) {
            }

        }

    }

}
