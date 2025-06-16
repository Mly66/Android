package cn.nbmly.shop.network;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    // 用户相关接口
    @POST("api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("api/auth/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    // 商品相关接口
    @GET("api/products")
    Call<List<Product>> getProducts(
            @Query("page") int page,
            @Query("size") int size,
            @Query("category") String category);

    @GET("api/products/{id}")
    Call<Product> getProductDetail(@Path("id") long id);

    // 购物车相关接口
    @GET("api/cart")
    Call<List<CartItem>> getCart();

    @POST("api/cart")
    Call<CartItem> addToCart(@Body AddToCartRequest request);

    @DELETE("api/cart/{id}")
    Call<Void> removeFromCart(@Path("id") long id);

    // 订单相关接口
    @GET("api/orders")
    Call<List<Order>> getOrders();

    @POST("api/orders")
    Call<Order> createOrder(@Body CreateOrderRequest request);

    @PUT("api/orders/{id}/status")
    Call<Order> updateOrderStatus(
            @Path("id") long id,
            @Body String status);
}