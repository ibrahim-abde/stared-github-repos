package com.brhm.githubrepos.reposList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brhm.githubrepos.R;
import com.brhm.githubrepos.Utils;
import com.brhm.githubrepos.models.Repo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_LOADING = 1;
    private static int TYPE_REPO = 2;

    private List<Repo> repos;
    private boolean loading = false;

    public ReposListAdapter() {
        super();
        repos = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(viewType == TYPE_LOADING) {
            return new RecyclerView.ViewHolder(inflater.inflate(R.layout.loading_item,parent,false)) {};
        } else
            return new RepoViewHolder(inflater.inflate(R.layout.repo_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, int position) {
        if(getItemViewType(position) == TYPE_LOADING) return;

        Repo repo = repos.get(position);
        RepoViewHolder holder = (RepoViewHolder) h;

        holder.name.setText(repo.getName());
        holder.description.setText(repo.getDescription());
        holder.numberStars.setText(Utils.getShortNumber(repo.getStars()));
        holder.username.setText(repo.getOwner().getName());

        Picasso.get()
                .load(repo.getOwner().getAvatarURL())
                .placeholder(R.drawable.avatar_holder)
                .into(holder.userAvatar);
    }

    @Override
    public int getItemViewType(int position) {
        if(loading && position >= repos.size())
            return TYPE_LOADING;
        else
            return TYPE_REPO;
    }

    public boolean isEmpty() {
        return repos.isEmpty();
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        if(loading)
            notifyItemInserted(repos.size());
        else
            notifyItemRemoved(repos.size());
    }

    public void addRepos(List<Repo> repos) {
        setLoading(false);
        this.repos.addAll(repos);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return repos.size() + (loading ? 1 : 0);
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.username)
        TextView username;

        @BindView(R.id.user_image)
        ImageView userAvatar;

        @BindView(R.id.number_stars)
        TextView numberStars;

        RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
