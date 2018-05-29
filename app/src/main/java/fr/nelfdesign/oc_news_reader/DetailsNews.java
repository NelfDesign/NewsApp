package fr.nelfdesign.oc_news_reader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailsNews extends AppCompatActivity {

    private TextView desc, link, date;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_news);

        String titre = getIntent().getStringExtra("titre");
        setTitle(titre);

        link = findViewById(R.id.link);
        desc = findViewById(R.id.desc);
        img = findViewById(R.id.image);
        date = findViewById(R.id.date);

        String link2 = getIntent().getStringExtra("link");
        link.setText("Lien vers l'article : " + link2);

        String description = getIntent().getStringExtra("description");
        desc.setText(description);

        String image = getIntent().getStringExtra("image");
        Picasso.get()
                .load(image)
                .resize(900, 500)
                .into(img);

        String dt = getIntent().getStringExtra("date");
        date.setText(dt);

    }
}
