package com.example.aslstudytool;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraper extends AsyncTask<WebScraper.WebScraperCallback, Void, Void> {
    Semaphore jsoupLock = new Semaphore(1);
    String regex = "((https?://)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(/([a-zA-Z-_/.0-9#:+?%=&;,]*)?)?)";
    @Override
    protected Void doInBackground(WebScraper.WebScraperCallback... webScraperCallbacks) {

        try{
            jsoupLock.acquire();
            for(int i = 0; i < MainActivity.letterUrls.size();i++) {

                Document aslDictionaryMenu = Jsoup.connect(MainActivity.letterUrls.get(i)).get();

                String mPage = aslDictionaryMenu.outerHtml();
                ArrayList<String> urlList = new ArrayList<>(Arrays.asList(mPage.split("<a href=")));
                for (int ii = 0; ii < urlList.size(); ii++) {
                    Matcher mMatcher = Pattern.compile(regex).matcher(urlList.get(ii));
                    if (mMatcher.find()) {
                        if (!mMatcher.group().contains("Lifeprint.com")) {
                            if((int)(mMatcher.group(1).charAt(0))==(i+97)) {
                                if(MainActivity.wordUrls.size()>1) {

                                    if (MainActivity.wordUrls.get(MainActivity.wordUrls.size() - 1).equals("https://www.lifeprint.com/asl101/pages-signs/" + (mMatcher.group(1).charAt(0)) + "/" + mMatcher.group(1))) {

                                    } else {
                                        MainActivity.wordUrls.add("https://www.lifeprint.com/asl101/pages-signs/" + (mMatcher.group(1).charAt(0)) + "/" + mMatcher.group(1));

                                        MainActivity.words.add(mMatcher.group(1).substring(0, 1).toUpperCase() + mMatcher.group(1).substring(1).replace(".htm", "").replace("-", " ").trim());
                                    }
                                }else{

                                    MainActivity.wordUrls.add("https://www.lifeprint.com/asl101/pages-signs/" + (mMatcher.group(1).charAt(0)) + "/" + mMatcher.group(1));

                                    MainActivity.words.add(mMatcher.group(1).substring(0, 1).toUpperCase() + mMatcher.group(1).substring(1).replace(".htm", "").replace("-", " ").trim());
                                }
                             if(MainActivity.words.get(MainActivity.words.size()-1).length()>2&&MainActivity.words.get(MainActivity.words.size()-2).length()>=3
                                     &&MainActivity.words.size()>1
                                     &&MainActivity.words.get(MainActivity.words.size()-1).charAt(0)==MainActivity.words.get(MainActivity.words.size()-2).charAt(0)
                                        &&MainActivity.words.get(MainActivity.words.size()-1).charAt(2)<MainActivity.words.get(MainActivity.words.size()-2).charAt(2)){

                                MainActivity.words.remove(MainActivity.words.size()-2);
                                MainActivity.wordUrls.remove(MainActivity.wordUrls.size()-2);
                             }

                            }

                            /*else{
                                MainActivity.words.add("("+mMatcher.group(1).replace(".htm", "").replace("-"," ")+")");
                            }*/
                        }
                    }
                }
            }  jsoupLock.release();
        }catch (Exception e){
            e.printStackTrace();
        }

        webScraperCallbacks[0].handleResult();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    interface WebScraperCallback{

        void handleResult();

    }
}
