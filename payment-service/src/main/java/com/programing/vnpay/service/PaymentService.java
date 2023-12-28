package com.programing.vnpay.service;

import com.programing.vnpay.config.Config;
import com.programing.vnpay.dto.UserDTO;
import com.programing.vnpay.model.PaymentInfo;
import com.programing.vnpay.repository.PaymentRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;

    public int orderReturn(HttpServletRequest request) {
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = Config.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    public String savePayment(
            String amount,
            String bank,
            String bankTranNo,
            String cardType,
            String orderInfo,
            String paymentDate,
            String tmnCode,
            String transactionNo,
            String responseCode,
            String transactionStatus,
            String txnRef,
            String secure,
            UserDTO user
    ) {

        PaymentInfo paymentInfo = PaymentInfo.builder()
                .userId(user.getUserId())
                .amount(amount)
                .bank(bank)
                .bankTranNo(bankTranNo)
                .cardType(cardType)
                .orderInfo(orderInfo)
                .paymentDate(paymentDate)
                .tmnCode(tmnCode)
                .transactionNo(transactionNo)
                .responseCode(responseCode)
                .transactionStatus(transactionStatus)
                .txnRef(txnRef)
                .secure(secure)
                .build();

        repository.save(paymentInfo);


        return "Payment invoice has been saved";
    }

}
