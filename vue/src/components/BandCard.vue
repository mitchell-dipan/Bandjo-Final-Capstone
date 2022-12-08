<template>
  <div class="card">
    <img id="prof-pic" src="" />
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
    <ul
      v-for="picture in pictures"
      v-bind:key="picture.id"
      class="picture-gallery"
    >
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

<style scoped>
.card {
  display: flex;
  position: relative;
  flex-direction: column;
  align-items: center;
  margin-left: 20%;
  width: 70%;
  border-left: #2d3142 solid 2px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
#prof-pic {
  border: solid black 2px;
  border-radius: 100%;
}
.picture-gallery {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;
}
.picture-gallery > li {
  flex-basis: 350px;
}
.picture-gallery li img {
  object-fit: cover;
  max-width: 50%;
  height: auto;
  vertical-align: middle;
  border-radius: 5px;
}
.picture-gallery::after {
  content: "";
  flex-basis: 350px;
}
</style>