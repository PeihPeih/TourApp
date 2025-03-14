package com.example.tour;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour.model.Tour;
import com.example.tour.model.TourAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TourAdapter.TourItemListener{
    private Spinner sp;
    private RecyclerView recyclerView;
    private EditText eName, eDuration;
    private Button btnAdd, btnUpdate;
    private TourAdapter adapter;
    private SearchView searchView;
    private int pcurr;
    private int[] imgs = {R.drawable.maybay, R.drawable.oto, R.drawable.xemay};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapter = new TourAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager) ;
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tour tour = new Tour();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String duration = eDuration.getText().toString();
                int img = R.drawable.maybay;
                double price = 0;
                try {
                    img = imgs[Integer.parseInt(i)];
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(name)){
                    eName.setError("Name cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(duration))
                {
                    eDuration.setError("Duration cannot be empty");
                    return;
                }
                tour.setImg(img);
                tour.setName(name);
                tour.setDuration(duration);
                adapter.add(tour);

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tour tour = new Tour();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String duration = eDuration.getText().toString();
                int img = R.drawable.maybay;
                double price = 0;
                try {
                    img = imgs[Integer.parseInt(i)];
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(name)){
                    eName.setError("Name cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(duration))
                {
                    eDuration.setError("Duration cannot be empty");
                    return;
                }
                tour.setImg(img);
                tour.setName(name);
                tour.setDuration(duration);
                adapter.update(pcurr, tour);
                btnUpdate.setEnabled(false);
                btnAdd.setEnabled(true);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterTours(newText);
                return false;
            }
        });
    }

    private void filterTours(String newText){
        List<Tour> filteredList = new ArrayList<>();
        for(Tour tour : adapter.getBackup()){
            if (tour.getName().toLowerCase().contains(newText.toLowerCase()) || tour.getDuration().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(tour);
            }
        }
        adapter.setmList(filteredList);
    }

    private void initView(){
        sp = findViewById(R.id.img);
        SpinnerAdapter adapter = new com.example.tour.model.SpinnerAdapter(this);
        sp.setAdapter(adapter);
        recyclerView=findViewById(R.id.recycleView);
        eName = findViewById(R.id.name);
        eDuration = findViewById(R.id.duration);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setEnabled(false);
        searchView = findViewById(R.id.search);
    }

    @Override
    public void onItemClick(View view, int position) {
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
        pcurr = position;
        Tour tour = adapter.getItem(position);
        int img = tour.getImg();
        int p = 0;
        for (int i = 0; i < imgs.length; i++) {
            if (img == imgs[i]) {
                p = i;
                break;
            }
        }
            sp.setSelection(p);
            eName.setText(tour.getName());
            eDuration.setText(tour.getDuration());

    }
}