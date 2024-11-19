<script setup lang="ts">
import { ref } from 'vue';
import InvoiceForm from '../components/InvoiceForm.vue';
import InvoiceList from '../components/InvoiceList.vue';

interface Invoice {
  id: number;
  description: string;
  amount: number;
}

const invoices = ref<Invoice[]>([]);

const handleAddInvoice = (newInvoice: Invoice) => {
  invoices.value.push(newInvoice);
};
</script>



<template>
  <main>
    <h1>Invoice management</h1>
    <InvoiceForm @addInvoice="handleAddInvoice" />
    <InvoiceList :invoices="invoices" />
  </main>
</template>
