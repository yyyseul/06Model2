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
	    		  // get ��û�̹Ƿ� querystring���� �Ѱ��ش�.
	    		  const query = querystring.stringify({
	    		    bank_code: bankCode,
	    		    bank_num: bankAccountNumber,
	    		  });

	    		  return axios.get(
	    		    `${config.iamport.apiBaseUrl}/vbanks/holder?${query}`,
	    		    {
	    		      headers: {
	    		        Authorization: `Bearer ${accessToken}`, // ���� ��û�̹Ƿ� ����� ��ū�� �Ѱ��ش�.
	    		      },
	    		    },
	    		  ).catch((error) => {
	    		    /**
	    		     * handling iamport error
	    		     */
	    		    const clientErrors = [400, 404];

	    		    // 400, 404 ������ ������ ���� ������ ����̹Ƿ� �ٸ� ���� Ŭ������ ó���Ѵ�.
	    		    if (clientErrors.includes(error.response.status)) {
	    		      throw new ApiError(error.response.data.message, 400);
	    		    }

	    		    throw new ApiError('iamport api ����');
	    		  });
	    		}
    
    
    </script>
<meta charset="EUC-KR">
<title>��������</title>
</head>
<body>

<form name="account" method="post">
	�����ڵ�<input type="text" id=bankCode/>
	���¹�ȣ<input type="text" id="bankAccountNumber"/>
</form>

<button onclick="checkBankHolder()">��������</button>

</body>
</html>