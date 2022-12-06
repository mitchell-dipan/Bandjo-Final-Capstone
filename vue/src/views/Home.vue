<template>
  <div class="home">
    <message-list></message-list>
  </div>
</template>

<script>
import MessageList from "../components/MessageList.vue";
export default {
  name: "home",
  components: {
    MessageList,
  },
};
</script>
