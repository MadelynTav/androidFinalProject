package madelyntav.c4q.nyc.actualandroidstudioproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    static String base ="https://itunes.apple.com";
    static String type="";
    Button submit;
    static EditText request;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    ListView listView;
    String requestFromUser;
    CustomAdapter customAdapter;
    Retrofit retrofit;
    ItunesService service;
    Call<ResultResponse> results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit= (Button) findViewById(R.id.button);
        request= (EditText) findViewById(R.id.searchBar);
        mDrawerList=(ListView) findViewById(R.id.navList);
        listView= (ListView) findViewById(R.id.listView);
        retrofit = new Retrofit.Builder().baseUrl(base).addConverterFactory(GsonConverterFactory.create()).build();
         service = retrofit.create(ItunesService.class);


        addDrawerItems();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestFromUser = request.getText().toString().trim();
                getApiResults(requestFromUser);

            }
        });

    }

    private void getApiResults(String requested) {

    if (!type.equals("")){
            requested+=type;
        }

        results = service.getResults(requested);
        results.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Response<ResultResponse> response, Retrofit retrofit) {

                ResultResponse resultResponse = response.body();
                List<Result> resultList= resultResponse.results;
                customAdapter= new CustomAdapter(getApplicationContext(),resultList);
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
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                            type="&entity=music";
                        break;
                    }
                    case 1: {
                        type="&entity=software";
                        break;
                    }
                    case 2: {
                        type="&entity=movie";

                        break;
                    }
                    case 3: {
                        type="&entity=podcast";

                        break;
                    }
                    case 4: {
                        type="&entity=musicvideo";

                        break;
                    }
                    case 5: {
                        type="&entity=tvShow";

                        break;
                    }
                    default:
                        break;
                }
            }
        });
    }
}
