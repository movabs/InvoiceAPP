package com.invoice.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    @PostMapping
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        System.out.println("Received Invoice: " + invoice);

        if (invoice == null) {
            System.out.println("Invoice is null after deserialization!");
            return null;
        }
        invoice.calculateTotal();
        return invoiceService.createInvoice(invoice);
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }
}
