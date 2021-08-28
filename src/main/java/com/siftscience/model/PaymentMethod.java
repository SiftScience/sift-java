package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentMethod {
    @Expose @SerializedName("$payment_type") private String paymentType;
    @Expose @SerializedName("$payment_gateway") private String paymentGateway;
    @Expose @SerializedName("$card_bin") private String cardBin;
    @Expose @SerializedName("$card_last4") private String cardLast4;
    @Expose @SerializedName("$avs_result_code") private String avsResultCode;
    @Expose @SerializedName("$cvv_result_code") private String cvvResultCode;
    @Expose @SerializedName("$verification_status") private String verificationStatus;
    @Expose @SerializedName("$routing_number") private String routingNumber;
    @Expose @SerializedName("$decline_reason_code") private String declineReasonCode;
    @Expose @SerializedName("$paypal_payer_id") private String paypalPayerId;
    @Expose @SerializedName("$paypal_payer_email") private String paypalPayerEmail;
    @Expose @SerializedName("$paypal_payer_status") private String paypalPayerStatus;
    @Expose @SerializedName("$paypal_address_status") private String paypalAddressStatus;
    @Expose @SerializedName("$paypal_protection_eligibility") private String
            paypalProtectionEligibility;
    @Expose @SerializedName("$paypal_payment_status") private String paypalPaymentStatus;
    @Expose @SerializedName("$stripe_cvc_check") private String stripeCvcCheck;
    @Expose @SerializedName("$stripe_address_line1_check") private String stripeAddressLine1Check;
    @Expose @SerializedName("$stripe_address_line2_check") private String stripeAddressLine2Check;
    @Expose @SerializedName("$stripe_address_zip_check") private String stripeAddressZipCheck;
    @Expose @SerializedName("$stripe_funding") private String stripeFunding;
    @Expose @SerializedName("$stripe_brand") private String stripeBrand;
    @Expose @SerializedName("$ach") private Ach ach;
    @Expose @SerializedName("$sepa") private Sepa sepa;
    @Expose @SerializedName("$wire") private Wire wire;

    public String getPaymentType() {
        return paymentType;
    }

    public PaymentMethod setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public String getPaymentGateway() {
        return paymentGateway;
    }

    public PaymentMethod setPaymentGateway(String paymentGateway) {
        this.paymentGateway = paymentGateway;
        return this;
    }

    public String getCardBin() {
        return cardBin;
    }

    public PaymentMethod setCardBin(String cardBin) {
        this.cardBin = cardBin;
        return this;
    }

    public String getCardLast4() {
        return cardLast4;
    }

    public PaymentMethod setCardLast4(String cardLast4) {
        this.cardLast4 = cardLast4;
        return this;
    }

    public String getCvvResultCode() {
        return cvvResultCode;
    }

    public PaymentMethod setCvvResultCode(String cvvResultCode) {
        this.cvvResultCode = cvvResultCode;
        return this;
    }

    public String getAvsResultCode() {
        return avsResultCode;
    }

    public PaymentMethod setAvsResultCode(String avsResultCode) {
        this.avsResultCode = avsResultCode;
        return this;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public PaymentMethod setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
        return this;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public PaymentMethod setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
        return this;
    }

    public String getDeclineReasonCode() {
        return declineReasonCode;
    }

    public PaymentMethod setDeclineReasonCode(String declineReasonCode) {
        this.declineReasonCode = declineReasonCode;
        return this;
    }

    public String getPaypalPayerId() {
        return paypalPayerId;
    }

    public PaymentMethod setPaypalPayerId(String paypalPayerId) {
        this.paypalPayerId = paypalPayerId;
        return this;
    }

    public String getPaypalPayerEmail() {
        return paypalPayerEmail;
    }

    public PaymentMethod setPaypalPayerEmail(String paypalPayerEmail) {
        this.paypalPayerEmail = paypalPayerEmail;
        return this;
    }

    public String getPaypalPayerStatus() {
        return paypalPayerStatus;
    }

    public PaymentMethod setPaypalPayerStatus(String paypalPayerStatus) {
        this.paypalPayerStatus = paypalPayerStatus;
        return this;
    }

    public String getPaypalAddressStatus() {
        return paypalAddressStatus;
    }

    public PaymentMethod setPaypalAddressStatus(String paypalAddressStatus) {
        this.paypalAddressStatus = paypalAddressStatus;
        return this;
    }

    public String getPaypalProtectionEligibility() {
        return paypalProtectionEligibility;
    }

    public PaymentMethod setPaypalProtectionEligibility(String paypalProtectionEligibility) {
        this.paypalProtectionEligibility = paypalProtectionEligibility;
        return this;
    }

    public String getPaypalPaymentStatus() {
        return paypalPaymentStatus;
    }

    public PaymentMethod setPaypalPaymentStatus(String paypalPaymentStatus) {
        this.paypalPaymentStatus = paypalPaymentStatus;
        return this;
    }

    public String getStripeCvcCheck() {
        return stripeCvcCheck;
    }

    public PaymentMethod setStripeCvcCheck(String stripeCvcCheck) {
        this.stripeCvcCheck = stripeCvcCheck;
        return this;
    }

    public String getStripeAddressLine1Check() {
        return stripeAddressLine1Check;
    }

    public PaymentMethod setStripeAddressLine1Check(String stripeAddressLine1Check) {
        this.stripeAddressLine1Check = stripeAddressLine1Check;
        return this;
    }

    public String getStripeAddressLine2Check() {
        return stripeAddressLine2Check;
    }

    public PaymentMethod setStripeAddressLine2Check(String stripeAddressLine2Check) {
        this.stripeAddressLine2Check = stripeAddressLine2Check;
        return this;
    }

    public String getStripeAddressZipCheck() {
        return stripeAddressZipCheck;
    }

    public PaymentMethod setStripeAddressZipCheck(String stripeAddressZipCheck) {
        this.stripeAddressZipCheck = stripeAddressZipCheck;
        return this;
    }

    public String getStripeFunding() {
        return stripeFunding;
    }

    public PaymentMethod setStripeFunding(String stripeFunding) {
        this.stripeFunding = stripeFunding;
        return this;
    }

    public String getStripeBrand() {
        return stripeBrand;
    }

    public PaymentMethod setStripeBrand(String stripeBrand) {
        this.stripeBrand = stripeBrand;
        return this;
    }

    public Ach getAch() {
        return ach;
    }

    public PaymentMethod setAch(Ach ach) {
        this.ach = ach;
        return this;
    }

    public Sepa getSepa() {
        return sepa;
    }

    public PaymentMethod setSepa(Sepa sepa) {
        this.sepa = sepa;
        return this;
    }

    public Wire getWire() {
        return wire;
    }

    public PaymentMethod setWire(Wire wire) {
        this.wire = wire;
        return this;
    }
}
