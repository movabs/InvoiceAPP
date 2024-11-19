package com.invoice.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("itemName")
    private String itemName;
    @JsonProperty("itemPrice")
    private BigDecimal itemPrice;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("invoiceNumber")
    private String invoiceNumber;
    @JsonProperty("total")
    private BigDecimal total;
    @CreationTimestamp
    private Date createdAt;

    public Invoice() {
        this.invoiceNumber = generateInvoiceNumber();
    }

    private String generateInvoiceNumber() {
        long timestamp = System.currentTimeMillis();
        String invoiceNumber = "INV-" + timestamp;
        if (id != null) {
            invoiceNumber += id;
        }
        return invoiceNumber;
    }

    public void calculateTotal() {
        total = itemPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
