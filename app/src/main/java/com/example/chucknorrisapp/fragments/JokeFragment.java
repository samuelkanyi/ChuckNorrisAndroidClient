package com.example.chucknorrisapp.fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.QuoteSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.chucknorrisapp.R;
import com.example.chucknorrisapp.controller.Controller;
import com.example.chucknorrisapp.models.Joke;
import com.wang.avi.AVLoadingIndicatorView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JokeFragment extends Fragment implements  View.OnClickListener{

    private AVLoadingIndicatorView avi;
    ImageView imageView;
    TextView jokeView;
    Bundle bundle;


    private static final String ARG_CATEGORY = "category";
    private static final String TAG = "JokeFragment";
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {




        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joke, container, false);

    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jokeView = view.findViewById(R.id.jokeView);
        imageView = view.findViewById(R.id.refreshView);
        imageView.setOnClickListener(this);
        avi = (AVLoadingIndicatorView)getActivity().findViewById(R.id.indicator);
        avi.smoothToShow();
        bundle = getArguments();
        fetchJoke(bundle);



    }

    void fetchJoke(Bundle bundle){
        jokeView.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        Controller.getInstance().getRandomJoke(bundle.getString("category")).enqueue(new Callback<Joke>() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                SpannableString string = new SpannableString(response.body().getValue());
                string.setSpan(new QuoteSpan(Color.parseColor("#2BDEDE"), 20, 40), 0, string.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                jokeView.setText(string);
                jokeView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);

                avi.hide();
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                avi.hide();
            }
        });

    }

    @Override
    public void onClick(View view) {
        avi.smoothToShow();
        fetchJoke(bundle);
    }
}