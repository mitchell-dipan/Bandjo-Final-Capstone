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
      <div id="edit-profile" @click="unfollowBand" v-if="status === true">
        Following
      </div>
      <div id="edit-profile" @click="followBand" v-else>Follow</div>
      <div>
        <button id="edit-profile">Edit Profile</button>
      </div>
    </div>
    <div class="upcoming-shows">
      <h2>Upcoming</h2>
      <h2 id="shows">Shows</h2>
      <ul v-for="show in shows" v-bind:key="show.id">
        <li>
          {{ show.showName }} <br />
          {{ show.showLocation }} <br />
          {{ show.showDate }}
        </li>
      </ul>
    </div>
    <div class="picture-gallery">
      <h2>Gallery</h2>
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
      status: "",
      follow: "Follow",
    };
  },
  created() {
    const bandId = parseInt(this.$route.params.id);
    const userId = this.$store.state.user.id;
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
    MessageService.checkFollowStatus(bandId, userId).then((r) => {
      this.status = r.data;
    });
  },
  methods: {
    followBand() {
      const bandId = parseInt(this.$route.params.id);
      const userId = this.$store.state.user.id;
      MessageService.follow(bandId, userId).then(() => {});
      this.status = true;
    },
    unfollowBand() {
      const bandId = parseInt(this.$route.params.id);
      const userId = this.$store.state.user.id;
      MessageService.unfollow(bandId, userId).then(() => {});
      this.status = false;
    },
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
#edit-profile {
  padding: 0.75rem 1.25rem;
  border-radius: 10rem;
  color: #fff;
  text-transform: uppercase;
  font-size: 1rem;
  letter-spacing: 0.15rem;
  transition: all 0.3s;
  overflow: hidden;
  z-index: 1;
  background-color: #ef8354;
}
.upcoming-shows {
  display: flex;
  align-self: flex-start;
  flex-wrap: wrap;
  width: 75vw;
  margin-left: 5%;
  background-color: #2d3142;
  padding-top: 2%;
  margin-bottom: 3%;
  margin-top: 2%;
}
.upcoming-shows h2 {
  margin-left: 5%;
  margin-bottom: 3%;
  font-size: 1.7em;
  color: white;
  writing-mode: vertical-rl;
  text-orientation: upright;
}
.upcoming-shows ul li {
  list-style: none;
  font-size: 1.5em;
  color: white;
  background: #ef8354;
  padding-top: 10%;
  padding-bottom: 10%;

  text-align: center;
  width: 15vw;
  margin-right: 10%;
}
.picture-gallery h2 {
  margin-left: 5%;
  margin-bottom: 3%;
  font-size: 1.7em;
  color: white;
  writing-mode: vertical-rl;
  text-orientation: upright;
}
#shows {
  margin-left: 2%;
}
.picture-gallery {
  display: flex;
  align-self: flex-start;
  flex-wrap: wrap;
  width: 75vw;
  margin-left: 5%;
  background-color: #2d3142;
  padding-top: 2%;
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