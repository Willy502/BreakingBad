package com.alejandro.breakingbad.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.alejandro.breakingbad.R;
import com.alejandro.breakingbad.adapter.CharactersAdapter;
import com.alejandro.breakingbad.model.BBCharacter;
import com.alejandro.breakingbad.services.BreakingBadService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<BBCharacter> BBCharacters;
    private RecyclerView charactersRecycler;
    private CharactersAdapter adapter;
    private Toolbar toolbar;
    private ProgressBar loader;
    private int limit = 10, offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();

        loader.setVisibility(View.VISIBLE);
        toolbar.setTitle("Breaking Bad Characters");
        setActionBar(toolbar);

        BBCharacters = new ArrayList<>();
        adapter = new CharactersAdapter(BBCharacters, this);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        charactersRecycler.setLayoutManager(manager);
        charactersRecycler.setAdapter(adapter);
        charactersRecycler.setItemAnimator(new DefaultItemAnimator());

        getCharacters(limit, offset);
        offset += 10;
        charactersRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    loader.setVisibility(View.VISIBLE);
                    getCharacters(limit, offset);
                    offset += 10;
                }
            }
        });
    }

    private void getCharacters(int newLimit, int newOffset) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.breakingbadapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BreakingBadService breakingBadService = retrofit.create(BreakingBadService.class);
        Call<List<BBCharacter>> call = breakingBadService.getCharacters(newLimit, newOffset);
        call.enqueue(new Callback<List<BBCharacter>>() {
            @Override
            public void onResponse(Call<List<BBCharacter>> call, Response<List<BBCharacter>> response) {
                if (!response.isSuccessful()) return;
                for (BBCharacter character : response.body()) {
                    BBCharacters.add(character);
                }
                adapter.notifyDataSetChanged();
                loader.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<BBCharacter>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void connectView() {
        charactersRecycler = findViewById(R.id.charactersRecycler);
        toolbar = findViewById(R.id.toolbar);
        loader = findViewById(R.id.loader);
    }
}