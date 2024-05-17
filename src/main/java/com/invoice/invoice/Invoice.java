package com.invoice.invoice;

import jakarta.persistence.Id;
import lombok.Data;
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
    private Long id;

    private String itemName;
    private BigDecimal itemPrice;
    private int quantity;
    private String invoiceNumber;
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
