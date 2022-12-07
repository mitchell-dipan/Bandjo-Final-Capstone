<template>
  <div class="home">
    <nav-bar></nav-bar>
    <message-list></message-list>
  </div>
</template>

<script>
import MessageList from "../components/MessageList.vue";
import NavBar from "../components/navbar.vue";
export default {
  name: "home",
  components: {
    MessageList,
    NavBar,
  },
};
</script>
<style scoped>
.home {
  display: flex;
}
</style>