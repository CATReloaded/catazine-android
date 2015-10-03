package net.catazine.live.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.catazine.live.Models.Author;
import net.catazine.live.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AuthorsAdapter extends ArrayAdapter<Author> {

    public AuthorsAdapter(Context context, ArrayList<Author> authors) {
        super(context, R.layout.list_item_authors, authors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Author author = getItem(position);

        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_authors, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.author_name.setText(author.getName());
        Picasso.with(getContext())
                .load(author.getAvatar())
                .into(holder.author_image);

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.author_name)
        TextView author_name;
        @Bind(R.id.author_image)
        ImageView author_image;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
