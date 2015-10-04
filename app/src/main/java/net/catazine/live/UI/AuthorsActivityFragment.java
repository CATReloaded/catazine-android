package net.catazine.live.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rockerhieu.rvadapter.endless.EndlessRecyclerViewAdapter;

import net.catazine.live.Adapters.AuthorsAdapter;
import net.catazine.live.Models.Author;
import net.catazine.live.R;
import net.catazine.live.Utility.EndlessRecyclerOnScrollListener;
import net.catazine.live.Utility.NetworkHelper;
import net.catazine.live.Utility.NetworkResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AuthorsActivityFragment extends Fragment
        implements EndlessRecyclerViewAdapter.RequestToLoadMoreListener {

    @Bind(R.id.authors_recycler_view)
    RecyclerView mAuthorsRecyclerView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    private AuthorsAdapter authorsAdapter;
    private LinearLayoutManager mGridLayoutManager;

    public AuthorsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authors, container, false);
        ButterKnife.bind(this, view);
        mGridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mAuthorsRecyclerView.setLayoutManager(mGridLayoutManager);
        authorsAdapter = new AuthorsAdapter
                (getActivity(), null);

        inflateAuthors(1, this);

        return view;
    }

    private void inflateAuthors(final int page_number, final AuthorsActivityFragment authorsActivityFragment) {

        NetworkHelper.requestAuthors(page_number, new NetworkResponse() {

            @Override
            public void onFailure(IOException error, int code) {

            }

            @Override
            public void onResponse(Collection<?> resultList) {
                authorsAdapter.appendAuthors((ArrayList<Author>) resultList);
                mAuthorsRecyclerView.setAdapter(authorsAdapter);
                mAuthorsRecyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(mGridLayoutManager) {
                    @Override
                    public void onLoadMore(int current_page) {
                        inflateAuthors(page_number + 1, authorsActivityFragment);
                    }
                });

                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onResponse(Object resultObj) {

            }
        });
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
