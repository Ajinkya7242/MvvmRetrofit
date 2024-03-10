package com.example.mvvmwithretrofit.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmwithretrofit.model.Movie;
import com.example.mvvmwithretrofit.model.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.movieRepository =new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAllMovieData(){
        return movieRepository.getMutableLiveData();
    }
}
