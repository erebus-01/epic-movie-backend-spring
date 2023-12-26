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
            <p style="font-size: 22px">Gửi tới: <strong>${name},</strong></p>
            <p>Chúng tôi muốn bày tỏ lòng biết ơn chân thành vì đã lựa chọn dịch vụ xem phim của chúng tôi. Cam kết của bạn đối với hoạt động giải trí hoàn toàn phù hợp với sứ mệnh của chúng tôi là mang lại trải nghiệm xem phim tốt nhất.</p>
            <p>Nếu bạn có bất kỳ đề xuất phim nào hoặc cần hỗ trợ với dịch vụ của chúng tôi, vui lòng liên hệ. Chúng tôi mong muốn nâng cao trải nghiệm xem phim của bạn.</p>
            <p>Trân trọng,<br/>Đội ngũ EPICMOVIE</p>
            <p style="text-align: center;">
                <a href="${url}" style="display: inline-block; padding: 12px 18px; background-color: #3498db; color: #fff; text-decoration: none;">
                    Verify My account
                </a>
            </p>
            <p>Cảm ơn.</p>
        </td>
    </tr>
</table>

</body>
</html>