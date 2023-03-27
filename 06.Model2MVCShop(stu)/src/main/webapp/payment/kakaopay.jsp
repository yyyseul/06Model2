<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>

	<!--  카카오페이 구현하자-->
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
        

        function requestPay() {
            IMP.request_pay({
                pg : 'kakaopay',
                merchant_uid: "IMP"+makeMerchantUid, 
                name : '아무거나',
                amount : 1,
                buyer_email : 'Iamport@chai.finance',
                buyer_name : '부자',
                buyer_tel : '010-1234-1234',
                buyer_addr : '우주'
            }, function (rsp) { // callback
                if (rsp.success) {
                	alert("카카오결제 성공");
                    console.log(rsp);
                } else {
                	alert("결제 실패");
                    console.log(rsp);
                }
            });
        }
    </script>
<meta charset="EUC-KR">
<title>KakaoPay Test</title>
</head>
<body>
	<button onclick="requestPay()">결제하기</button> <!-- 결제하기 버튼 생성 -->
</body>
</html>