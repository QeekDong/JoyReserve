package com.joyreserve.reserve;

import com.joyreserve.reserve.model.ReserveResult;
import com.joyreserve.reserve.model.RoomResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Cloud on 2017/5/12.
 */

public interface APIUtils {
    @GET("check_room_login?")
    Observable<RoomResult>getRoomInfo(@Query("room_id") String room_id,
                                      @Query("auth_code") String auth_code);

    @GET("get_one_day_one_room_state?")
    Observable<ReserveResult>getReserveInfo(@Query("room_id") String room_id,
                                            @Query("auth_code") String auth_code,
                                            @Query("query_date") String query_date);


}
