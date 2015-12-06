package madelyntav.c4q.nyc.actualandroidstudioproject;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    static String base = "https://itunes.apple.com";
    String type = "";
    Button submit;
    EditText request;
    ListView mDrawerList;
    ArrayAdapter<String> mAdapter;
    ListView listView;
    String requestFromUser;
    CustomAdapter customAdapter;
    Retrofit retrofit;
    ItunesService service;
    Call<ResultResponse> results;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = (Button) findViewById(R.id.button);
        request = (EditText) findViewById(R.id.searchBar);
        mDrawerList = (ListView) findViewById(R.id.navList);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.listView);
        retrofit = new Retrofit.Builder().baseUrl(base).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(ItunesService.class);
        addDrawerItems();


//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                requestFromUser = request.getText().toString().trim();
//                getApiResults(requestFromUser);
//            }
//        });

    }

    public void getApiResults(View view) {
        requestFromUser = request.getText().toString().trim();
        //requested=requested + type;
        Log.d(requestFromUser, type);

        results = service.getResults(requestFromUser,type);
        results.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Response<ResultResponse> response, Retrofit retrofit) {

                ResultResponse resultResponse = response.body();
                List<Result> resultList = resultResponse.results;
                customAdapter = new CustomAdapter(getApplicationContext(), resultList);
                listView.setAdapter(customAdapter);
                type="";
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void addDrawerItems() {

        String[] osArray = {"Music", "Software", "Movies", "Podcasts", "Videos", "TV Shows"};
        mAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, osArray);

        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        type = "music";
                        drawerLayout.closeDrawers();

                        break;
                    }
                    case 1: {
                        type = "software";
                        drawerLayout.closeDrawers();

                        break;
                    }
                    case 2: {
                        type = "movie";
                        drawerLayout.closeDrawers();

                        break;
                    }
                    case 3: {
                        type = "podcast";
                        drawerLayout.closeDrawers();

                        break;
                    }
                    case 4: {
                        type = "musicvideo";
                        drawerLayout.closeDrawers();

                        break;
                    }
                    case 5: {
                        type = "tvShow";
                        drawerLayout.closeDrawers();

                        break;
                    }
                    default:

                        break;
                }
            }
        });
    }
}
