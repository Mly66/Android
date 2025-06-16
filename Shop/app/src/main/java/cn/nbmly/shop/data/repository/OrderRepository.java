package cn.nbmly.shop.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import cn.nbmly.shop.data.api.ApiService;
import cn.nbmly.shop.data.api.RetrofitClient;
import cn.nbmly.shop.data.model.Order;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {
    private final ApiService apiService;
    private final MutableLiveData<List<Order>> ordersLiveData;

    public OrderRepository(Application application) {
        apiService = RetrofitClient.getInstance().getApiService();
        ordersLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Order>> getOrders() {
        loadOrders();
        return ordersLiveData;
    }

    private void loadOrders() {
        apiService.getOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ordersLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                // 处理错误
            }
        });
    }
}