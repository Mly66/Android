package cn.nbmly.shop.ui.user.address.edit;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import cn.nbmly.shop.data.model.Address;
import cn.nbmly.shop.data.repository.AddressRepository;
import cn.nbmly.shop.util.PreferenceManager;

public class AddressEditViewModel extends AndroidViewModel {

    private final AddressRepository addressRepository;
    private final PreferenceManager preferenceManager;

    public AddressEditViewModel(@NonNull Application application) {
        super(application);
        addressRepository = new AddressRepository(application);
        preferenceManager = new PreferenceManager(application);
    }

    public void saveAddress(Address address) {
        String token = preferenceManager.getToken();
        if (token != null) {
            if (address.getId() == 0) {
                addressRepository.addAddress(token, address);
            } else {
                addressRepository.updateAddress(token, address);
            }
        }
    }
}