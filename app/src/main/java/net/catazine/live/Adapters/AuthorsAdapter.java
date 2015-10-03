package net.catazine.live.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.catazine.live.Models.Author;
import net.catazine.live.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AuthorsAdapter extends RecyclerView.Adapter<AuthorsAdapter.AuthorsViewHolder> {
    OnItemClickListener mItemClickListener;
    private ArrayList<Author> authors;
    private Context context;

    public AuthorsAdapter(Context context, ArrayList<Author> authors) {
        this.context = context;
        this.authors = authors;
    }

    @Override
    public AuthorsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_authors, null);
        return new AuthorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AuthorsViewHolder authorsViewHolder, int i) {
        final Author author = (Author) authors.toArray()[i];

        //Setting text view title
        authorsViewHolder.authorName.setText(author.getName());
        authorsViewHolder.authorName.setContentDescription(author.getName());

        //Download image using picasso library
        Picasso
                .with(context)
                .load(author.getAvatar())
                .into(authorsViewHolder.authorAvatar);

    }

    @Override
    public int getItemCount() {
        return (null != authors ? authors.size() : 0);
    }

    public void appendAuthors(ArrayList<Author> authors) {
        if (this.authors == null) {
            this.authors = authors;
        } else {
            this.authors.addAll(authors);
        }
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class AuthorsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.author_row)
        LinearLayout authorRow;
        @Bind(R.id.author_name)
        TextView authorName;
        @Bind(R.id.author_image)
        ImageView authorAvatar;

        public AuthorsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            authorRow.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }
    }
}
