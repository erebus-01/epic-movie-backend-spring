package com.programing.vnpay.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "payment")
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_uuid")
    private UUID userId;

    @Column(name = "amount")
    private String amount;

    @Column(name = "bank")
    private String bank;

    @Column(name = "bank_transaction")
    private String bankTranNo;

    @Column(name = "card_type")
    private String cardType;
    private String orderInfo;

    @Column(name = "date")
    private String paymentDate;
    private String tmnCode;
    private String transactionNo;
    private String responseCode;

    private String transactionStatus;
    private String txnRef;

    private String secure;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

}
