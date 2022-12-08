<template>
  <div>
    <nav-bar><create-band></create-band></nav-bar>
  </div>
</template>

<script>
import CreateBand from "../components/CreateBand.vue";
import NavBar from "../components/navbar.vue";
export default {
  name: "make-band",
  components: {
    CreateBand,
    NavBar,
  },
};
</script>

<style>
</style>