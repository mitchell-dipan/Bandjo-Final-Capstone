<template>
  <div>
    <h1>{{ band.name }}</h1>
    <h2>{{ band.members }}</h2>
    <p>{{ band.description }}</p>

    <ul v-for="show in shows" v-bind:key="show.id">
      <li>
        {{ show.showName }} / {{ show.showLocation }} / {{ show.showDate }}
      </li>
    </ul>
    <ul v-for="genre in genres" v-bind:key="genre.id">
      <li>{{ genre.genreName }}</li>
    </ul>
    <ul v-for="picture in pictures" v-bind:key="picture.id">
      <li><img v-bind:src="picture.pic_url" /></li>
    </ul>
  </div>
</template>

<script>
import MessageService from "../services/MessageService";

export default {
  name: "page-detail",
  data() {
    return {
      band: {},
      pictures: [],
      shows: [],
      genres: [],
    };
  },
  created() {
    const bandId = parseInt(this.$route.params.id);
    MessageService.getBandPage(bandId).then((r) => {
      this.band = r.data;
    });
    MessageService.getPictures(bandId).then((r) => {
      this.pictures = r.data;
    });
    MessageService.getShows(bandId).then((r) => {
      this.shows = r.data;
    });
    MessageService.getGenres(bandId).then((r) => {
      this.genres = r.data;
    });
  },
};
</script>

<style>
</style>