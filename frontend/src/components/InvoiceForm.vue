<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';

const emit = defineEmits<{
  (e: 'addInvoice', newInvoice: { itemName: string, itemPrice: number, quantity: number }): void;
}>();

const itemName = ref('');
const itemPrice = ref<number | string>('');
const quantity = ref<number | string>('');

const handleSubmit = async () => {
  if (!itemName.value || !itemPrice.value || !quantity.value) return;

  const newInvoice = {
    itemName: itemName.value,
    itemPrice: parseFloat(itemPrice.value as string),
    quantity: parseInt(quantity.value as string, 10),
  };

  try {
    const response = await axios.post(
      'http://localhost:8080/api/invoices',
      newInvoice,
      {
        headers: {
          'Content-Type': 'application/json',
        },
      }
    );

    emit('addInvoice', response.data);
    // itemName.value = '';
    // itemPrice.value = '';
    // quantity.value = '';
  } catch (error) {
    console.error('Error adding invoice:', error);
  }
};
</script>

<template>
  <div class="invoice-form">
    <h2>Add Invoice</h2>
    <form @submit.prevent="handleSubmit">
      <input
        type="text"
        placeholder="Item Name"
        v-model="itemName"
      />
      <input
        type="number"
        placeholder="Item Price"
        v-model.number="itemPrice"
      />
      <input
        type="number"
        placeholder="Quantity"
        v-model.number="quantity"
      />
      <button type="submit">Add Invoice</button>
    </form>
  </div>
</template>

<style scoped>
.invoice-form {
  margin-bottom: 20px;
}
form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
</style>
