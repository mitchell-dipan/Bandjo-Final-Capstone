<template>
  <div><nav-bar></nav-bar><band-card></band-card></div>
</template>

<script>
import BandCard from "../components/BandCard.vue";
import NavBar from "../components/navbar.vue";
export default {
  name: "band-page",
  components: {
    BandCard,
    NavBar,
  },
};
</script>

<style>
</style>