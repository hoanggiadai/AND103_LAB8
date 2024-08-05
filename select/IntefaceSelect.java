package vn.daihg1502.and103_lab1.Lab5.select;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IntefaceSelect {
    @GET("select.php")
    Call<SvrResponseSelect> selectSanPham();
}
