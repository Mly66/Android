package cn.nbmly.shop.ui.order.checkout;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import cn.nbmly.shop.data.model.Address;
import cn.nbmly.shop.data.model.CartItem;
import cn.nbmly.shop.data.repository.OrderRepository;
import cn.nbmly.shop.util.PreferenceManager;

public class CheckoutViewModel extends AndroidViewModel {

    private final OrderRepository orderRepository;
    private final PreferenceManager preferenceManager;
    private final MutableLiveData<List<CartItem>> cartItems = new MutableLiveData<>();
    private final MutableLiveData<Address> selectedAddress = new MutableLiveData<>();

    public CheckoutViewModel(@NonNull Application application) {
        super(application);
        orderRepository = new OrderRepository(application);
        preferenceManager = new PreferenceManager(application);
    }

    public void setCartItems(List<CartItem> items) {
        cartItems.setValue(items);
    }

    public LiveData<List<CartItem>> getCartItems() {
        return cartItems;
    }

    public void setSelectedAddress(Address address) {
        selectedAddress.setValue(address);
    }

    public LiveData<Address> getSelectedAddress() {
        return selectedAddress;
    }

    public double calculateTotalPrice() {
        List<CartItem> items = cartItems.getValue();
        if (items == null) {
            return 0;
        }
        double total = 0;
        for (CartItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public double calculateFreight() {
        // 这里可以根据实际业务逻辑计算运费
        return 10.0;
    }

    public void submitOrder() {
        String token = preferenceManager.getToken();
        List<CartItem> items = cartItems.getValue();
        Address address = selectedAddress.getValue();
        if (token != null && items != null && address != null) {
            orderRepository.createOrder(token, items, address);
        }
    }
}