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
            {{ band.bandName }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "band-list",
  data() {
    return {
      bands: [
        {
          id: 1,
          bandName: "Mountain",
        },
        {
          id: 2,
          bandName: "Black Sabbath",
        },
        {
          id: 3,
          bandName: "Red Hot Chili Peppers",
        },
      ],

      search: {
        bandName: "",
      },
    };
  },
  computed: {
    filteredList() {
      let filteredBands = this.bands;
      if (this.search.bandName != "") {
        filteredBands = filteredBands.filter((band) =>
          band.bandName
            .toLowerCase()
            .includes(this.search.bandName.toLowerCase())
        );
      }
      return filteredBands;
    },
  },
};
</script>

<style>
</style>