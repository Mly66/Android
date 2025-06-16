package cn.nbmly.shop.ui.order;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;
import cn.nbmly.shop.data.model.Order;
import cn.nbmly.shop.data.repository.OrderRepository;

public class OrderHistoryViewModel extends AndroidViewModel {

    private final OrderRepository orderRepository;
    private final MutableLiveData<List<Order>> orders = new MutableLiveData<>(new ArrayList<>());

    public OrderHistoryViewModel(@NonNull Application application) {
        super(application);
        orderRepository = new OrderRepository(application);
    }

    public LiveData<List<Order>> getOrders() {
        return orders;
    }

    public void loadOrders() {
        orderRepository.getOrders().observeForever(ordersList -> {
            orders.setValue(ordersList);
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // 清理资源
    }
}