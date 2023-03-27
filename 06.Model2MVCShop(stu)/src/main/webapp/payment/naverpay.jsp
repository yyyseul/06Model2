<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>

	<!--  네이버페이 구현하자-->
	 <!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    
    <script>
        var IMP = window.IMP; 
        IMP.init("imp76832241"); 
        
        var today = new Date();   
        var hours = today.getHours(); // 시
        var minutes = today.getMinutes();  // 분
        var seconds = today.getSeconds();  // 초
        var milliseconds = today.getMilliseconds();
        var makeMerchantUid = hours +  minutes + seconds + milliseconds;
        

        function request_pay({
           
                pg : 'naverpay',
                merchant_uid: "IMP"+makeMerchantUid, 
                name : '아무거나',
                amount : 1,
                buyer_email : 'Iamport@chai.finance',
                buyer_name : '부자',
                buyer_tel : '010-1234-1234',
                buyer_addr : '우주',
                naverPopupMode : true,
            }, function (rsp) { // callback
                if (rsp.success) {
                	alert("네이버결제 성공");
                    console.log(rsp);
                } else {
                	alert("결제 실패");
                    console.log(rsp);
                }
            });
    </script>
<meta charset="EUC-KR">
<title>NaverPay Test</title>
</head>
<body>
	<button onclick="request_pay()">결제하기</button> <!-- 결제하기 버튼 생성 -->
</body>
</html>