package fr.nelfdesign.oc_news_reader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.io.InputStream;

class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements XMLParser.DocumentXml{

    private String TAG = "News_Activity";

    private static final int VIEW_TYPE_ARTICLE = 0;
    private static final int VIEW_TYPE_PROGRESS = 1;
    private boolean _hasMore = false;

    private TextView title;
    private static Document _dom = null;

    private final MainActivity _mainActivity;

    public NewsAdapter(MainActivity mainActivity) {
        _mainActivity = mainActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_ARTICLE: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.titre_list, parent, false);
                return new MyViewHolder(view);
            }
            case VIEW_TYPE_PROGRESS: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_progress, parent, false);
                return new ProgressViewHolder(view);
            }
            default:
                throw new IllegalStateException("Unknown type" + viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder){
            Element item = (Element) _dom.getElementsByTagName("item").item(position);
            ((MyViewHolder) holder).setElement(item);
        }else if (holder instanceof ProgressViewHolder) {
            _mainActivity.loadNext();
        }
    }

    @Override
    public int getItemCount() {
        if (_dom != null){
            return _dom.getElementsByTagName("item").getLength() + (_hasMore ? 1 : 0);
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < _dom.getElementsByTagName("item").getLength())
            return VIEW_TYPE_ARTICLE;
        else
            return VIEW_TYPE_PROGRESS;
    }

    @Override
    public void setXMLDocument(Document document, boolean hasMore) {
        _dom = document;
        _hasMore = hasMore;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private Element currentElement;

        public MyViewHolder(final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    Context context = v.getContext();
                    Element item = (Element) _dom.getElementsByTagName("item").item(position);
                    RssItem rss = RssItemBuild(item);
                    Intent intent = new Intent(context, DetailsNews.class);
                    intent.putExtra("link", rss.getLink());
                    intent.putExtra("titre", rss.getTitle());
                    intent.putExtra("description", rss.getDescription());
                    intent.putExtra("date", rss.getDatePublication());
                    intent.putExtra("image", rss.getImage());
                    intent.putExtra("guid", rss.getGuid());
                    context.startActivity(intent);
                }
            });

        }

        public void setElement(Element element) {
            currentElement = element;
            title.setText(element.getElementsByTagName("title").item(0).getTextContent());

        }

        public RssItem RssItemBuild(Element element) {
            currentElement = element;

            String titre = element.getElementsByTagName("title").item(0).getTextContent().toString();
            String link = element.getElementsByTagName("link").item(0).getTextContent().toString();
            String description = element.getElementsByTagName("description").item(0).getTextContent().toString();
            String datePublication = element.getElementsByTagName("pubDate").item(0).getTextContent().toString();
            String image = element.getElementsByTagName("enclosure").item(0).getAttributes().getNamedItem("url").getNodeValue();
            String guid = element.getElementsByTagName("guid").item(0).getTextContent().toString();
            RssItem rssItem = new RssItem(link, titre, description, datePublication, image, guid);
            return rssItem;
        }
    }
    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressViewHolder(View itemView) { super(itemView); }
    }
}






