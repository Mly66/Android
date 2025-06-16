package cn.nbmly.shop.data.api;

import java.util.List;
import cn.nbmly.shop.data.model.Order;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiService {
    // ... 其他已有的接口 ...

    @GET("orders")
    Call<List<Order>> getOrders(@Header("Authorization") String token);
}