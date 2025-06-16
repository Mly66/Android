package cn.nbmly.shop.ui.order.checkout.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import cn.nbmly.shop.data.model.CartItem;
import cn.nbmly.shop.databinding.ItemCheckoutProductBinding;

public class CheckoutAdapter extends ListAdapter<CartItem, CheckoutAdapter.ViewHolder> {

    public CheckoutAdapter() {
        super(new DiffUtil.ItemCallback<CartItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull CartItem oldItem, @NonNull CartItem newItem) {
                return oldItem.getProduct().getId() == newItem.getProduct().getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull CartItem oldItem, @NonNull CartItem newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCheckoutProductBinding binding = ItemCheckoutProductBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCheckoutProductBinding binding;

        ViewHolder(ItemCheckoutProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(CartItem item) {
            binding.productNameText.setText(item.getProduct().getName());
            binding.productPriceText.setText(String.format("¥%.2f", item.getProduct().getPrice()));
            binding.quantityText.setText(String.format("x%d", item.getQuantity()));
        }
    }
}