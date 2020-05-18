package com.example.dogfinder.presentation.start;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dogfinder.R;
import com.example.dogfinder.presentation.BreedList.BreedListActivity;

import data.model.BreedImageList;
import data.model.BreedList;

public class MainActivity extends AppCompatActivity {

    SelectViewModel viewModel;

    private Spinner srBreeds;
    private Button bnShowDetails;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        srBreeds = findViewById(R.id.spinner);
        bnShowDetails = findViewById(R.id.search_button);

        ArrayAdapter<String> srArrayAdapter = new ArrayAdapter<String>
                (this,
                        R.layout.spinner_style,
                        getResources().getStringArray(R.array.breads)){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextSize(20);
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setGravity(Gravity.CENTER);
                return v;
            }
        };
        srBreeds.setAdapter(srArrayAdapter);
        viewModel = ViewModelProviders.of(this).get(SelectViewModel.class);
        context = this;

        viewModel.observeBreedImageListLiveData().observe(this, new Observer<BreedImageList>() {
            @Override
            public void onChanged(BreedImageList breedImageList) {
                Toast.makeText(context, "in onchanged Names", Toast.LENGTH_LONG).show();
                if(breedImageList.getItems().size() != 0) {
                    //openBreedList(viewModel.getBreedList());
                } else {
                    Toast.makeText(context, "Nothing to show ...", Toast.LENGTH_LONG).show();
                }
            }
        });

        bnShowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getBreedsNames(srBreeds.getSelectedItem().toString().toLowerCase());
                viewModel.getBreedImages(srBreeds.getSelectedItem().toString().toLowerCase());
                openBreedList(viewModel.getBreedList());
            }
        });

    }

    private void openBreedList(BreedList breedList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BreedListActivity.BREED_LIST_KEY, breedList);
        bundle.putString(BreedListActivity.QUERY_KEY, srBreeds.getSelectedItem().toString());
        System.out.println("before intent creation");
        Intent intent = new Intent(MainActivity.this, BreedListActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
