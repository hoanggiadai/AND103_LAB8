<?php
// Tất cả các biến sẽ có dấu $
// Truy cập thuộc tính của đối tượng dùng dấu ->
// Để nối chuỗi dùng dấu .
// printf : echo : in ra màn hình

// Bước 1: Khai báo thông tin kết nối CSDL

$s="localhost"; $u="root"; $p=""; $db="AND103";

// Bước 2: Kết nối cơ sở dữ liệu
$con= new mysqli($s, $u, $p, $db);
// Truyền tham số cho biến MaSP
$MaSP=$_GET['MaSP']; 
$TenSP=$_GET['TenSP'];
$MoTa=$_GET['MoTa'];

// Bước 3: Viết lệnh insert
$sql="INSERT into SanPham (MaSP, TenSP, MoTa) VALUES ('$MaSP', '$TenSP', '$MoTa')";

// Bước 4: Thực hiện insert
if($con->query($sql)===TRUE){
    echo "Insert thành công";
}
else {
    echo "Lỗi: ".$con->error;
}

// Bước 5: Đóng kết nối
$con->close();

// http://localhost/AND103/Lab5/insert.php?MaSP=PH02&TenSP=Sản phẩm 2&MoTa=Mô tả sản phẩm 2