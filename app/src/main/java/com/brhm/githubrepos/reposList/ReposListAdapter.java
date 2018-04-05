package com.brhm.githubrepos.reposList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brhm.githubrepos.R;
import com.brhm.githubrepos.models.Repo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposListAdapter extends RecyclerView.Adapter<ReposListAdapter.ViewHolder> {

    private List<Repo> repos;

    public ReposListAdapter() {
        super();
        repos = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repo repo = repos.get(position);

        holder.name.setText(repo.getName());
    }

    public void addRepos(List<Repo> repos) {
        this.repos.addAll(repos);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
