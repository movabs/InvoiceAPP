<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';

interface Invoice {
  id: number;
  itemName: string;
  total: number;
}

const invoices = ref<Invoice[]>([]);
const editInvoice = ref<Invoice | null>(null);

onMounted(() => {
  axios
    .get('http://localhost:8080/api/invoices')
    .then((response) => {
      invoices.value = response.data;
    })
    .catch((error) => {
      console.error('Error fetching invoices:', error);
    });
});

const handleEdit = (invoice: Invoice) => {
  editInvoice.value = invoice;
};

const handleDelete = (id: number) => {
  axios
    .delete(`http://localhost:8080/api/invoices/${id}`)
    .then(() => {
      invoices.value = invoices.value.filter((invoice) => invoice.id !== id);
    })
    .catch((error) => {
      console.error('Error deleting invoice:', error);
    });
};
</script>

<template>
  <div class="invoice-list">
    <h2>Invoices</h2>
    <ul>
      <li
        v-for="invoice in invoices"
        :key="invoice.id"
        :class="['invoice-item', { editing: editInvoice?.id === invoice.id }]"
      >
        <div class="item-details">
          <div class="item-name">{{ invoice.itemName }}</div>
          <div class="total">Total: ${{ invoice.total }}</div>
        </div>
        <div class="actions">
          <button @click="handleEdit(invoice)">Edit</button>
          <button @click="handleDelete(invoice.id)">Delete</button>
        </div>
      </li>
    </ul>
  </div>
</template>


<style scoped>
.invoice-list ul {
  list-style-type: none;
  padding: 0;
}
.invoice-item {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #e4e4e4;
  border-radius: 4px;
}
.invoice-item.editing {
  background-color: #f0f0f0;
}
.item-details {
  display: flex;
  flex-direction: column;
  color: #e1dada;
}
.actions {
  display: flex;
  gap: 10px;
}
button {
  padding: 5px 10px;
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 4px;
}
button:hover {
  background-color: #0056b3;
}
</style>
