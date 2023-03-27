<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>

	<!--  ���̹����� ��������-->
	 <!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    
    <script>
        var IMP = window.IMP; 
        IMP.init("imp76832241"); 
        
        var today = new Date();   
        var hours = today.getHours(); // ��
        var minutes = today.getMinutes();  // ��
        var seconds = today.getSeconds();  // ��
        var milliseconds = today.getMilliseconds();
        var makeMerchantUid = hours +  minutes + seconds + milliseconds;
        

        function request_pay({
           
                pg : 'naverpay',
                merchant_uid: "IMP"+makeMerchantUid, 
                name : '�ƹ��ų�',
                amount : 1,
                buyer_email : 'Iamport@chai.finance',
                buyer_name : '����',
                buyer_tel : '010-1234-1234',
                buyer_addr : '����',
                naverPopupMode : true,
            }, function (rsp) { // callback
                if (rsp.success) {
                	alert("���̹����� ����");
                    console.log(rsp);
                } else {
                	alert("���� ����");
                    console.log(rsp);
                }
            });
    </script>
<meta charset="EUC-KR">
<title>NaverPay Test</title>
</head>
<body>
	<button onclick="request_pay()">�����ϱ�</button> <!-- �����ϱ� ��ư ���� -->
</body>
</html>