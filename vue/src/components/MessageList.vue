<template>
  <div>
    <h1>notifications</h1>
    <div v-for="message in messages" :key="message.id">
      <h2>{{ message.content }}</h2>
      <p>{{ message.date }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      messages: [],
    };
  },
  created() {
    this.messages = this.setMessages;
  },

  computed: {
    setMessages() {
      return this.$store.state.messages;
    },
  },
};
</script>