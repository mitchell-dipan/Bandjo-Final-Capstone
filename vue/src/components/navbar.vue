<template>
  <div id="nav" v-if="$store.state.token != ''">
    <img src="../../assets/bandjo-logo-final-removebg-preview.png" />
    <router-link id="link" v-bind:to="{ name: 'home' }">Home</router-link>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <router-link id="link" v-bind:to="{ name: 'search-bands' }"
      >Search</router-link
    >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
  align-items: flex-start;
  width: 20%;
  height: 100vh;
  background-color: #2d3142;
}
img {
  width: 50%;
}
#link:first-child {
  margin-top: 25%;
}
#link {
  margin-left: 10%;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  text-decoration: none;
  color: white;
  font-size: 2em;
}
#link:hover {
  background-color: gray;
}
</style>