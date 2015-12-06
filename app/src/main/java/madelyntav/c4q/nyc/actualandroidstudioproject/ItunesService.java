package madelyntav.c4q.nyc.actualandroidstudioproject;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by c4q-madelyntavarez on 12/6/15.
 */
public interface ItunesService {
    @GET("/search")
    Call<ResultResponse> getResults(@Query("term") String query, @Query("entity") String movie);
}
