package cn.nbmly.shop.ui.user.address.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import cn.nbmly.shop.data.model.Address;
import cn.nbmly.shop.databinding.ItemAddressBinding;

public class AddressAdapter extends ListAdapter<Address, AddressAdapter.AddressViewHolder> {

    private final OnAddressActionListener listener;

    public AddressAdapter(OnAddressActionListener listener) {
        super(new DiffUtil.ItemCallback<Address>() {
            @Override
            public boolean areItemsTheSame(@NonNull Address oldItem, @NonNull Address newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Address oldItem, @NonNull Address newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAddressBinding binding = ItemAddressBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new AddressViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class AddressViewHolder extends RecyclerView.ViewHolder {
        private final ItemAddressBinding binding;

        AddressViewHolder(ItemAddressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Address address) {
            binding.nameText.setText(address.getName());
            binding.phoneText.setText(address.getPhone());
            binding.addressText.setText(address.getFullAddress());
            binding.defaultSwitch.setChecked(address.isDefault());

            binding.defaultSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (listener != null) {
                    listener.onSetDefault(address, isChecked);
                }
            });

            binding.editButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEdit(address);
                }
            });

            binding.deleteButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onDelete(address);
                }
            });
        }
    }

    public interface OnAddressActionListener {
        void onSetDefault(Address address, boolean isDefault);

        void onEdit(Address address);

        void onDelete(Address address);
    }
}