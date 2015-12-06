package madelyntav.c4q.nyc.actualandroidstudioproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
/**
 * Created by c4q-madelyntavarez on 12/6/15.
 */
public class CustomAdapter extends BaseAdapter {
    public Context _context;
    public List<Result> results;

    public CustomAdapter(Context context, List<Result> results){
        this._context= context;
        this.results= results;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null) {
            LayoutInflater infalInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.small_view, null);
        }
        ImageView imageView= (ImageView) convertView.findViewById(R.id.image);
        String art= results.get(position).artworkUrl100;
        Picasso.with(_context).load(art).resize(200,200).centerCrop().into(imageView);

        TextView textView= (TextView) convertView.findViewById(R.id.title);
        String artist=results.get(position).artistName;
        String song=results.get(position).trackName;
        textView.setText(artist+": "+song);

        TextView details=(TextView) convertView.findViewById(R.id.details);
        String releaseDate=results.get(position).releaseDate;
        details.setText(releaseDate);


        return convertView;
    }
}
