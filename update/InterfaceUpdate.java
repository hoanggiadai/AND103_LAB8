package vn.daihg1502.and103_lab1.Lab5.update;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.daihg1502.and103_lab1.Lab5.SVResponseSanPham;

public interface InterfaceUpdate {
    @FormUrlEncoded
    @POST("update.php")
    Call<SVResponseSanPham> updateSanPham(
            @Field("MaSP") String MaSP,
            @Field("TenSP") String TenSP,
            @Field("MoTa") String MoTa
    );
}
