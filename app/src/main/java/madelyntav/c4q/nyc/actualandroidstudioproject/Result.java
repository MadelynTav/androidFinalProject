package madelyntav.c4q.nyc.actualandroidstudioproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c4q-madelyntavarez on 12/6/15.
 */
public class Result {
    String artistName;
    String artworkUrl100;
    String trackName;
    String releaseDate;
}

class ResultResponse{
    List<Result> results;

    public ResultResponse(){
        results= new ArrayList<Result>();
    }
}
