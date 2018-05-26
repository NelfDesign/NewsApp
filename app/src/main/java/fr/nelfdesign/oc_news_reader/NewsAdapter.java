package fr.nelfdesign.oc_news_reader;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oc.rss.fake.FakeNews;
import com.oc.rss.fake.FakeNewsList;

import java.util.List;

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private String TAG = "News_Activity";
    private List<FakeNews> list = FakeNewsList.all;
    private TextView title;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View newsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.titre_list, parent, false);
        return new MyViewHolder(newsView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FakeNews fake = list.get(position);
        Log.d(TAG, "onBindViewHolder: Fake = " + fake);
        holder.title.setText(fake.title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;

        public MyViewHolder(final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    int position = getLayoutPosition();
                    FakeNews fake = list.get(position);

                    Intent intent = new Intent(context, DetailsNews.class);
                    intent.putExtra("titre", fake.title);
                    intent.putExtra("content", fake.htmlContent);
                    context.startActivity(intent);
                }
            });
        }
    }
}
