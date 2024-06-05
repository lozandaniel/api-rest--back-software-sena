package com.example.backproyectswdistriquesos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "medios_de_pago")
public class PaymentMethod {
    @Id
    @Column(name = "id_medio_de_pago")
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long paymentMethodId;

    @Column(name = "tipo_de_pago")
    private String typePaymentMethod;
    @Column(name = "descripcion")
    private String description;

    public PaymentMethod() {
    }

    public PaymentMethod(Long paymentMethodId, String typePaymentMethod, String description) {
        this.paymentMethodId = paymentMethodId;
        this.typePaymentMethod = typePaymentMethod;
        this.description = description;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getTypePaymentMethod() {
        return typePaymentMethod;
    }

    public void setTypePaymentMethod(String typePaymentMethod) {
        this.typePaymentMethod = typePaymentMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
