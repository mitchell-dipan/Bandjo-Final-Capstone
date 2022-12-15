<template>
  <div>
    <nav-bar></nav-bar>
    <manage-genres></manage-genres>
  </div>
</template>

<script>
import NavBar from "../components/navbar.vue";
import ManageGenres from "../components/ManageGenres.vue";
export default {
  components: {
    ManageGenres,
    NavBar,
  },
};
</script>

<style>
</style>