<template>
  <main>
    <img src="../../assets/notes_logo.png" />
    <div id="message-box" v-for="message in messages" :key="message.id">
      <div class="profile">
        <img src="../../assets/bandjo-logo-final-removebg-preview.png" />
        <h1>{{ message.name }}</h1>
      </div>
      <h3>{{ message.content }}</h3>
      <p>Posted on {{ message.date }}</p>
    </div>
  </main>
</template>

<script>
import MessageService from "../services/MessageService";

export default {
  data() {
    return {
      messages: [],
    };
  },
  created() {
    MessageService.getAllNotifications().then((response) => {
      this.messages = response.data;
    });
  },

  computed: {
    setMessages() {
      return this.$store.state.messages;
    },
  },
};
</script>
<style scoped>
main {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
img {
  width: 10%;
}
#message-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  width: 50%;
  border-radius: 10px;
  margin-top: 2%;
}
.profile {
  display: flex;
}
#message-box img {
  width: 10%;
  border: 1px solid black;
  border-radius: 100%;
  margin-top: 2%;
  margin-left: 5%;
}
#message-box h1 {
  margin-left: 2%;
}
</style>