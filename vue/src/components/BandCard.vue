<template>
  <div class="card">
    <div class="profile">
      <img v-bind:src="band.profilePic" />
      <div class="title-members">
        <h1>{{ band.name }}</h1>
        <p id="description">{{ band.description }}</p>
        <p>Members: {{ band.members }}</p>
        <div class="genre-box">
          <ul v-for="genre in genres" v-bind:key="genre.id">
            <li>{{ genre.genreName }}</li>
          </ul>
        </div>
      </div>
    </div>
    <ul v-for="show in shows" v-bind:key="show.id">
      <li>
        {{ show.showName }} / {{ show.showLocation }} / {{ show.showDate }}
      </li>
    </ul>
    <div class="gallery">
      <h2>Gallery</h2>
      <div class="picture-gallery">
        <ul v-for="picture in pictures" v-bind:key="picture.id">
          <li><img v-bind:src="picture.pic_url" /></li>
        </ul>
      </div>
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
  margin-left: 3%;
}
.profile img {
  border: solid black 2px;
  border-radius: 100%;
  object-fit: cover;
  width: 20%;
  align-self: flex-start;
  margin-top: 2%;
}
.profile h1 {
  font-size: 3em;
}
.title-members {
  margin-left: 2%;
}
.title-members p {
  margin-top: 2%;
  font-size: 2em;
}
#description {
  font-style: italic;
}
.genre-box {
  display: flex;
  margin-top: 4%;
}
.genre-box li {
  list-style: none;
  padding-right: 2vw;
  padding-left: 2vw;
  padding-top: 1vh;
  padding-bottom: 1vh;
  background-color: #ef8354;
  border-radius: 10px;
  font-weight: bold;
  color: white;
}
.gallery {
  display: flex;
  flex-direction: column;
  align-self: flex-start;
  align-items: flex-start;
  padding-top: 3%;
}
.gallery h2 {
  margin-left: 5%;
  margin-bottom: 3%;
  font-size: 3em;
  color: white;
}
.picture-gallery {
  display: flex;
  align-self: flex-start;
  flex-wrap: wrap;
  width: 70vw;
}
.picture-gallery li {
  list-style: none;
}
.picture-gallery li img {
  width: 15vw;
  height: 30vh;
  object-fit: cover;
  opacity: 0.7;
  transition: 1s ease;
}
.picture-gallery li img:hover {
  opacity: 1;
  transition: 0.5s ease;
}
</style>