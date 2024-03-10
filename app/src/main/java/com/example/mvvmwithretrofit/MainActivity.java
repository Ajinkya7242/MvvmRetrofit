package com.example.mvvmwithretrofit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.mvvmwithretrofit.databinding.ActivityMainBinding;
import com.example.mvvmwithretrofit.model.Movie;
import com.example.mvvmwithretrofit.view.MovieAdapter;
import com.example.mvvmwithretrofit.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        viewModel=new ViewModelProvider(this).get(MainActivityViewModel.class);

        getPopularMovies();
        swipeRefreshLayout=binding.swiperefresh;
        swipeRefreshLayout.setColorSchemeResources(R.color.black);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
    }

    private void getPopularMovies() {

        viewModel.getAllMovieData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesLiveData) {
                movies=(ArrayList<Movie>)moviesLiveData;
                displayMovieInRecyclerView();
            }
        });
    }

    private void displayMovieInRecyclerView() {
        recyclerView=binding.rvAllMovie;

        adapter=new MovieAdapter(this,movies);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        adapter.notifyDataSetChanged();


    }
}