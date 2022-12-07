<template>
  <div id="app">
    <div id="nav" v-if="$store.state.token != ''">
      <router-link id="link" v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link
        id="link"
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <router-view />
  </div>
</template>
<style scoped>
#app {
  width: 100%;
  height: 100%;
  margin: 0 auto;
}
#nav {
  display: flex;
  flex-direction: column;
  width: 15%;
  height: 100%;
  background-color: white;
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
}
#link {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  text-decoration: none;
  color: black;
  font-size: 2em;
}
</style>