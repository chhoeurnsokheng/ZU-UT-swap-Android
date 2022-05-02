package com.zillennium.utswap.screens.navbar.newsTab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zillennium.utswap.databinding.FragmentNavbarNewsBinding;
import com.zillennium.utswap.screens.news.articleNewsScreen.ArticleNewsActivity;

public class NewsFragment extends Fragment {

    private FragmentNavbarNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNavbarNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        LinearLayout layBreakingNews = this.binding.layBreakingNews;
        for (int i = 0; i < layBreakingNews.getChildCount(); i++) {
            layBreakingNews.getChildAt(i).setOnClickListener(view -> {
                startActivity(new Intent(getContext(), ArticleNewsActivity.class));
            });
        }

        LinearLayout layPopularNews = this.binding.layPopularNews;
        for (int i = 0; i < layPopularNews.getChildCount(); i++) {
            layPopularNews.getChildAt(i).setOnClickListener(view -> {
                startActivity(new Intent(getContext(), ArticleNewsActivity.class));
            });
        }

        LinearLayout layLatestNews = this.binding.layLatestNews;
        for (int i = 0; i < layLatestNews.getChildCount(); i++) {
            layLatestNews.getChildAt(i).setOnClickListener(view -> {
                startActivity(new Intent(getContext(), ArticleNewsActivity.class));
            });
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}