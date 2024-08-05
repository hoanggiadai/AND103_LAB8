package vn.daihg1502.and103_lab1.Lab5.delete;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.daihg1502.and103_lab1.Lab5.SVResponseSanPham;

public interface InterfaceDelete {
    @FormUrlEncoded
    @POST("delete.php")
    Call<SVResponseSanPham> deleteSanPham(
            @Field("MaSP") String MaSP
    );
}
