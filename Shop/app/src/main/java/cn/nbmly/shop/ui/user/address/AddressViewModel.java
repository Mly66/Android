package cn.nbmly.shop.ui.user.address;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import cn.nbmly.shop.data.model.Address;
import cn.nbmly.shop.data.repository.AddressRepository;
import cn.nbmly.shop.util.PreferenceManager;

public class AddressViewModel extends AndroidViewModel {

    private final AddressRepository addressRepository;
    private final PreferenceManager preferenceManager;
    private final MutableLiveData<List<Address>> addresses = new MutableLiveData<>();

    public AddressViewModel(@NonNull Application application) {
        super(application);
        addressRepository = new AddressRepository(application);
        preferenceManager = new PreferenceManager(application);
        loadAddresses();
    }

    public LiveData<List<Address>> getAddresses() {
        return addresses;
    }

    private void loadAddresses() {
        String token = preferenceManager.getToken();
        if (token != null) {
            addressRepository.getAddresses(token).observeForever(addressList -> {
                addresses.setValue(addressList);
            });
        }
    }

    public void setDefaultAddress(Address address, boolean isDefault) {
        String token = preferenceManager.getToken();
        if (token != null) {
            addressRepository.setDefaultAddress(token, address.getId(), isDefault);
        }
    }

    public void deleteAddress(Address address) {
        String token = preferenceManager.getToken();
        if (token != null) {
            addressRepository.deleteAddress(token, address.getId());
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // 清理资源
    }
}