<template>
  <div>
    <ul v-for="genre in genres" :key="genre.genreId">
      <li>{{ genre.genreName }}</li>
    </ul>
  </div>
</template>

<script>
import MessageService from "../services/MessageService";
export default {
  data() {
    return {
      genres: [],
    };
  },
  created() {
    MessageService.getAllGenres().then((r) => {
      this.genres = r.data;
    });
  },
};
</script>

<style>
</style>