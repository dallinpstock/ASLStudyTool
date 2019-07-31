package com.example.aslstudytool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.ScriptGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkAdapter {
    static String httpRequest(String urlString) {
        return httpRequest(urlString, null);
    }

    static String httpRequest(String urlString, String requestMethod) {
                String result = "";
                InputStream inputStream = null;
                HttpsURLConnection connection = null;

                try {
                    URL url = new URL(urlString);
                    connection = (HttpsURLConnection) url.openConnection();

                    connection.setRequestMethod(requestMethod);
                    connection.setConnectTimeout(6000);
                    connection.setReadTimeout(6000);
                    final int responseCode = connection.getResponseCode();
                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        inputStream = connection.getInputStream();
                        if (inputStream != null) {
                            BufferedReader mReader = new BufferedReader(new InputStreamReader(inputStream));
                            StringBuilder mBuilder = new StringBuilder();
                            String line;
                            do {
                                line = mReader.readLine();
                                mBuilder.append(line);
                            } while (line != null);
                            result = mBuilder.toString();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
    interface NetworkImageCallback{
        void processImage(Bitmap image);
    }

            public static void bitmapFromUrl(final String stringUrl,final NetworkImageCallback callback, final int i) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                Bitmap result = null;
                InputStream mInputStream = null;
                HttpsURLConnection connection = null;
                try {
                    URL url = new URL(stringUrl);
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setReadTimeout(6000);
                    connection.setConnectTimeout(6000);

                    connection.connect();

                    if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                        mInputStream = connection.getInputStream();
                        if (mInputStream != null) {
                            result = BitmapFactory.decodeStream(mInputStream);
                        }
                    }
                    callback.processImage(result);


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    if (mInputStream != null) {
                        try {
                            mInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }).start();
    }

}
