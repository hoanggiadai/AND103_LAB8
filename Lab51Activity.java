package vn.daihg1502.and103_lab1.Lab5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.daihg1502.and103_lab1.Lab5.delete.InterfaceDelete;
import vn.daihg1502.and103_lab1.Lab5.select.IntefaceSelect;
import vn.daihg1502.and103_lab1.Lab5.select.SvrResponseSelect;
import vn.daihg1502.and103_lab1.Lab5.update.InterfaceUpdate;
import vn.daihg1502.and103_lab1.R;

public class Lab51Activity extends AppCompatActivity {

    EditText txt1, txt2, txt3;
    TextView tvKQ;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab51);

        txt1 = findViewById(R.id.lab51Txt1);
        txt2 = findViewById(R.id.lan51Txt2);
        txt3 = findViewById(R.id.lab51Txt3);
        tvKQ = findViewById(R.id.lab51KQ);
        btn1 = findViewById(R.id.lab51btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                insertData(txt1, txt2, txt3, tvKQ);
//                selectData();
//                deleteData(txt1);
                updateData(txt1, txt2, txt3, tvKQ);

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void insertData(EditText txt1, EditText txt2, EditText txt3, TextView tvKQ) {
        // B1: Tạo đối tượng chứa dữ liệu
        SanPham s = new SanPham(txt1.getText().toString(),
                                txt2.getText().toString(),
                                txt3.getText().toString());

        // B2: Tạo đối tượng Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.4/AND103/Lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // B3: Gọi hàm insert
        InterfaceInsertSanPham insertSanPham = retrofit.create(InterfaceInsertSanPham.class);

        // Chuẩn bị hàm
        Call<SVResponseSanPham> call =
                insertSanPham.insertSanPham(s.getMaSP(), s.getTenSP(), s.getMoTa());

        // Thực thi hàm
        call.enqueue(new Callback<SVResponseSanPham>() {

            // Thành công
            @Override
            public void onResponse(Call<SVResponseSanPham> call, Response<SVResponseSanPham> response) {
                SVResponseSanPham res = response.body();
                tvKQ.setText(res.getMessage());
            }

            // Thất bại
            @Override
            public void onFailure(Call<SVResponseSanPham> call, Throwable t) {
                tvKQ.setText(t.getMessage());
            }
        });

    }

    String strKQ = "";
    List<SanPham> ls;
    private void selectData(){
        strKQ="";

        // B1: Tạo đối tượng Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.4/AND103/Lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // B2: Gọi hàm select trong Interface
        // B2.1: Tạo đối tượng
        IntefaceSelect intefaceSelect = retrofit.create(IntefaceSelect.class);

        //B2.2: Chuẩn bị hàm
        Call<SvrResponseSelect> call = intefaceSelect.selectSanPham();

        // B2.3: Thực thi request
        call.enqueue(new Callback<SvrResponseSelect>() {
            @Override
            public void onResponse(Call<SvrResponseSelect> call, Response<SvrResponseSelect> response) {
                SvrResponseSelect res = response.body(); // Lấy kết quả từ server trả về
                // Chuyển đô kết quả sang dạng list
                ls = new ArrayList<>(Arrays.asList(res.getProducts()));
                // Đọc list
                for (SanPham p: ls){
                    strKQ+="MaSP: "+p.getMaSP()+"; TenSP: "+p.getTenSP()+"; MoTa: "+p.getMoTa()+"\n";
                }
                tvKQ.setText(strKQ);
            }

            @Override
            public void onFailure(Call<SvrResponseSelect> call, Throwable t) {
                tvKQ.setText(t.getMessage());
            }
        });
    }

    private void deleteData(EditText txt1) {

        // B2: Tạo đối tượng Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.4/AND103/Lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // B3: Gọi hàm insert
        InterfaceDelete deleteSanPham = retrofit.create(InterfaceDelete.class);

        // Chuẩn bị hàm
        Call<SVResponseSanPham> call =
                deleteSanPham.deleteSanPham(txt1.getText().toString());

        // Thực thi hàm
        call.enqueue(new Callback<SVResponseSanPham>() {

            // Thành công
            @Override
            public void onResponse(Call<SVResponseSanPham> call, Response<SVResponseSanPham> response) {
                SVResponseSanPham res = response.body();
                tvKQ.setText(res.getMessage());
            }

            // Thất bại
            @Override
            public void onFailure(Call<SVResponseSanPham> call, Throwable t) {
                tvKQ.setText(t.getMessage());
            }
        });

    }

    private void updateData(EditText txt1, EditText txt2, EditText txt3, TextView tvKQ) {
        // B1: Tạo đối tượng chứa dữ liệu
        SanPham s = new SanPham(txt1.getText().toString(),
                txt2.getText().toString(),
                txt3.getText().toString());

        // B2: Tạo đối tượng Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.4/AND103/Lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // B3: Gọi hàm insert
        InterfaceUpdate updateSanPham = retrofit.create(InterfaceUpdate.class);

        // Chuẩn bị hàm
        Call<SVResponseSanPham> call =
                updateSanPham.updateSanPham(s.getMaSP(), s.getTenSP(), s.getMoTa());

        // Thực thi hàm
        call.enqueue(new Callback<SVResponseSanPham>() {

            // Thành công
            @Override
            public void onResponse(Call<SVResponseSanPham> call, Response<SVResponseSanPham> response) {
                SVResponseSanPham res = response.body();
                tvKQ.setText(res.getMessage());
            }

            // Thất bại
            @Override
            public void onFailure(Call<SVResponseSanPham> call, Throwable t) {
                tvKQ.setText(t.getMessage());
            }
        });

    }
}