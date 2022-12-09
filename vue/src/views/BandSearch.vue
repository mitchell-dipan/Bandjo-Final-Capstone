<template>
  <div><nav-bar></nav-bar><band-list></band-list></div>
</template>

<script>
import BandList from "../components/BandList.vue";
import NavBar from "../components/navbar.vue";
export default {
  name: "band-search",
  components: {
    BandList,
    NavBar,
  },
};
</script>

<style scoped>
</style>