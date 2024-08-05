package vn.daihg1502.and103_lab1.Lab5;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceInsertSanPham {
    @FormUrlEncoded
    @POST("insert1.php")
    Call<SVResponseSanPham> insertSanPham(
            @Field("MaSP") String MaSP,
            @Field("TenSP") String TenSP,
            @Field("MoTa") String MoTa
    );
}
