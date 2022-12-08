<template>
  <div class="card">
    <div class="profile">
      <img v-bind:src="band.profilePic" />
      <div class="title-members">
        <h1>{{ band.name }}</h1>
        <p>{{ band.description }}</p>
      </div>
    </div>
    <h2>Members: {{ band.members }}</h2>
    <p>{{ band.description }}</p>

    <ul v-for="show in shows" v-bind:key="show.id">
      <li>
        {{ show.showName }} / {{ show.showLocation }} / {{ show.showDate }}
      </li>
    </ul>
    <ul v-for="genre in genres" v-bind:key="genre.id">
      <li>{{ genre.genreName }}</li>
    </ul>
    <div class="picture-gallery">
      <ul v-for="picture in pictures" v-bind:key="picture.id">
        <li><img v-bind:src="picture.pic_url" /></li>
      </ul>
    </div>
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
.profile {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.profile img {
  border: solid black 2px;
  border-radius: 100%;
  width: 20%;
  align-self: flex-start;
  margin-top: 2%;
}
.profile h1 {
  margin-left: 5%;
  font-size: 3em;
}
.picture-gallery {
  display: flex;
  flex-wrap: wrap;
  width: 45vw;
}
.picture-gallery li {
  list-style: none;
}
.picture-gallery li img {
  width: 15vw;
  height: 30vh;
  object-fit: cover;
}
</style>