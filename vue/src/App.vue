<template>
  <div id="app">
    <div id="nav" v-if="$store.state.token != ''">
      <router-link id="link" v-bind:to="{ name: 'home' }">Home</router-link>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
  justify-content: center;
  width: 100%;
  height: 100%;
  background-color: #4f5d75;
  border-bottom: #2d3142 solid 2px;
}
#link {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  text-decoration: none;
  color: white;
  font-size: 2em;
}
</style>