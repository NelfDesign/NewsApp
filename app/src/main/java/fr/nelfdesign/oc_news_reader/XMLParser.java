package fr.nelfdesign.oc_news_reader;

import android.os.AsyncTask;

import org.w3c.dom.Document;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;

public class XMLParser extends AsyncTask<String, Void, Document> {

    interface DocumentXml{
        void setXMLDocument(Document document, boolean hasMore);
    }

    private DocumentXml _Consumer;
    private boolean _hasMore = false;

    public XMLParser(DocumentXml consumer){
        this._Consumer = consumer;
    }
    @Override
    protected Document doInBackground(String... params){

        try {
            Thread.sleep(4000);
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream stream = connection.getInputStream();

            try {
                return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
            }finally {
                stream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(Document result) {
        _Consumer.setXMLDocument(result,false);
    }
}
