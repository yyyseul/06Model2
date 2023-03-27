<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<!-- �̴Ͻý� ���� -->
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
        

        function requestPay() {
            IMP.request_pay({
                pg : 'html5_inicis',
                pay_method : 'card',
                merchant_uid: "IMP"+makeMerchantUid, 
                name : '��� 10kg',
                amount : 100,
                buyer_email : 'Iamport@chai.finance',
                buyer_name : '������Ʈ ���������',
                buyer_tel : '010-1234-5678',
                buyer_addr : '����Ư���� ������ �Ｚ��',
                buyer_postcode : '123-456'
            }, function (rsp) { // callback
                if (rsp.success) {
                    console.log(rsp);
                } else {
                    console.log(rsp);
                }
            });
        }
    </script>
    <meta charset="UTF-8">
    <title>Inicis Sample Payment</title>
</head>
<body>
    <button onclick="requestPay()">�����ϱ�</button> <!-- �����ϱ� ��ư ���� -->
</body>
</html>