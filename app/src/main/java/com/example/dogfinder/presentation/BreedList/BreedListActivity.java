package com.example.dogfinder.presentation.BreedList;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogfinder.R;
import com.example.dogfinder.presentation.BreedList.List.BreedListAdapter;
import com.example.dogfinder.presentation.BreedList.List.OnBreedClickListener;

import java.util.List;

import data.model.Breed;
import data.model.BreedList;

public class BreedListActivity extends AppCompatActivity implements OnBreedClickListener {

    public static String BREED_LIST_KEY = "breed_list_key";
    public static String QUERY_KEY = "query_key";

    private RecyclerView rvBreedList;
    private BreedListAdapter breedListAdapter;
    private TextView tvHeader;
    private BreedListViewModel viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getIntent().getExtras();
        setContentView(R.layout.activity_breed_list);

        rvBreedList = findViewById(R.id.rvBreed);
        tvHeader = findViewById(R.id.tvHeader);
        breedListAdapter = new BreedListAdapter(this);

        BreedList list = new BreedList();
        String query = "";
        if (args != null) {
            list = (BreedList) args.getSerializable(BREED_LIST_KEY);
            query = args.getString(QUERY_KEY);
        }
        initList();

        viewModel = ViewModelProviders.of(this, new BreedListViewModelFactory(query, list)).get(BreedListViewModel.class);
        viewModel.observeHeaderLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvHeader.setText(s);
            }
        });
        viewModel.observeBreedListLiveData().observe(this, new Observer<List<Breed>>() {
            @Override
            public void onChanged(List<Breed> breeds) {
                breedListAdapter.setItems(breeds);
            }
        });
    }

    private void initList() {
        rvBreedList.setLayoutManager(new LinearLayoutManager(this));
        rvBreedList.setAdapter(breedListAdapter);
    }

    @Override
    public void onBreedClick(Breed breed) {
        //todo:show images here
        //Intent intent = new Intent(BreedListActivity.this, )
    }
}
