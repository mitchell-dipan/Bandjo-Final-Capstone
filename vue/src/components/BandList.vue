<template>
  <div class="main">
    <div id="search">
      <img src="../../assets/bandjo-logo-final-removebg-preview.png" />
      <h1>Explore</h1>
      <input
        type="text"
        id="bandNameFilter"
        v-model="search.bandName"
        placeholder="Type any band or artist..."
      />
      <div v-for="band in filteredList" v-bind:key="band.id">
        <router-link
          :to="{ name: 'page', params: { id: band.bandID } }"
          class="route"
        >
          <p class="result">
            {{ band.name }}
          </p>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import MessageService from "../services/MessageService.js";

export default {
  name: "band-list",
  data() {
    return {
      bands: [],

      search: {
        bandName: "",
      },
    };
  },
  created() {
    MessageService.getAllBands().then((response) => {
      this.bands = response.data;
    });
  },
  computed: {
    filteredList() {
      let filteredBands = this.bands;
      if (this.search.bandName != "") {
        filteredBands = filteredBands.filter((band) =>
          band.name.toLowerCase().includes(this.search.bandName.toLowerCase())
        );
        return filteredBands;
      } else {
        return [];
      }
    },
  },
};
</script>

<style scoped>
.main {
  display: flex;
  align-items: center;
  flex-direction: column;
  height: 100%;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
#search {
  display: flex;
  flex-direction: column;
  width: 50%;
}
img {
  width: 45%;
  align-self: center;
}
h1 {
  margin-top: 1%;
  font-size: 4em;
  font-weight: 400;
  color: white;
}
input {
  margin-top: 5%;
  width: 100%;
  padding: 2%;
  font-size: 2em;
  border-radius: 20px;
  border: 1px white solid;
}
.result {
  margin-top: 5%;
  padding: 10%;
  background-color: #2d3142;
  color: white;
  font-size: 3em;
  border: solid 1px #ef8354;
  border-radius: 10px;
  text-decoration: none;
}
.route {
  text-decoration: none;
}
</style>