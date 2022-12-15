<template>
  <div id="nav" v-if="$store.state.token != ''">
    <img src="../../assets/bandjo-logo-final-removebg-preview.png" />
    <router-link id="link" v-bind:to="{ name: 'home' }">Home</router-link>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <router-link id="link" v-bind:to="{ name: 'search-bands' }"
      >Search</router-link
    >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <router-link id="link" :to="{ name: 'create-band' }"
      >Create Band</router-link
    >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <router-link
      v-if="this.$store.state.user.id == 2"
      id="link"
      :to="{ name: 'manage-genres' }"
      >Manage Genres</router-link
    >
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <router-link
      id="link"
      v-bind:to="{ name: 'logout' }"
      v-if="$store.state.token != ''"
      >Logout</router-link
    >
  </div>
</template>

<script>
export default {};
</script>

<style scoped >
#nav {
  display: flex;
  position: fixed;
  flex-direction: column;
  align-items: center;
  width: 20%;
  height: 100vh;
  border-right: 2px solid white;
}
img {
  width: 70%;
}
#link:first-child {
  margin-top: 25%;
}
#link {
  align-self: flex-start;
  margin-left: 23%;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  text-decoration: none;
  color: white;
  font-size: 2em;
}
#link:hover {
  text-decoration: underline;
}
</style>