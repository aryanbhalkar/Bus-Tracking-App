package com.example.mega;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Routes extends AppCompatActivity {

    private ListView routesListView, stopsListView;
    private TextView currentRouteText;
    private EditText searchBar;

    // Data for routes and their stops
    private final HashMap<String, List<String>> routeStopsMap = new HashMap<>();
    private final List<String> routes = new ArrayList<>();
    private final List<String> stops = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_routes);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        routesListView = findViewById(R.id.routesListView);
        stopsListView = findViewById(R.id.stopsListView);
        currentRouteText = findViewById(R.id.currentRouteText);
        searchBar = findViewById(R.id.searchBar);


        // Initialize route and stops data
        initializeData();

        ArrayAdapter<String> routesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, routes);
        routesListView.setAdapter(routesAdapter);

        // Set up item click listener for routes
        routesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedRoute = routes.get(position);
                currentRouteText.setText(String.format("Stops for %s", selectedRoute));

                // Update stops ListView
                List<String> stops = routeStopsMap.get(selectedRoute);
                if (stops != null) {
                    ArrayAdapter<String> stopsAdapter = new ArrayAdapter<>(Routes.this, android.R.layout.simple_list_item_1, stops);
                    stopsListView.setAdapter(stopsAdapter);
                }
            }
        });
        // Add TextWatcher to EditText for real-time search
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // No need to implement this method for our purpose
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Trigger search when the text changes
                performSearch(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No need to implement this method for our purpose
            }
        });
    }

    private void initializeData() {
        // Adding routes
        routes.add("Kagal 01");
        routes.add("Kagal 02");
        routes.add("Kasaba Bawada 01");
        routes.add("Kasaba Bawada 02");
        routes.add("Rajarampuri 01");
        routes.add("Rajarampuri 02");
        routes.add("Gangavesh");
        routes.add("Vadanage");
        routes.add("Kalamba 01");
        routes.add("Kalamba 02");
        routes.add("Valivade");
        routes.add("Phulewadi");
        routes.add("Ichalkaranji 01");
        routes.add("Ichalkaranji 02");
        routes.add("Gangavesh");

        // Adding stops for each route
        routeStopsMap.put("Kagal 01", List.of(
                "Kagal Stand", "Panchayat Samiti", "Ujalaiwadi",
                "Airport Road", "Sarnobatwadi"
        ));

        routeStopsMap.put("Kagal 02", List.of(
                "Silver Zone", "Yalgud", "Ranadivewadi",
                "Kasaba Sangaon", "Kaneriwadi", "Gokul Shirgaon"
        ));

        routeStopsMap.put("Kasaba Bawada 01", List.of(
                "Medical College", "Pinjar Galli"
        ));

        routeStopsMap.put("Kasaba Bawada 02", List.of(
                "Sugar Mill", "Naik Dhaba", "Ram Nagar-Shiye",
                "Shiye Phata", "Toap", "Ambap Phata"
        ));

        routeStopsMap.put("Rajarampuri 01", List.of(
                "Mahasainik Darbar Hall", "Dhairyaprasad Hall",
                "Vrushali Hotel", "Sayaji Hotel", "BSNL Tower",
                "Takala", "Rajarampuri 1st Lane",
                "Rajarampuri 6th Lane", "Rajarampuri 13th Lane"
        ));

        routeStopsMap.put("Rajarampuri 02", List.of(
                "Gokhale College", "Savitribai Phule Hospital",
                "Reliance Mall", "Titan Showroom",
                "Gokul Hotel", "Lisha Hotel",
                "Mukta Sainik Vasahat", "Market Yard",
                "Sangli Phata"
        ));

        routeStopsMap.put("Gangavesh", List.of(
                "Gangavesh", "Malkar Tikti", "CPR",
                "Mahaveer College", "Raman Mala", "Bhagawa Chowk"
        ));

        routeStopsMap.put("Vadanage", List.of(
                "Rankala Stand", "Panchganga Bridge", "Vadanage Phata",
                "Parvati Mandir", "Super Bazar", "Nigave",
                "Bhuyewadi", "Bhuye", "Jatharwadi", "Shiye",
                "Shiye Phata"
        ));

        routeStopsMap.put("Kalamba 01", List.of(
                "Kalamba", "AmrutSiddhi Mangal Karyalaya",
                "Sai Mandir", "Hockey Stadium", "Shenda Park Chowk",
                "NCC Bhavan"
        ));

        routeStopsMap.put("Kalamba 02", List.of(
                "ITI", "Sambhaji Nagar", "Devkar Panand",
                "Juna Washi Naka"
        ));

        routeStopsMap.put("Valivade", List.of(
                "Valivade", "Chinchwad", "Gandhinagar",
                "Nigadewadi", "Tawade Hotel", "Shiroli",
                "Nagaon Phata"
        ));

        routeStopsMap.put("Phulewadi", List.of(
                "Gangai Lawn", "Bhagava Chowk", "Fire Brigade",
                "Shalini Palace", "Jawalacha Ganpati"
        ));

        routeStopsMap.put("Ichalkaranji 01", List.of(
                "Jambhali", "Yadrav Phata", "Shahapur",
                "Nagarpalika", "Deccan", "Sanjay Foundry",
                "Korochi", "Hatkangale"
        ));

        routeStopsMap.put("Ichalkaranji 02", List.of(
                "Shiradwad", "Nadivesh Naka", "Sambhaji Chowk, Ichalkaranji",
                "Ichalkaranji Stand", "Shahu Putala"
        ));
    }

    private void performSearch(String query) {
        stops.clear(); // Clear previous search results

        if (query.isEmpty()) {
            // If the search query is empty, reset TextView and clear stops ListView
            currentRouteText.setText("Select a Route");

            // Clear the stops ListView
            ArrayAdapter<String> stopsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stops);
            stopsListView.setAdapter(stopsAdapter);
            return;
        }

        // Search for stops that match the query
        boolean stopFound = false;
        for (String route : routeStopsMap.keySet()) {
            List<String> routeStops = routeStopsMap.get(route);
            for (String stop : routeStops) {
                if (stop.toLowerCase().contains(query.toLowerCase())) {
                    stops.add(stop);
                    stopFound = true;
                }
            }
        }

        if (stopFound) {
            // Update stops ListView
            ArrayAdapter<String> stopsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stops);
            stopsListView.setAdapter(stopsAdapter);

            // Select the route for the first matching stop
            String firstMatchingStop = stops.get(0);
            selectRouteForStop(firstMatchingStop);
        } else {
            ArrayAdapter<String> stopsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stops);
            stopsListView.setAdapter(stopsAdapter);
        }
    }

    // Method to select the route that contains the stop
    private void selectRouteForStop(String stop) {
        for (String route : routeStopsMap.keySet()) {
            List<String> routeStops = routeStopsMap.get(route);
            if (routeStops.contains(stop)) {
                currentRouteText.setText(String.format("Stops for %s", route));

                // Update the routes ListView to highlight the selected route
                for (int i = 0; i < routesListView.getCount(); i++) {
                    if (routesListView.getItemAtPosition(i).equals(route)) {
                        routesListView.setSelection(i);  // Pre-select the route
                        break;
                    }
                }
                break;
            }
        }
    }
}