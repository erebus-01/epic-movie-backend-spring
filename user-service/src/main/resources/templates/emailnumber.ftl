<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

<table align="center" border="0" cellpadding="0" cellspacing="0" width="600" style="border-collapse: collapse;">
    <tr>
        <td align="center" bgcolor="#000000" style="padding: 40px 0 30px 0;">
            <span style="font-size: 58px; font-weight: 800; color: #B20710;">
                EPICMOVIE
            </span>
            <img src="https://drive.google.com/uc?export=view&id=1QxK9WS3rOZ1pXTuS3NrfqUc2W9Oq4UC7" alt="logo-epicmovie" style="width: 60px; margin-left: 5px;" />
        </td>
    </tr>


    <tr>
        <td bgcolor="#eaeaea" style="padding: 40px 30px 40px 30px;">
            <p style="font-size: 22px">Gửi tới: <strong style="text-transform: capitalize">${name},</strong></p>

            <p align="center" style="font-size: 30px; font-weight: bold">Nhập mã để thực hiện đổi mật khẩu</p>

            <p align="center" style="font-size: 50px; font-weight: bold; letter-spacing: 3rem; padding-left: 3rem">${codes}</p>

            <p align="center" style="font-size: 16px; font-weight: 500">Nếu bạn không phải là người gửi yêu cầu này, hãy khóa tài khoản ngay lập tức để tránh việc bị truy cập trái phép. Đọc Bảo Mật Tài Khoản để biết các mẹo về mật khẩu có tính bảo mật cao.</p>

            <p>Trân trọng,<br/>Đội ngũ <strong>EPICMOVIE</strong>.</p>
            <p>Cảm ơn.</p>
        </td>
    </tr>
</table>

</body>
</html>