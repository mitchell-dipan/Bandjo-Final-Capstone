<template>
  <div>
    <table>
      <thead>
        <tr>
          <th>Band Name</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input type="text" id="bandNameFilter" v-model="search.bandName" />
          </td>
        </tr>
        <tr v-for="band in filteredList" v-bind:key="band.id">
          <td>
            {{ band.name }}
          </td>
        </tr>
      </tbody>
    </table>
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

<style>
</style>