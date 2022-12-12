<template>
  <div>
    <nav-bar></nav-bar>
    <edit-band></edit-band>
  </div>
</template>

<script>
import EditBand from "../components/EditProfile.vue";
import NavBar from "../components/navbar.vue";
export default {
  name: "edit-profile-view",
  components: {
    NavBar,
    EditBand,
  },
};
</script>

<style scoped>
</style>