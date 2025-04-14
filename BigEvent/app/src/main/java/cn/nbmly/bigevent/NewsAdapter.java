package cn.nbmly.bigevent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_NO_IMAGE = 0;
    private static final int VIEW_TYPE_SINGLE_IMAGE = 1;
    private static final int VIEW_TYPE_TWO_THREE_IMAGES = 2;
    private static final int VIEW_TYPE_FOUR_IMAGES = 3;
    private static final int VIEW_TYPE_MANY_IMAGES = 4;

    private List<NewsItem> newsItems = new ArrayList<>();
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<NewsItem> data) {
        this.newsItems = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        NewsItem item = newsItems.get(position);
        int imageCount = item.getImageCount();

        if (imageCount == 0) {
            return VIEW_TYPE_NO_IMAGE;
        } else if (imageCount == 1) {
            return VIEW_TYPE_SINGLE_IMAGE;
        } else if (imageCount <= 3) {
            return VIEW_TYPE_TWO_THREE_IMAGES;
        } else if (imageCount == 4) {
            return VIEW_TYPE_FOUR_IMAGES;
        } else {
            return VIEW_TYPE_MANY_IMAGES;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case VIEW_TYPE_NO_IMAGE:
                View noImageView = inflater.inflate(R.layout.item_news_no_image, parent, false);
                return new NoImageViewHolder(noImageView);

            case VIEW_TYPE_SINGLE_IMAGE:
                View singleImageView = inflater.inflate(R.layout.item_news_single_image, parent, false);
                return new SingleImageViewHolder(singleImageView);

            case VIEW_TYPE_TWO_THREE_IMAGES:
                View twoThreeImagesView = inflater.inflate(R.layout.item_news_two_three_images, parent, false);
                return new TwoThreeImagesViewHolder(twoThreeImagesView);

            case VIEW_TYPE_FOUR_IMAGES:
                View fourImagesView = inflater.inflate(R.layout.item_news_four_images, parent, false);
                return new FourImagesViewHolder(fourImagesView);

            case VIEW_TYPE_MANY_IMAGES:
            default:
                View manyImagesView = inflater.inflate(R.layout.item_news_many_images, parent, false);
                return new ManyImagesViewHolder(manyImagesView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsItem item = newsItems.get(position);

        if (holder instanceof NoImageViewHolder) {
            ((NoImageViewHolder) holder).bind(item);
        } else if (holder instanceof SingleImageViewHolder) {
            ((SingleImageViewHolder) holder).bind(item);
        } else if (holder instanceof TwoThreeImagesViewHolder) {
            ((TwoThreeImagesViewHolder) holder).bind(item);
        } else if (holder instanceof FourImagesViewHolder) {
            ((FourImagesViewHolder) holder).bind(item);
        } else if (holder instanceof ManyImagesViewHolder) {
            ((ManyImagesViewHolder) holder).bind(item);
        }
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        TextView titleView, userNameView, commentView, timeView;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.news_title);
            userNameView = itemView.findViewById(R.id.user_name);
            commentView = itemView.findViewById(R.id.event_num);
            timeView = itemView.findViewById(R.id.publish_time);
        }

        // 绑定基本数据
        public void bindBasicInfo(NewsItem item) {
            titleView.setText(item.getTitle());
            userNameView.setText(item.getUserName());
            commentView.setText(item.getCommentCount() + "评论");
            timeView.setText(item.getPublishTime());
        }

        // 加载图片
        protected void loadImage(ImageView imageView, String url) {
            // 使用简化的Glide加载方式
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .centerCrop()
                    .into(imageView);
        }
    }

    // 无图片的ViewHolder
    class NoImageViewHolder extends BaseViewHolder {
        public NoImageViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(NewsItem item) {
            bindBasicInfo(item);
        }
    }

    // 单张图片的ViewHolder
    class SingleImageViewHolder extends BaseViewHolder {
        ImageView rightImage;

        public SingleImageViewHolder(@NonNull View itemView) {
            super(itemView);
            rightImage = itemView.findViewById(R.id.right_image);
        }

        public void bind(NewsItem item) {
            bindBasicInfo(item);

            // 加载右侧图片
            if (item.getImageCount() > 0) {
                loadImage(rightImage, item.getImageUrls().get(0));
            }
        }
    }

    // 2-3张图片的ViewHolder
    class TwoThreeImagesViewHolder extends BaseViewHolder {
        ImageView[] horizontalImageViews = new ImageView[3];

        public TwoThreeImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontalImageViews[0] = itemView.findViewById(R.id.image_h1);
            horizontalImageViews[1] = itemView.findViewById(R.id.image_h2);
            horizontalImageViews[2] = itemView.findViewById(R.id.image_h3);
        }

        public void bind(NewsItem item) {
            bindBasicInfo(item);

            int imageCount = Math.min(item.getImageCount(), 3);
            List<String> imageUrls = item.getImageUrls();

            // 显示2-3张图片
            for (int i = 0; i < horizontalImageViews.length; i++) {
                if (i < imageCount) {
                    horizontalImageViews[i].setVisibility(View.VISIBLE);
                    loadImage(horizontalImageViews[i], imageUrls.get(i));
                } else {
                    horizontalImageViews[i].setVisibility(View.GONE);
                }
            }
        }
    }

    // 4张图片的ViewHolder
    class FourImagesViewHolder extends BaseViewHolder {
        ImageView image1, image2, image3, image4;

        public FourImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.image_grid1);
            image2 = itemView.findViewById(R.id.image_grid2);
            image3 = itemView.findViewById(R.id.image_grid3);
            image4 = itemView.findViewById(R.id.image_grid4);
        }

        public void bind(NewsItem item) {
            bindBasicInfo(item);

            List<String> imageUrls = item.getImageUrls();

            // 加载4张图片
            loadImage(image1, imageUrls.get(0));
            loadImage(image2, imageUrls.get(1));
            loadImage(image3, imageUrls.get(2));
            loadImage(image4, imageUrls.get(3));
        }
    }

    // 更多图片的ViewHolder
    class ManyImagesViewHolder extends BaseViewHolder {
        GridLayout gridImages;

        public ManyImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            gridImages = itemView.findViewById(R.id.grid_images);
        }

        public void bind(NewsItem item) {
            bindBasicInfo(item);

            // 清除之前的视图
            gridImages.removeAllViews();

            List<String> imageUrls = item.getImageUrls();
            int displayCount = Math.min(imageUrls.size(), 9); // 最多显示9张图片

            // 计算每个图片的尺寸
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            int contentWidth = screenWidth - 24; // 减去padding
            int imageSize = (contentWidth / 3) - 4; // 三列，减去边距

            // 动态添加图片到网格
            for (int i = 0; i < displayCount; i++) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = imageSize;
                params.height = imageSize;
                params.columnSpec = GridLayout.spec(i % 3, 1f);
                params.rowSpec = GridLayout.spec(i / 3);
                params.setMargins(2, 2, 2, 2);
                imageView.setLayoutParams(params);

                loadImage(imageView, imageUrls.get(i));
                gridImages.addView(imageView);
            }
        }
    }
}