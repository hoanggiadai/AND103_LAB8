<?php

$res=array();
$s="localhost"; $u="root"; $p=""; $db="AND103";
$con= new mysqli($s, $u, $p, $db);
// Truyền tham số cho biến MaSP
$MaSP=$_POST['MaSP']; 
$TenSP=$_POST['TenSP'];
$MoTa=$_POST['MoTa'];
$sql="INSERT into SanPham (MaSP, TenSP, MoTa) VALUES ('$MaSP', '$TenSP', '$MoTa')";
if($con->query($sql)===TRUE){
    $res['Thành công'] = 1;
    $res['message'] = "POST thành công";
    echo json_encode($res);
}
else {
    $res['Thành công'] = 0;
    $res['message'] = "POST thất bại";
    echo json_encode($res);
}
$con->close();

// http://localhost/AND103/Lab5/insert.php?MaSP=PH02&TenSP=Sản phẩm 2&MoTa=Mô tả sản phẩm 2