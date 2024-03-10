package com.example.mvvmwithretrofit.model;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.example.mvvmwithretrofit.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.microedition.khronos.opengles.GL;

public class Movie extends BaseObservable {



    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @BindingAdapter({"posterPath"})
    public static void loadImage(ImageView imageView, String imgUrl){
        String imagePath="https://image.tmdb.org/t/p/w500/"+imgUrl;
        Glide.with(imageView.getContext()).load(imagePath).into(imageView);
    }



    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }



    @Bindable
    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
        notifyPropertyChanged(BR.voteAverage);

    }



}