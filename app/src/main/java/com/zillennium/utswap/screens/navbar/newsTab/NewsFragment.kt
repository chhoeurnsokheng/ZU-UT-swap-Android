package com.zillennium.utswap.screens.navbar.newsTab

//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.zillennium.utswap.bases.mvp.BaseMvpFragment
//import com.zillennium.utswap.screens.news.articleNewsScreen.ArticleNewsActivity
//import com.zillennium.utswap.databinding.FragmentNavbarNewsBinding
//import java.io.IOException
//
//class NewsFragment :
//    BaseMvpFragment<NewsView.View, NewsView.Presenter>(),
//    NewsView.View {
//
//    var binding: FragmentNavbarNewsBinding? = null
//    override var mPresenter: NewsView.Presenter = NewsPresenter()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
//
//        binding = FragmentNavbarNewsBinding.inflate(inflater, container, false)
//        val root = binding?.root
//
//        try {
//            binding?.apply {
//                for (i in 0 until layBreakingNews.childCount) {
//                    layBreakingNews.getChildAt(i).setOnClickListener { view: View? ->
//                        startActivity(
//                            Intent(
//                                context,
//                                ArticleNewsActivity::class.java
//                            )
//                        )
//                    }
//                }
//
//                for (i in 0 until layPopularNews.childCount) {
//                    layPopularNews.getChildAt(i).setOnClickListener { view: View? ->
//                        startActivity(
//                            Intent(
//                                context,
//                                ArticleNewsActivity::class.java
//                            )
//                        )
//                    }
//                }
//
//                for (i in 0 until layLatestNews.childCount) {
//                    layLatestNews.getChildAt(i).setOnClickListener { view: View? ->
//                        startActivity(
//                            Intent(
//                                context,
//                                ArticleNewsActivity::class.java
//                            )
//                        )
//                    }
//                }
//            }
//            // Code
//        } catch (error: IOException) {
//            // Must be safe
//        }
//
//        return root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }
//}