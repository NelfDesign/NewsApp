package fr.nelfdesign.oc_news_reader;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private XMLParser parser = null;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(this);
        recyclerView.setAdapter(newsAdapter);

        parser = new XMLParser(newsAdapter);
        parser.execute("https://www.lemonde.fr/rss/une.xml");

        loadNext();

        final ProgressBar progress = findViewById(R.id.progress);
        newsAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                progress.setVisibility(View.GONE);
            }
        });
    }

    public void loadNext() {
        if (parser != null && parser.getStatus() != AsyncTask.Status.FINISHED)
            return ;

        parser = new XMLParser(newsAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (parser != null){
            parser.cancel(true);
        }
    }
}
