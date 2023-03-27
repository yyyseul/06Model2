<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>

 	<!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    
    <script type="text/javascript">
    
	    async function authenticate() {
	    	  return axios.post(
	    	    `${config.iamport.apiBaseUrl}/users/getToken`,
	    	    {
	    	      imp_key: "3120528761584822",
	    	      imp_secret: "HeWea1iuXMM1McOJys1PrhUYeMiDc6C3XnEa5h0oIc2bIz3vOo1Kemy2H2xkwmyxSbSz5eZ5l3dNeEUA",
	    	    },
	    	  );
	    	}
	    
	    
	    async function checkBankHolder(
	    		  accessToken,
	    		  { bankCode, bankAccountNumber },
	    		) {
	    		  // get 요청이므로 querystring으로 넘겨준다.
	    		  const query = querystring.stringify({
	    		    bank_code: bankCode,
	    		    bank_num: bankAccountNumber,
	    		  });

	    		  return axios.get(
	    		    `${config.iamport.apiBaseUrl}/vbanks/holder?${query}`,
	    		    {
	    		      headers: {
	    		        Authorization: `Bearer ${accessToken}`, // 인증 요청이므로 헤더에 토큰을 넘겨준다.
	    		      },
	    		    },
	    		  ).catch((error) => {
	    		    /**
	    		     * handling iamport error
	    		     */
	    		    const clientErrors = [400, 404];

	    		    // 400, 404 에러는 유저가 값을 누락한 경우이므로 다른 에러 클래스로 처리한다.
	    		    if (clientErrors.includes(error.response.status)) {
	    		      throw new ApiError(error.response.data.message, 400);
	    		    }

	    		    throw new ApiError('iamport api 에러');
	    		  });
	    		}
    
    
    </script>
<meta charset="EUC-KR">
<title>계좌인증</title>
</head>
<body>

<form name="account" method="post">
	은행코드<input type="text" id=bankCode/>
	계좌번호<input type="text" id="bankAccountNumber"/>
</form>

<button onclick="checkBankHolder()">계좌인증</button>

</body>
</html>