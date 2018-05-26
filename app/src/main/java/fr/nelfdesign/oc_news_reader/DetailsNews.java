package fr.nelfdesign.oc_news_reader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;

public class DetailsNews extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_news);

        String titre = getIntent().getStringExtra("titre");
        setTitle(titre);

        webView = findViewById(R.id.web);

        String htmlContent = getIntent().getStringExtra("content");

        webView.loadData(htmlContent, "text/html; charset=UTF-8", null);
    }
}
